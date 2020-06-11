# Bomberman Party
# Autor
* **Andrés González** - [anfegoca](https://github.com/anfegoca)
# Resumen
Bomberman Party es un juego estilo arcade en el cual 4 jugadores se enfrentarán en un mapa conformado por un laberinto de bloques. Para derrotar a otros jugadores, tendrán que colocar bombas de tal manera que la onda de la explosión los alcance, para ello también deberán abrirse paso hasta ellos destruyendo los bloques que se interponen en su camino, en los bloques encontrarán power ups que los ayudarán a completar su objetivo. El objetivo principal de juego es obtener la mayor cantidad de muertes en un periodo de tiempo determinado.
# Descripción
## Antecedentes
Este juego se basa en el popular juego Bomberman el cual fue desarrollada por Hudson Soft en el año 1983, El personaje Bomberman es un robot que debe atravesar un laberinto al tiempo que evita a diversos enemigos. Las puertas que conducen a otras salas del laberinto se encuentran bajo rocas que Bomberman debe destruir con bombas. Hay objetos que pueden ayudar a mejorar las bombas de Bomberman, como la habilidad de fuego, que mejora el alcance de las explosiones de las bombas.

![img](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/e30a8ddee463e7d6e24e07b3b27e2c31.gif)

## Problema que se resuelve
Todos pudimos disfrutar de Bomberman, pero competir contra unos enemigos que son predecibles porque siempre hacen lo mismo no es muy divertido, con Bomberman Party podremos competir contra nuestros amigos y demostrarles quien es el mejor colocando bombas, además en estas espocas de aislamiento podremos pasarlas jugando Bomberman Party Online de manera segura.
## Diseño
En la interfaz principal se pueden ver la opciones crear sala o unirse a una sala, las cuales de la esas dos opciones al usuario, para avanzar a cualquiera de las dos opciones necesita colocar el nombre de usuario

![img2](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/1.png)

Al escoger la opción de crear sala se mostrará la siguiente ventana, donde se podrá ver el codigo de la sala y modificar el tiempo de duración de la partida.

![img4](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/10.png)

Cuando se cree la sala o se una a la sala a través de un codigo se los jugadores entrarán a la sala de espera, donde se mostrarán los jugadores que ingresaron a la sala y se podrá escoger la opción de jugar, al escoger esta opción se marcará al jugador como listo para jugar.
Cuando todos los jugadores estén listo el juego comenzará

![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/2.png)

![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/3.png)

Cuando inicie el juego los jugadores apareceran uno en cada esquina, deberán romper los bloques usando las bombas para llegar a los otros jugadores, también podrán obtener power ups al romper algunos bloques

![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/4.png)
![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/5.png)
![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/6.png)
![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/7.png)

Cuando un jugador mate a otro se le otorgará un power up y un punto, la partida finaliza cuando se termina el tiempo. Al terminarse se verá la siguiente ventana, donde podremos ver los puntajes de los jugadores y podremos indicar si queremos volver a jugar.

![img](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/8.png)

Al volver a jugar se devolverá a los jugadores a la sala de espera.
## Diagrama de clases

![img5](https://github.com/anfegoca/ARSW-Bomberman-Party/blob/master/resources/11.png)

## Historias de usuario

[Bomberman Party](https://tree.taiga.io/project/anfegoca-bomberman-party/backlog)
