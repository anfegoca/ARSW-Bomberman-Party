
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
        constructor() {
                super();
                this.state = {
                        zoom: 3,
                };
        }


        render() {
                return (
                        <img src="../images/jugador.jpg"
                                style={{ width: this.state.zoom * this.props.w, height: this.state.zoom * this.props.h, position: 'absolute', top: this.state.zoom * this.props.y, left: this.state.zoom * this.props.x }}
                        ></img>
                );
        }
}
class Escenario extends React.Component {
        constructor() {
                super();
                this.state = {
                        zoom: 3,
                        ancho: 160 * 3,
                        alto: 160 * 3,
                        bloquesfijos: [],
                        bloquesTemporales: [],
                        jugadores: [],
                };
        }
        componentDidMount() {
                this.agregarJugador();
                this.prepararBloqueFijos();
                this.prepararBloquesTemporales();
                this.mostrarJugadores();
                this.eventos();
                this.timerID = setInterval(
                        () => this.actualizar(),
                        1000
                        );

        }
        eventos(){
                document.addEventListener('keydown', (event) => {
                        const keyName = event.key;
                        console.log('keydown event\n\n' + 'key: ' + keyName);
                        if(keyName === "ArrowUp"){
                                this.moverJugador(0,-this.state.zoom);

                        }else if(keyName ==="ArrowDown"){
                                this.moverJugador(0,this.state.zoom);

                        }else if(keyName ==="ArrowLeft"){
                                this.moverJugador(-this.state.zoom,0);
                                
                        }else if(keyName ==="ArrowRight"){
                                this.moverJugador(this.state.zoom,0);
                        }
                      });
        }
        actualizar(){
                this.mostrarJugadores();

        }
        moverJugador(x,y){
                console.log("Mover"+x+" "+y+" "+this.props.user+" "+this.props.codigo);
                const data = new FormData();

                data.append('nombre', this.props.user);
                data.append('codigo', this.props.codigo);
                data.append('x', x);
                data.append('y', y);
                

                fetch("/moverJugador", {
                        method: 'post',
                        body: data
                }).then(response => {
                        console.log("mover jugador")
                }).catch(err => {
                        console.log(err)
                })
                

        }

        prepararBloqueFijos() {
                console.log("Fijos");
                var url = "/getFijos?codigo=" + this.props.codigo;
                fetch(url

                )
                        .then(res => res.json())
                        .then((result) => {
                                this.setState({
                                        bloquesfijos: result
                                });

                        });

        }
        prepararBloquesTemporales() {
                console.log("Temporales" + this.props.codigo);
                var url = "/getTemporales?codigo=" + this.props.codigo;
                fetch(url)
                        .then(res => res.json())
                        .then((result) => {
                                this.setState({
                                        bloquesTemporales: result
                                });
                        });
        }
        agregarJugador() {
                const data = new FormData();

                data.append('nombre', this.props.user);
                data.append('codigo', this.props.codigo);
                fetch("/addJugador", {
                        method: 'post',
                        body: data
                }).then(response => {
                        console.log("Jugador agregado")
                }).catch(err => {
                        console.log(err)
                })
        }
        mostrarJugadores() {
                console.log("Mostrar" + this.props.codigo);
                var url = "/getJugadores?codigo=" + this.props.codigo;
                fetch(url)
                        .then(res => res.json())
                        .then((result) => {
                                this.setState({
                                        jugadores: result
                                });
                        });

        }




        render() {
                const bloquesF = this.state.bloquesfijos.map((bloque, i) => {
                        return (
                                <BloqueFijo x={bloque.x} y={bloque.y} w={bloque.ancho} h={bloque.alto}></BloqueFijo>

                        )
                })
                const bloquesT = this.state.bloquesTemporales.map((bloque, i) => {
                        return (
                                <BloqueTemporal x={bloque.x} y={bloque.y} w={bloque.ancho} h={bloque.alto}></BloqueTemporal>

                        )
                })
                const jugadores = this.state.jugadores.map((jugador, i) => {
                        return (
                                <Jugador x={jugador.x} y={jugador.y} w={jugador.ancho} h={jugador.alto}></Jugador>

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
                                
                                {this.state.visible ? <Escenario codigo={this.state.sala} user={this.state.username} /> : null}
                        </div>
                );
        }
}

ReactDOM.render(

        <MyForm></MyForm>,
        document.getElementById('root')
);
