package red;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
//Esta es la clase donde mandamos lo que queremos que haga nuestra pala en juego del oponente.

import javax.swing.JPanel;

import juego_offline.MarcoJuego;
import juego_offline.PanelJuego.AL;

public class EnviarDatosJuego extends JPanel implements Runnable {

	private Socket socket;
	protected int tPulsada = 1;
	protected int tSoltada = 1;
	protected Boolean mandar = false;

	public EnviarDatosJuego(Socket s) {
		this.socket = s;
		
		this.addKeyListener(new AL());
		setFocusable(true);
	}
	/*[[IMPORTANTE]]
	 * EL DATA-OUTPUT-STREAM ¡¡¡¡NO!!! SE PONE EN EL TRY WITH RESOURCES PORQUE ME CIERRA EL SOCKET Y NO LO PUEDO USAR MAS. 
	 * [[IMPORTANTE]]*/
	//NO CERRAR EL SOCKET
	@Override
	public void run() {
		
		try {
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			MarcoJuego juego = new MarcoJuego(dout);
			/*Timer t = new Timer();
			t.scheduleAtFixedRate(new TimerTask() {//de momento no paramos la ejecucion.
				
				@Override
				public void run() {
				
					mandarDatos(dout);
					
				}
			}, 0L, 300L);//Mandamos datos cada 3 ms. Asi es un flujo constante.
		*/} catch (IOException e) {
		
			e.printStackTrace();
		}

	}
	public void mandarDatos(DataOutputStream dout){
		//System.out.println("holaa");
		if(mandar==true) {
				try {
					dout.writeBytes(tPulsada +"/n");
					dout.writeBytes(tSoltada +"/n");
					mandar=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}else {
				try {
					dout.writeBytes("no detecta las teclas\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
	}
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			System.out.println(tPulsada+ "    -Pulsada.");
			if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_D) {
				tPulsada = e.getKeyCode();
				System.out.println(tPulsada+ "    -Pulsada.");
				mandar= true;
			}
		}

		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_D) {
				tSoltada = e.getKeyCode();
				System.out.println(tSoltada + "    -Soltada.");
				mandar = true;
			}
		}
	}
// 0x57(W) 0x41(A) 0x53(S) 0x44(D) -> Ocupan todas 1 byte
	
	/*Podriamos haber hecho un while donde se manda la posicion todo el rato, 
	 *pero no sabemos si se va a ejecutar en el intervalo de tiempo que queremos.
	 *Esperemos  que esto no afecte mucho a la eficiencia.*/

}
