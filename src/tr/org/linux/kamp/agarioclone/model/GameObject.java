package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * @author aysenur
 * @version 1.0
 * A generic abstract class that can produce an enemy,a mine, a chip, aa player.
 *
 */

public abstract class GameObject {
	private int x;
	private int y;
	private int radious;
	private Color color;
	
	/**
	 * 
	 * @param x , x coordinate
	 * @param y , y coordinate
	 * @param radious , radious
	 * @param color , color of the gameobjects
	 */
	
	public GameObject(int x, int y, int radious, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.radious = radious;
		this.color = color;
	}
	
	
	/**
	 * A drawing method to draw with setting color and filling via fields
	 * @param g2d Using for setting color and filling oval
	 */
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getRadious(), getRadious());
	}
	 
	/**
	 * Getrectangle method to sclae the collision between player and the other Gameobjects
	 * @return A rectangle objects with fields x, y, radious
	 */
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
