package red;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

//Esta es la clase donde recibimos el movimiento de la pala del cliente y simulamos sus pulsaciones.
public class RecibirDatosJuego implements Runnable {
	private Socket socket;
	private int datos = KeyEvent.VK_W;

	public RecibirDatosJuego(Socket s) {
		this.socket = s;
	}
//NO CERRAR EL SOCKET
	@Override
	public void run() {

		try  { // aqui
			//DataInputStream din = new DataInputStream(socket.getInputStream());
			BufferedReader buffIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Robot robot = new Robot();
			while (true) {
				/*datos = din.read(); //pensaba que esto bloqueaba
				System.out.println(datos);
				robot.keyPress(datos);
				robot.keyRelease(datos);
				System.out.println(datos);*/
			String hola = buffIn.readLine();
				
				System.out.println("he recibido "+hola);
				int tecla = Integer.parseInt(hola);
				if(tecla ==KeyEvent.VK_W){
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
				}
				if(tecla == KeyEvent.VK_A){
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyPress(KeyEvent.VK_LEFT);
					robot.keyRelease(KeyEvent.VK_LEFT);
				}
				if(tecla == KeyEvent.VK_S){
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				}
				if(tecla == KeyEvent.VK_D){
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyPress(KeyEvent.VK_RIGHT);
					robot.keyRelease(KeyEvent.VK_RIGHT);
				}
				
			}
		} catch (IOException e ) {
			e.printStackTrace();
		} catch (AWTException e) {	
			e.printStackTrace();
		}

	}
}
//0x57(W) 0x41(A) 0x53(S) 0x44(D)