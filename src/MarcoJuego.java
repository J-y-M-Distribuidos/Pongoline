import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
//Esto es el marco alrededor del juego
public class MarcoJuego extends JFrame{

	PanelJuego panel;
	
	public MarcoJuego() {
		panel = new PanelJuego();
		this.add(panel);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setBackground(Color.gray);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();	//Asi se ajusta automaticamente al panel.
		this.setVisible(true);
		this.setLocationRelativeTo(null);	//Asi aparece en el centro
	}
}
