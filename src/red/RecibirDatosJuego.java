package red;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

//Esta es la clase donde recibimos el movimiento de la pala del cliente y simulamos sus pulsaciones.
public class RecibirDatosJuego implements Runnable {
	private Socket socket;
	private byte datos;

	public RecibirDatosJuego(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {

		try (DataInputStream din = new DataInputStream(socket.getInputStream());) { // aqui
			Robot robot = new Robot();
			while (true) {
				datos = din.readByte();
				robot.keyPress(datos);
				robot.keyRelease(datos);
				System.out.println(datos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {	
			e.printStackTrace();
		}

	}
}