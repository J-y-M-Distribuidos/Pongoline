package red;

/*En esta clase es donde decides a que cliente conectarte de la lista de clientes disponible.*/
public class ConectarConJugador implements Runnable {
	private Cliente mi_cliente;
	private String ipExt;
	private int portExt;

	public ConectarConJugador(Cliente clientei, String ip, int puerto) {
		this.mi_cliente = clientei;
		this.ipExt = ip;
		this.portExt = puerto;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
