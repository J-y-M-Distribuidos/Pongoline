package red;

import java.io.*;
import java.net.*;
import java.util.*;

public class AniadirJugadores implements Runnable {

	private Socket socket;
	private Servidor servidor;

	public AniadirJugadores(Socket s, Servidor server) {
		this.socket = s;
		this.servidor = server;
	}

	@Override
	public void run() {
		try (BufferedReader buffin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());){
			//Recibimos los datos del jugador y los añadimos a la lista de jugadores del servidor.
			String ipJ = buffin .readLine();
			String puertoJ = buffin.readLine();
			String nickJ = buffin.readLine();
			int clave = this.servidor.getListaJugadores().size();
			this.servidor.anadirJugadores(clave, ipJ, puertoJ, nickJ);
			//Le devolvemos al cliente la lista de jugadores actualizada.
			oout.writeObject(this.servidor.getListaJugadores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
