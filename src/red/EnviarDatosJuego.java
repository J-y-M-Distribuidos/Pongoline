package red;

import java.net.*;
//Esta es la clase donde mandamos lo que queremos que haga nuestra pala en juego del oponente.
public class EnviarDatosJuego implements Runnable {

	private Socket socket;

	public EnviarDatosJuego(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
