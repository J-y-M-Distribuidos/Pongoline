import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Raqueta extends Rectangle {

	int id;// 1 o 2 para los jugadores
	int yVelocity;

	/*
	 * Posicion x, Posicion y, Ancho, Alto e Identificador
	 */
	public Raqueta(int x, int y, int width, int height, int id) {
		super(x,y,width,height);
		this.id = id;
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	// Las palas solo se mueven de arriba a abajo
	public void setYDirection(int yDirection) {

	}

	public void move() {

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
