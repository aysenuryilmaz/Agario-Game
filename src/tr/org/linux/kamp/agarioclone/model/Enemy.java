package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;

public class Enemy extends GameObject {
	private int speed; 
	
	public Enemy(int x, int y, int radious,int speed ,Color color) {
		super(x, y, radious, color);
		this.speed=speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
}
