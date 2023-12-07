package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EscucharInvitaciones implements Runnable {

	private Cliente cliente;

	public EscucharInvitaciones(Cliente c) {
		this.cliente = c;
	}

	@SuppressWarnings("unchecked")//Porque lo he hecho yo vaya.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (ServerSocket serverC = new ServerSocket(cliente.getPuerto());) {
			while (true) {
				try (Socket s = serverC.accept();
						BufferedReader buffin = new BufferedReader(new InputStreamReader(s.getInputStream()));
						ObjectInputStream oin = new ObjectInputStream(s.getInputStream());) {
					
					cliente.add_lista_invitaciones((ArrayList<String>)oin.readObject());
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
