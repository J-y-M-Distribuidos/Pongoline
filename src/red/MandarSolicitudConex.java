package red;

import java.io.*;
import java.net.*;

public class MandarSolicitudConex implements Runnable {

	private Cliente mi_cliente;
	private String ipExt;
	private int portExt;
	public MandarSolicitudConex(Cliente clientei, String ip, int puerto) {
		this.mi_cliente = clientei;
		this.ipExt = ip;
		this.portExt = puerto;
	}

	@Override
	public void run() {
		
		try (Socket s = new Socket(ipExt,portExt);
				BufferedReader buffIn  = new BufferedReader(new InputStreamReader(s.getInputStream()));
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());){
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

/*SOLO MANDA UNA SOLICITUD AL CLIENTE, Y SE QUEDA ESPERANDO A QUE LE RESPONDA.
SI LE RESPONDE ENTONCES YA ES CUANDO EMPEZAMOS A JUGAR. SINO ESPERA INDEFINIDAMENTE (cambiar esto ultimo)*/