package juego_offline;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
//Esto es el marco alrededor del juego
public class MarcoJuego extends JFrame{

	PanelJuego panel;
	DataOutputStream aver;
	public MarcoJuego(DataOutputStream aver1) {
		aver = aver1;
		panel = new PanelJuego(aver1);
		this.add(panel);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();	//Asi se ajusta automaticamente al panel.
		this.setVisible(true);
		this.setLocationRelativeTo(null);	//Asi aparece en el centro
	}
}
/*TODO
 * Para que las palas se reajusten automaticamente a su lado si cambiamos el tamaño de la ventana,
 * tenemos que mirar si se activa el evento resize y cuando se active volver a dibujar las palas
 * con newPala. Estas NO se van a "redibujar" con run, solo se van a "repintar"*/