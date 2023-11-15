import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Este va a ser el lienzo donde pongamos las cosas
public class PanelJuego extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5625));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIMAETER = 20;
	static final int RAQUETA_WIDTH = 25;
	static final int RAQUETA_HEIGHT = 100;
	Thread juegoThread;
	Image image;
	Graphics graphics;
	Random random;
	Raqueta raqueta1;
	Raqueta raqueta2;
	Bola bola;
	Puntos puntos;

	public PanelJuego() {
		newPala();
		newBola();
		Puntos puntos= new Puntos(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);	//Puede leer pulsaciones de teclas
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		juegoThread = new Thread(this);
		juegoThread.start();
	}

	// Crea una nueva bola en caso de que queramos resetear la actual.
	public void newBola() {

	}

	// Crea nuevas palas para los jugadores en caso de que queramos resetear las
	// actuales.
	public void newPala() {
		
	}

	// Pinta cosas en pantalla
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}

	public void draw(Graphics g) {

	}

	// Mueve cosas
	public void move() {

	}

	// Compureba la colisión de los elementos del juego
	public void checkColision() {

	}

	// Runea el juego
	public void run() {

	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

		}

		public void keyReleased(KeyEvent e) {

		}
	}
}
