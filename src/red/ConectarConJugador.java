package red;

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
	public void run() {
		// TODO Auto-generated method stub

	}

}
