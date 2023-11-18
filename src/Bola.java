import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math;
public class Bola extends Rectangle{
	Random random;
	int xVelocity;
	int yVelocity;
	int velocidad = 4;
	
	public Bola(int x, int y, int diametro) {
		super(x,y,diametro,diametro);
		random = new Random();
		int randXDir = random.nextInt(2);// 0 o 1
		int randYDir = random.nextInt(2);// 0 o 1
		if(randXDir == 0) {
			randXDir --;
		}
		setXDirection(randXDir);
		
		if(randYDir == 0) {
			randYDir --;
		}
		setYDirection(randYDir);
	}
	
	public void setXDirection(int randXDir) {
		xVelocity = randXDir ;
	}
	
	public void setYDirection(int randYDir) {
		yVelocity = randYDir ;
	}
	
	//x e y sonatributos de la clase padre rectangulo. Se ve mejor con this.x/this.y
	public void move() {
		if(Math.abs(xVelocity) != velocidad  || Math.abs(yVelocity) != velocidad) {
			x += velocidad * xVelocity;
			y += velocidad * yVelocity;
		}else {
			x += xVelocity;
			y += yVelocity;
		}
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, height, width);
		
	}
}
