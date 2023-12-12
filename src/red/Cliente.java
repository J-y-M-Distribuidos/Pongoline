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

	private static HashMap<Integer, List<String>> listaJugadores = new HashMap<Integer, List<String>>();
	private static HashMap<Integer, List<String>> listaInvitaciones = new HashMap<Integer, List<String>>();
	private static String ip;
	private static int miPuerto = 8888;
	private Boolean disponible = true;
	private static String nickn;

	public static void main(String[] args) {
		Cliente c = new Cliente();
		// TODO Auto-generated method stub
		try (Socket s = new Socket(InetAddress.getLocalHost(), 1313);
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				ObjectInputStream ooin = new ObjectInputStream(s.getInputStream());) {
			Scanner scanner = new Scanner(System.in);
			ip = InetAddress.getLocalHost().toString().split("/")[1];

			System.out.println("Escribe tu nickname: ");
			nickn = scanner.nextLine();
			conectarse_y_listaJ(nickn, dout, ooin);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Despues de recibir la lista y elegir un jugador, esperamos a poder
		// conectarnos/recibir
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new EscucharInvitaciones(c));
		menu(pool, c);
	}

	// PRE: Las variables estaticas ip y miPuerto ya tienen un valor asignado.
	// POS: Establece una conexión con el servidor y recibe una lista de los
	// jugadores conectados.
	// La excepcion IO es gestionada fuera del metodo, en el main.
	private static void conectarse_y_listaJ(String nick, DataOutputStream dout, ObjectInputStream ooin)
			throws IOException {
		// dout.writeBytes(ip + "\n");
		dout.writeBytes(miPuerto + "\n");
		dout.writeBytes(nick + "\n");
		try {
			System.out.println("Conexion correcta: "
					+ ((listaJugadores = (HashMap<Integer, List<String>>) ooin.readObject()) != null));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void menu(ExecutorService pool, Cliente c) {
		Boolean salir = false;

		while (!salir) {
			System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(
					"[ 0 ] -  MOSTRAR LISTA JUGADORES DISPONIBLES \n[ 1 ] -  MOSTRAR LISTA INVITACIONES \n[ 2 ] -  MANDAR SOLICITUD\n[ 3 ] -  ACEPTAR sOLICITUD\n[4 - 9] -  SALIR");
			System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
			Scanner scan = new Scanner(System.in);

			switch (scan.nextInt()) {
			case 0:
				mostrar_lista_jugadores();
				break;
			case 1:
				mostrar_lista_invitaciones();
				break;
			case 2:// MandarSolicitud
				System.out.println("Numero del jugador: ");
				int n2 = scan.nextInt();
				int puertoJ2 = Integer.parseInt(listaJugadores.get(n2).get(1));
				String ipJ2 = listaJugadores.get(n2).get(0);
				MandarSolicitudConex solicitud2 = new MandarSolicitudConex(c, ipJ2, puertoJ2, pool);
				pool.execute(solicitud2);
				salir = true;
				break;
			case 3:// AceptarSolicitud
				System.out.println("Numero del jugador: ");
				int n3 = scan.nextInt();
				int puertoJ3 = Integer.parseInt(listaInvitaciones.get(n3).get(1));
				String ipJ3 = listaInvitaciones.get(n3).get(0);
				ConectarConJugador concectar3 = new ConectarConJugador(c, ipJ3, puertoJ3, pool);
				pool.execute(concectar3);
				salir = true;
				break;
			default:
				System.exit(0);
			}
		}

	}

	private static void mostrar_lista_jugadores() {
		System.out.println("_________________________________________________\n    LISTA DE JUGADORES CONECTADOS");
		if (listaJugadores != null) {
			for (Entry<Integer, List<String>> entry : listaJugadores.entrySet()) {
				System.out.println(entry.getKey() + " -- " + entry.getValue().get(2).toString());
			}
			System.out.println("_________________________________________________\n");
		} else
			System.out.println("No hay jugadores conectados.");
	}

	private static void mostrar_lista_invitaciones() {
		System.out.println("_________________________________________________\n    LISTA DE SOLICITUDES");
		if (listaInvitaciones != null) {
			for (Entry<Integer, List<String>> entry : listaInvitaciones.entrySet()) {
				System.out.println(entry.getKey() + " -- " + entry.getValue().get(2).toString());
			}
		} else
			System.out.println("No hay invitaciones disponibles");
		System.out.println("_________________________________________________\n");
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

	public String getNick() {
		return nickn;
	}

	public void add_lista_invitaciones(String ip, String puerto, String nick) {
		ArrayList<String> nuevaInvi = new ArrayList<String>();
		nuevaInvi.add(ip);
		nuevaInvi.add(puerto);
		nuevaInvi.add(nick);
		listaInvitaciones.put(listaInvitaciones.size(), nuevaInvi);
	}

	public void add_lista_invitaciones(ArrayList<String> datos) {

		listaInvitaciones.put(listaInvitaciones.size(), datos);
	}

}
/*
 * 1º Tiene que conectarse al servidor y recibir la lista de jugadores. 2ª
 * Muestra por pantalla la opcion de ver la lista o la opcion de conectarse a un
 * jugador de la lista. 3º Le manda una peticion de solicitud al jugador. 4º Si
 * mientras esta conectado recibe una peticion de conexion tambien lo guardara
 * en una lista
 */
