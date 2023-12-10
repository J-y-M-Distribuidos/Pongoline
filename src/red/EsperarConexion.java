package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

import juego_offline.Juego;

public class EsperarConexion implements Runnable{

	private Cliente cliente;
	private static ExecutorService pool;
	public EsperarConexion(Cliente c, ExecutorService piscina) {
		cliente = c;
		pool = piscina;
	}
	@Override
	public void run() {
		try (ServerSocket ss = new ServerSocket(cliente.getPuerto()+7);) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {// Espera 50 minutos a que me acepte, sino me voy.

				@Override
				public void run() {
					System.out.println("No quiere jugar conmigo, me voy :(");
					System.exit(0);

				}
			}, 5 * 60 * 10000);
			try (Socket s = ss.accept();
					BufferedReader buffIn = new BufferedReader(new InputStreamReader(s.getInputStream()));){
				System.out.println("Han aceptado la solicitud.");
				timer.cancel();
				String res;
				res = buffIn.readLine();// SE SUPONE QUE ESTO BLOQUEA. PORQUE NO LO LEE BIEN?
				System.out.println(res);
				if (res == "Si") {
					EnviarDatosJuego mandar = new EnviarDatosJuego(s);
					RecibirDatosJuego recibir = new RecibirDatosJuego(s);
					pool.execute(mandar);
					pool.execute(recibir);
					Juego juego  = new Juego();//Runea el juego.
					juego.main(null);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
