package red;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

/*Aqui mendas la solicitud de conexion a uno de los clientes deisponibles de la lista de direcciones.*/
public class MandarSolicitudConex implements Runnable, Serializable {

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
		
		
				try (Socket s = new Socket(ipExt, portExt); //Manda datos en primera instancia
						BufferedReader buffIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
						ObjectOutputStream oout = new ObjectOutputStream(s.getOutputStream());) {

					ArrayList<String> misDatos = new ArrayList<String>();
					misDatos.add(mi_cliente.getip());
					misDatos.add(String.valueOf(mi_cliente.getPuerto()+7));
					misDatos.add(mi_cliente.getNick());

					oout.writeObject(misDatos);// Jugamos?
					pool.execute(new EsperarConexion(mi_cliente, pool));

				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			

	

	}

}

/*
 * SOLO MANDA UNA SOLICITUD AL CLIENTE, Y SE QUEDA ESPERANDO A QUE LE RESPONDA.
 * SI LE RESPONDE ENTONCES YA ES CUANDO EMPEZAMOS A JUGAR. SINO ESPERA 5min
 */