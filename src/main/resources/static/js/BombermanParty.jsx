function BBServiceURL() {
        var host = window.location.host;
        var url = 'wss://' + (host) + '/bbService';
        console.log("URL Calculada: " + url);
        return url;
}


class WSBBChannel {
        constructor(URL, callback, sala, user) {
                this.URL = URL;
                this.wsocket = new WebSocket(URL);
                this.wsocket.onopen = (evt) => this.onOpen(evt);
                this.wsocket.onmessage = (evt) => this.onMessage(evt);
                this.wsocket.onerror = (evt) => this.onError(evt);
                this.receivef = callback;
                this.sala = sala;
                this.user = user;
                //this.ticket = null;
        }


        onOpen(evt) {
                console.log("In onOpen", evt);
                this.agregarJugador();

        }
        onMessage(evt) {
                console.log("In onMessage", evt);
                // Este if permite que el primer mensaje del servidor no se tenga en cuenta.
                // El primer mensaje solo confirma que se estableció la conexión.
                // De ahí en adelante intercambiaremos solo puntos(x,y) con el servidor
                if (evt.data != "Connection established.") {
                        let mens = evt.data.split("/");
                        this.receivef(mens);
                        //this.wsocket.send(this.ticket);
                }
        }
        onError(evt) {
                console.error("In onError", evt);
        }

        agregarJugador() {
                let msg = "addJugador" + " " + (this.sala) + " " + (this.user);
                console.log("agregando jugador.. " + "Sala: " + this.sala + " User: " + this.user);
                this.wsocket.send(msg);
        }
        move(x, y) {
                let msg = "mov" + " " + (this.sala) + " " + (this.user) + " " + (x) + " " + (y);
                console.log("sending: ", msg);
                this.wsocket.send(msg);
        }
        colocarBomba() {
                let msg = "bom" + " " + (this.sala) + " " + (this.user);
                console.log("sending: ", msg);
                this.wsocket.send(msg);
        }
        send(tipo, sala, jugador, mov) {
                let msg = (tipo) + " " + (sala) + " " + (jugador) + " " + (mov);
                console.log("sending: ", msg);
                this.wsocket.send(msg);
        }


}
class BloqueTemporal extends React.Component {
        constructor() {
                super();
                this.state = {
                        zoom: 3,
                };
        }

        render() {
                return (
                        <img src="../images/BloqueTemporal.png"
                                style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                        ></img>
                );
        }
}
class Fuego extends React.Component {
        constructor() {
                super();
                this.state = {
                        zoom: 3,
                };
        }

        render() {

                return (
                        <img src={"../images/explosion" + this.props.tipo + ".png"}
                                style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                        ></img>
                );
        }
}
class Bomba extends React.Component {
        constructor(props) {
                super(props);
                this.state = {

                        zoom: 3,

                };
        }

        render() {

                return (
                        <img src="../images/bomba.png"
                                style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                        ></img>

                );
        }
}

class BloqueFijo extends React.Component {
        constructor() {
                super();
                this.state = {
                        zoom: 3,
                };
        }

        render() {
                return (
                        <img src="../images/BloqueFijo.png"
                                style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                        ></img>
                );
        }
}
class Jugador extends React.Component {
        constructor(props) {
                super(props);
                this.state = {
                        zoom: 3,
                        colores: ["red","blue","orange","green"],
                };
        }


        render() {
                return (
                        <div>
                                <div style={{ color: "white", background: this.state.colores[this.props.num] , marginLeft: 700, marginRight: 1000 }}>
                                        <div> {this.props.nombre}</div>
                                <div> {this.props.puntos+"/"+ this.props.muertes}</div>

                                </div>
                                <img src="../images/jugador.png"
                                        style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                                ></img>
                        </div>
                );
        }
}
class Escenario extends React.Component {
        constructor(props) {
                super(props);
                this.comunicationWS =
                        new WSBBChannel(BBServiceURL(),
                                (msg) => {
                                        console.log("On func call back ", msg);
                                        if (msg[0] === "Temp") {
                                                this.prepararBloquesTemporales(msg[1]);
                                        } else if (msg[0] === "Fijo") {
                                                this.prepararBloqueFijos(msg[1]);
                                        } else if (msg[0] === "Jug") {
                                                this.mostrarJugadores(msg[1]);
                                        } else if (msg[0] === "Bomb") {
                                                this.mostrarBombas(msg[1]);
                                        } else if (msg[0] === "Fueg") {
                                                this.mostrarFuegos(msg[1]);
                                        }

                                }
                                , this.props.sala, this.props.user);
                this.state = {
                        wsreference: this.comunicationWS,
                        zoom: 3,
                        ancho: 200 * 3,
                        alto: 200 * 3,
                        bloquesfijos: [],
                        bloquesTemporales: [],
                        jugadores: [],
                        bombas: [],
                        fuegos: [],
                };
        }
        componentDidMount() {
                this.eventos();

        }
        eventos() {
                document.addEventListener('keydown', (event) => {
                        const keyName = event.key;
                        console.log('keydown event\n\n' + 'key: ' + keyName);
                        if (keyName === "ArrowUp") {
                                this.moverJugador(0, -10);

                        } else if (keyName === "ArrowDown") {
                                this.moverJugador(0, 10);

                        } else if (keyName === "ArrowLeft") {
                                this.moverJugador(-10, 0);

                        } else if (keyName === "ArrowRight") {
                                this.moverJugador(10, 0);
                        } else if (keyName === " ") {
                                this.colocarBomba();
                        }
                });
        }
        actualizar() {
                this.mostrarJugadores();

        }
        colocarBomba() {
                this.state.wsreference.colocarBomba();

        }
        mostrarBombas(bombas) {
                var obj = JSON.parse(bombas);
                this.setState({
                        bombas: obj
                });

        }
        mostrarFuegos(fuegos) {
                var obj = JSON.parse(fuegos);
                this.setState({
                        fuegos: obj
                });
        }

