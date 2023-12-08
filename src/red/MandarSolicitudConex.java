package red;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
/*Aqui mendas la solicitud de conexion a uno de los clientes deisponibles de la lista de direcciones.*/
public class MandarSolicitudConex implements Runnable {

	private Cliente mi_cliente;
	private String ipExt;
	private int portExt;
	private static ExecutorService pool;

	public MandarSolicitudConex(Cliente clientei, String ip, int puerto, ExecutorService poolHilos) {
		this.mi_cliente = clientei;
		this.ipExt = ip;
		this.portExt = puerto;
		pool = poolHilos;
	}

	@Override
	public void run() {

		try (Socket s = new Socket(ipExt, portExt);
				BufferedReader buffIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
				ObjectOutputStream oout = new ObjectOutputStream(s.getOutputStream());) {
			ArrayList<String> misDatos = new ArrayList<String>();
			misDatos.add(mi_cliente.getip());
			misDatos.add(String.valueOf(mi_cliente.getPuerto()));
			misDatos.add(mi_cliente.getNick());
			
			oout.writeObject(misDatos);//Jugamos?
			

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {// Espera 5 minutos a que me acepte, sino me voy.

				@Override
				public void run() {
					System.out.println("No quiere jugar conmigo, me voy :(");
					System.exit(0);

				}
			}, 5 * 60 * 100);
			
			String res = buffIn.readLine();
			if (res == "Si") {
				timer.cancel();
				EnviarDatosJuego mandar = new EnviarDatosJuego(s);
				RecibirDatosJuego recibir = new RecibirDatosJuego(s);
				pool.execute(mandar);
				pool.execute(recibir);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

/*
 * SOLO MANDA UNA SOLICITUD AL CLIENTE, Y SE QUEDA ESPERANDO A QUE LE RESPONDA.
 * SI LE RESPONDE ENTONCES YA ES CUANDO EMPEZAMOS A JUGAR. SINO ESPERA
 * 5min
 */