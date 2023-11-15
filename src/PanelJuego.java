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
		pala1 = new Raqueta(10,(GAME_HEIGHT/2)-(RAQUETA_HEIGHT/2),RAQUETA_WIDTH,RAQUETA_HEIGHT,1);
		pala2 = new Raqueta((GAME_WIDTH-RAQUETA_WIDTH)-10,(GAME_HEIGHT/2)-(RAQUETA_HEIGHT/2),RAQUETA_WIDTH,RAQUETA_HEIGHT,2);
	
	}

	// Pinta cosas en pantalla
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}

	public void draw(Graphics g) {
		pala1.draw(g);
		pala2.draw(g);
	}

	// Mueve cosas
	public void move() {

	}

	// Compureba la colisión de los elementos del juego
	public void checkColision() {

	}

	// Runea el juego
	public void run() {
		//Bucle del juego.
		long lastTime = System.nanoTime();
		double numTicks = 60.0;
		double ns = 1000000000 / numTicks;
		double delta = 0;
		boolean funcionando = true;
		while(funcionando) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				move();
				checkColision();
				repaint();
				delta--;
				//System.out.println("hola");
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {

		}

		public void keyReleased(KeyEvent e) {

		}
	}
}
