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
		g.setColor(Color.WHITE);
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("Verdana",Font.BOLD,48));
		g.drawString(String.valueOf(jugador1 /10) +String.valueOf(jugador1 %10) , GAME_WIDTH/2 -85, GAME_HEIGHT*1/8);
		g.setColor(Color.YELLOW);
		g.drawString(String.valueOf(jugador2 /10) +String.valueOf(jugador2 %10) , GAME_WIDTH/2 +15, GAME_HEIGHT*1/8);

	}

	// Añade un punto al jugador que se le pasa por parametro
	public void score(int jugador) {
		if(jugador == 1) 
			jugador1++;
		else jugador2++;
	}
}
	