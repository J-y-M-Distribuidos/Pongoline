package red;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

//Esta es la clase donde recibimos el movimiento de la pala del cliente.
public class RecibirDatosJuego implements Runnable {
	private Socket socket;
	private byte datos;

	public RecibirDatosJuego(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		
		try (DataInputStream din = new DataInputStream(socket.getInputStream());){ //aqui
			while (true) {
				datos = din.readByte();
				System.out.println(datos);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}