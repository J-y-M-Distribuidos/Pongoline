package red;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.*;

/*Este servidor se va a encargar de mantener una lista de jugadores activa
 * y mandarsela a cada cliente de jugador para que este pueda conectarse con el rival que elija.*/
public class Servidor {

	private HashMap<Integer, ArrayList<String>> listaJugadores; // Cliente key, en la lista se guarda la ip, el puerto,
																// y el nombre del jugador.

	public Servidor() {
		this.listaJugadores = new HashMap<Integer, ArrayList<String>>();
	}

	// Devuelve la lista de jugadores conectados al servidor.
	public HashMap<Integer, ArrayList<String>> getListaJugadores() {
		return this.listaJugadores;
	}

	// Añade a la lista de jugadores un nuevo jugador.
	public void anadirJugadores(int key, String ip, String port, String nickname) {
		ArrayList<String> datosJ = new ArrayList<String>();
		datosJ.add(ip);
		datosJ.add(port);
		datosJ.add(nickname);
		listaJugadores.put(key, datosJ);
	}

	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(1313);) {
			ExecutorService pool = Executors.newCachedThreadPool();
			Servidor server = new Servidor();//Ojo que servidor y server de serversocket no es lo mismo, si no lo instanciamos no va.
			Boolean open = true;
			while (open) {
				Socket s = ss.accept();
				AniadirJugadores aj = new AniadirJugadores(s,server);
				pool.execute(aj);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
