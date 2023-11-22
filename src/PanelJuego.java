import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Este va a ser el lienzo donde pongamos las cosas
public class PanelJuego extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5625));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BOLA_DIMAETER = 20;
	static final int RAQUETA_WIDTH = 20;
	static final int RAQUETA_HEIGHT = 125;
	Thread juegoThread;
	Image image;
	Graphics graphics;
	Random random;
	Raqueta pala1;
	Raqueta pala2;
	Bola bola;
	Puntos puntos;

	public PanelJuego() {
		newPala();
		newBola();
		puntos = new Puntos(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); // Puede leer pulsaciones de teclas
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		juegoThread = new Thread(this);
		juegoThread.start();
	}

	// Crea una nueva bola en caso de que queramos resetear la actual.
	public void newBola() {
		random = new Random();
		bola = new Bola((GAME_WIDTH / 2) - (BOLA_DIMAETER / 2), (GAME_HEIGHT / 2) - (BOLA_DIMAETER / 2), BOLA_DIMAETER);
	}

	// Crea nuevas palas para los jugadores en caso de que queramos resetear las
	// actuales.
	public void newPala() {
		pala1 = new Raqueta(10, (GAME_HEIGHT / 2) - (RAQUETA_HEIGHT / 2), RAQUETA_WIDTH, RAQUETA_HEIGHT, 1);
		pala2 = new Raqueta((GAME_WIDTH - RAQUETA_WIDTH) - 10, (GAME_HEIGHT / 2) - (RAQUETA_HEIGHT / 2), RAQUETA_WIDTH,
				RAQUETA_HEIGHT, 2);

	}

	// Pinta cosas en pantalla
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	// dibuja en pantalla
	public void draw(Graphics g) {
		puntos.draw(g);
		pala1.draw(g);
		pala2.draw(g);
		bola.draw(g);
		
	}

	// Vamos a hacer que el movimiento de las palas no sea caca.
	// Asi se mueven tanto con el evento como con el move de raqueta --> dos inputs
	// de movimiento.
	public void move() {
		pala1.move();
		pala2.move();
		bola.move();
	}

	// Compureba la colisión de los elementos del juego
	// Previene que las palas se salgan de la pantalla.
	public void checkColision() {
		// Axis X
		if (pala1.x <= 0)
			pala1.x = 0;
		if (pala1.x >= (GAME_WIDTH - RAQUETA_WIDTH))
			pala1.x = GAME_WIDTH - RAQUETA_WIDTH;

		if (pala2.x <= 0)
			pala2.x = 0;
		if (pala2.x >= (GAME_WIDTH - RAQUETA_WIDTH))
			pala2.x = GAME_WIDTH - RAQUETA_WIDTH;
		if (bola.x <= 0) {
			bola.x = 0;
			bola.setXDirection(-bola.xVelocity);
		}
		if (bola.x>= (GAME_WIDTH - BOLA_DIMAETER)) {
			bola.x = GAME_WIDTH-BOLA_DIMAETER;
			bola.setXDirection(-bola.xVelocity);
		}
		// Axis Y
		if (pala1.y <= 0)
			pala1.y = 0;
		if (pala1.y >= (GAME_HEIGHT - RAQUETA_HEIGHT))
			pala1.y = GAME_HEIGHT - RAQUETA_HEIGHT;

		if (pala2.y <= 0)
			pala2.y = 0;
		if (pala2.y >= (GAME_HEIGHT - RAQUETA_HEIGHT))
			pala2.y = GAME_HEIGHT - RAQUETA_HEIGHT;
		if (bola.y <= 0) {
			bola.y = 0;
			bola.setYDirection(-bola.yVelocity);
		}
		if (bola.y>= (GAME_HEIGHT - BOLA_DIMAETER)) {
			bola.y = GAME_HEIGHT-BOLA_DIMAETER;
			bola.setYDirection(-bola.yVelocity);
		}
		//Bola choca con palas
		if(pala1.intersects(bola)) {
			bola.xVelocity = Math.abs(bola.xVelocity);
			bola.setXDirection(bola.xVelocity);
		}
		if(pala2.intersects(bola)) {
			bola.xVelocity = Math.abs(bola.xVelocity);
			bola.setXDirection(-bola.xVelocity);
		}
		//Bola nueva, cuando toca una pared y asigna puntos.
		if(bola.x == 0) {
			newBola();
			puntos.score(1);
		}
		if(bola.x == GAME_WIDTH-BOLA_DIMAETER) {
			newBola();
			puntos.score(2);
		}
	}

	// Runea el juego
	public void run() {
		// Bucle del juego.
		long lastTime = System.nanoTime();
		double numTicks = 120.0;
		double ns = 1000000000 / numTicks;
		double delta = 0;
		boolean funcionando = true;
		while (funcionando) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkColision();
				repaint();
				delta--;
				// System.out.println("hola");
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			pala1.keyPressed(e);
			pala2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			pala1.keyReleased(e);
			pala2.keyReleased(e);
		}
	}
}