        moverJugador(x, y) {
                console.log("Mover" + x + " " + y + " " + this.props.user + " " + this.props.sala);
                this.state.wsreference.move(x, y);
        }

        prepararBloqueFijos(bloques) {
                var obj = JSON.parse(bloques);
                this.setState({
                        bloquesfijos: obj
                });

        }
        prepararBloquesTemporales(bloques) {
                var obj = JSON.parse(bloques);
                this.setState({
                        bloquesTemporales: obj
                });

        }
        mostrarJugadores(jugadores) {
                var obj = JSON.parse(jugadores);
                this.setState({
                        jugadores: obj
                });
        }




        render() {
                const bloquesF = this.state.bloquesfijos.map((bloque, i) => {
                        return (
                                <BloqueFijo key={i} x={bloque.x} y={bloque.y} w={bloque.ancho} h={bloque.alto}></BloqueFijo>
                        )
                })
                const bloquesT = this.state.bloquesTemporales.map((bloque, i) => {
                        return (
                                <BloqueTemporal key={i} x={bloque.x} y={bloque.y} w={bloque.ancho} h={bloque.alto}></BloqueTemporal>
                        )
                })
                const jugadores = this.state.jugadores.map((jugador, i) => {
                        return (
                                <Jugador key={i} num={i} nombre={jugador.nombre} x={jugador.x} y={jugador.y} w={jugador.ancho} h={jugador.alto} puntos={jugador.puntos} muertes={jugador.muertes}></Jugador>

                        )
                })
                const bombas = this.state.bombas.map((bomba, i) => {
                        return (
                                <Bomba key={i} x={bomba.x} y={bomba.y} w={bomba.ancho} h={bomba.alto} impacto={bomba.impacto} explosion={bomba.explosion}></Bomba>

                        )
                })
                const fuegos = this.state.fuegos.map((fuego, i) => {
                        return (
                                <Fuego key={i} x={fuego.x} y={fuego.y} w={fuego.ancho} h={fuego.alto} tipo={fuego.tipo}></Fuego>

                        )
                })
                return (
                        <div>
                                <img src="../images/fondo.jpg"
                                        style={{ width: this.state.ancho + 30, height: this.state.alto + 30, position: 'absolute', top: 0, left: 0 }}
                                >
                                </img>
                                {bloquesF}
                                {bloquesT}
                                {jugadores}
                                {bombas}
                                {fuegos}

                        </div>

                );
        }
}

class MyForm extends React.Component {
        constructor(props) {
                super(props);
                this.state = {
                        username: '',
                        sala: null,
                        visible: false,
                };
        }
        mySubmitHandler = (event) => {
                event.preventDefault();
                let sala = this.state.sala;
                if (!Number(sala)) {
                        alert("El código debe ser númerico");
                } else {
                        this.setState({ visible: true });
                }

        }
        myChangeHandler = (event) => {
                let nam = event.target.name;
                let val = event.target.value;
                this.setState({ [nam]: val });
        }
        render() {
                console.log(this.state.visible);
                return (
                        <div>
                                <form onSubmit={this.mySubmitHandler} >
                                        <h1>{this.state.username} {this.state.sala}</h1>
                                        <p>Nombre:</p>
                                        <input
                                                type='text'
                                                name='username'
                                                onChange={this.myChangeHandler}
                                        />
                                        <p>Sala:</p>
                                        <input
                                                type='text'
                                                name='sala'
                                                onChange={this.myChangeHandler}
                                        />
                                        <br />
                                        <br />
                                        <input type='submit' />

                                </form>

                                {this.state.visible ? <Escenario sala={this.state.sala} user={this.state.username} /> : null}

                        </div>
                );
        }
}

ReactDOM.render(
        <MyForm></MyForm>,
        document.getElementById('root')
);
