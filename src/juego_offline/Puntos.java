package juego_offline;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Puntos extends Rectangle {

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int jugador1;
	int jugador2;

	public Puntos(int width, int height) {
		jugador1 = 0;
		jugador2 = 0;
		GAME_HEIGHT = height;
		GAME_WIDTH = width;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.setFont(new Font("Comic sans",Font.PLAIN,60));
		
        Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{25}, 30);
        g2d.setStroke(dashed);
        g2d.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        g2d.setColor(Color.MAGENTA);
		g2d.drawString(String.valueOf(jugador2/10)+String.valueOf(jugador2%10), (GAME_WIDTH/2)-85, 50);
		g2d.setColor(Color.YELLOW);
		g2d.drawString(String.valueOf(jugador1/10)+String.valueOf(jugador1%10), (GAME_WIDTH/2)+20, 50);

	}

	// Añade un punto al jugador que se le pasa por parametro
	public void score(int jugador) {
		if(jugador == 1) 
			jugador1++;
		else jugador2++;
	}
}
	