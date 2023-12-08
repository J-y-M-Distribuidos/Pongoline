package red;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
//Esta es la clase donde mandamos lo que queremos que haga nuestra pala en juego del oponente.
public class EnviarDatosJuego implements Runnable {

	private Socket socket;
	protected int tPulsada = 1;
	protected int tSoltada = 1;
	protected Boolean mandar = false;

	public EnviarDatosJuego(Socket s) {
		this.socket = s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (DataOutputStream dout = new DataOutputStream(socket.getOutputStream());){
			Timer t = new Timer();
			t.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mandarDatos(dout);
				}
			}, 0L, 3L);//Mandamos datos cada 3 ms. Asi es un flujo constante.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void mandarDatos(DataOutputStream dout){
		if(mandar==true) {
				try {
					dout.writeByte(tPulsada);
					dout.writeByte(tSoltada);
					mandar=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
		
		
		
	}
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_D) {
				tPulsada = e.getKeyCode();
				mandar= true;
			}
		}

		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_D) {
				tSoltada = e.getKeyCode();
				mandar = true;
			}
		}
	}
// 0x57(W) 0x41(A) 0x53(S) 0x44(D) -> Ocupan todas 1 byte
	
	/*Podriamos haber hecho un while donde se manda la posicion todo el rato, 
	 *pero no sabemos si se va a ejecutar en el intervalo de tiempo que queremos.
	 *Esperemos que esto no afecte mucho a la eficiencia.*/
}
