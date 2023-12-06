package red;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {

	private static HashMap<Integer, List<String>> listaJugadores = null;
	private static HashMap<Integer, List<String>> listaInvitaciones = null;
	private static String ip;
	private static int miPuerto = 8888;
	private Boolean disponible = true;

	public static void main(String[] args) {
		Cliente c = new Cliente();
		// TODO Auto-generated method stub
		try (Socket s = new Socket(InetAddress.getLocalHost(), 1313);
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				ObjectInputStream ooin = new ObjectInputStream(s.getInputStream());) {
			Scanner scanner = new Scanner(System.in);
			ip = InetAddress.getLocalHost().toString().split("/")[1];

			System.out.println("Escribe tu nickname: ");
			String nickn = scanner.nextLine();
			conectarse_y_listaJ(nickn, dout, ooin);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Despues de recibir la lista y elegir un jugador, esperamos a poder conectarnos/recibir
		ExecutorService pool = Executors.newCachedThreadPool();
		MandarSolicitudConex esperar = new MandarSolicitudConex(c);
		ConectarConJugador conectar = new ConectarConJugador(c);
		pool.execute(conectar);
		pool.execute(esperar);
	}

	// PRE: Las variables estaticas ip y miPuerto ya tienen un valor asignado.
	// POS: Establece una conexión con el servidor y recibe una lista de los jugadores conectados.
	//La excepcion IO es gestionada fuera del metodo, en el main.
	private static void conectarse_y_listaJ(String nick, DataOutputStream dout, ObjectInputStream ooin) throws IOException {
		//dout.writeBytes(ip + "\n");
		dout.writeBytes(miPuerto + "\n");
		dout.writeBytes(nick + "\n");
		try {
			System.out.println("Conexion correcta: " + ((listaJugadores = (HashMap<Integer, List<String>>) ooin.readObject() )!= null));
			 mostrar_lista_jugadores();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mostrar_lista_jugadores() {
		System.out.println("_________________________________________________\n    LISTA DE JUGADORES CONECTADOS\n_________________________________________________");
		if(listaJugadores != null) {
			for (Entry<Integer, List<String>> entry : listaJugadores.entrySet()) {
	            System.out.println(entry.getKey() + " -- " + entry.getValue().get(2).toString());
	        }
		}else System.out.println("No hay jugadores conectados.");	
	}
	public Boolean getDisponible() {
		return this.disponible;
	}
	public void setDisponible(Boolean disponibilidad) {
		this.disponible = disponibilidad;
	}
	public int getPuerto() {
		return miPuerto;
	}
	public String getip() {
		return ip;
	}

}
/*
 * 1º Tiene que conectarse al servidor y recibir la lista de jugadores. 2ª
 * Muestra por pantalla la opcion de ver la lista o la opcion de conectarse a un
 * jugador de la lista. 3º Le manda una peticion de solicitud al jugador. 4º Si
 * mientras esta conectado recibe una peticion de conexion tambien lo guardara
 * en una lista
 */
