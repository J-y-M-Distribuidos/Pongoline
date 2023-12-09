package red;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

/*En esta clase es donde decides a que cliente conectarte de la lista de clientes disponible.*/
public class ConectarConJugador implements Runnable {
	private Cliente mi_cliente;
	private String ipExt;
	private int portExt;
	private static ExecutorService pool;

	public ConectarConJugador(Cliente clientei, String ip, int puerto, ExecutorService poolHilos) {
		this.mi_cliente = clientei;
		this.ipExt = ip;
		this.portExt = puerto;
		pool = poolHilos;
	}

	@Override
	public void run() {//Vamos a presuponer que el otro sigue activo escuchando.


		try (Socket s = new Socket(ipExt, portExt);//Nos conectamos al otro cliente.
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());) {
			
			dout.writeBytes("Si\n");//ESCRIBE UN SI PERO NO LE LLEGA.
			pool.execute(new EnviarDatosJuego(s));
			pool.execute(new RecibirDatosJuego(s));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
