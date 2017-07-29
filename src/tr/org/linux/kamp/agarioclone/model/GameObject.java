package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	private int x;
	private int y;
	private int radious;
	private Color color;
	
	
	public GameObject(int x, int y, int radious, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.radious = radious;
		this.color = color;
	}
	
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getRadious(), getRadious());
	}
	 
	public Rectangle getRectangle() {
		Rectangle rect=new Rectangle(getX(), getY(), getRadious(), getRadious());
		return rect;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadious() {
		return radious;
	}
	public void setRadious(int radious) {
		this.radious = radious;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
