package red;

import java.net.*;

//Esta es la clase donde recibimos el movimiento de la pala del cliente.
public class RecibirDatosJuego implements Runnable {
	private Socket socket;

	public RecibirDatosJuego(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}