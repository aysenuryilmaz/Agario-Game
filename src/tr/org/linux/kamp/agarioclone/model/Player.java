package tr.org.linux.kamp.agarioclone.model;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject{

	private int speed;
	private String playerName;
	private BufferedImage image;
	
	public Player(int x, int y, int radious,int speed, Color color,String playerName) {
		super(x, y, radious, color);
		this.speed=speed;
		this.playerName=playerName;
//		try {
//			image = ImageIO.read(getClass().getResource("/smile.JPG"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		//---resim eklemeden kullanılabilir olanlar----------------
		super.draw(g2d);
		FontMetrics fontmetrics = g2d.getFontMetrics(g2d.getFont());
		int width=fontmetrics.stringWidth(playerName);
		int nameX=getX()+(getRadious()-width)/2;
		int nameY=getY()-fontmetrics.getHeight();
		g2d.drawString(playerName, nameX, nameY);
		//---------------
		//resim ekledikten sonra gereken 
		//g2d.drawImage(image, getX(), getY(),getRadious(),getRadious(), null);
		
		
	}
	
}
