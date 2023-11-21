import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Raqueta extends Rectangle {

	int id;// 1 o 2 para los jugadores
	int yVelocity;
	int xVelocity;
	int velocidad = 5;
	/*
	 * Posicion x, Posicion y, Ancho, Alto e Identificador
	 */
	public Raqueta(int x, int y, int width, int height, int id) {
		super(x,y,width,height);
		this.id = id;
	}

	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			//Si pulsamos la W...
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-velocidad);
				move();
			}
			//Si pulsamos la S...
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(velocidad);
				move();
			}
			//Si pulsamos la A...
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(-velocidad);
				move();
			}
			//Si pulsamos la D...
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(velocidad);
				move();
			}
			break;
		case 2:
			//Si pulsamos la UP...
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-velocidad);
				move();
			}
			//Si pulsamos la DOWN...
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(velocidad);
				move();
			}
			//Si pulsamos la LEFT...
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(-velocidad);
				move();
			}
			//Si pulsamos la RIGHT...
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(velocidad);
				move();
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			//Si soltamos la W...
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			//Si soltamos la S...
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			//Si soltamos la A...
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(0);
				move();
			}
			//Si soltamos la D...
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(0);
				move();
			}
			break;
		case 2:
			//Si soltamos la UP...
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			//Si soltamos la DOWN...
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			//Si soltamos la LEFT...
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(0);
				move();
			}
			//Si soltamos la RIGHT...
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(0);
				move();
			}
			break;
		}
	}

	// Las palas solo se mueven de arriba a abajo
	public void setYDirection(int yDirection) {
		this.yVelocity = yDirection;
	}
	
	//O no... eheh
	public void setXDirection(int xDirection) {
		this.xVelocity= xDirection;
	}
	//Mueve las palas una direccion
	public void move() {
		this.x += xVelocity;
		this.y += yVelocity;
	}

	public void draw(Graphics g) {
		if(id==1) {
			g.setColor(Color.MAGENTA);
		}else {
			g.setColor(Color.YELLOW);
		}
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	}
	
