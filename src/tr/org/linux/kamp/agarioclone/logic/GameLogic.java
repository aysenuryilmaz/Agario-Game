package tr.org.linux.kamp.agarioclone.logic;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agarioclone.model.Chip;
import tr.org.linux.kamp.agarioclone.model.GameObject;
import tr.org.linux.kamp.agarioclone.model.Player;
import tr.org.linux.kamp.agarioclone.view.GameFrame;
import tr.org.linux.kamp.agarioclone.view.GamePanel;

public class GameLogic {
	private Player player;
	private ArrayList<GameObject>gameObjects;
	
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	
	private int xTarget;
	private int yTarget;
	
	private Random random=new  Random();
	
	private boolean isGameRunning=false;//oyun çalışıyormu diye boolean
	
	public GameLogic() {
		player= new Player(10,10,20,3,Color.DARK_GRAY);
		gameObjects=new ArrayList<GameObject>();
		gameObjects.add(player);
		
		gameFrame=new GameFrame();
		gamePanel= new GamePanel(gameObjects);
		
		fillChips(15);
		addMouseEvents();
	}
	
	public void checkCollision() {
		ArrayList<GameObject>objectsToRemove=new ArrayList<GameObject>();
		for (GameObject gameObject : gameObjects) {//game objesini başan sona dolaşıyoruz
			if(player.getRectangle().intersects(gameObject.getRectangle())) {//kesişiyor mu diye bakıyoruz
				if(gameObject instanceof Chip) {//kesişen şey Chip mi diye bakıyorum
					player.setRadious(player.getRadious()+gameObject.getRadious());
					//--arraylist oluşturmaya gerek kalmadan da bu şekilde yapılabilir-----
					//gameObject.setX(1000);
					//gameObject.setY(1000);
					objectsToRemove.add(gameObject);
				}
			}
		}
		gameObjects.removeAll(objectsToRemove);
		fillChips(objectsToRemove.size());
	}

	private void movePlayer() {
		if(xTarget>player.getX()) {
			player.setX(player.getX()+player.getSpeed());
		}else if(xTarget<player.getX()){
			player.setX(player.getX()-player.getSpeed());
		}
		
		if(yTarget>player.getY()) {
			player.setY(player.getY()+player.getSpeed());
		}else if(xTarget<player.getY()){
			player.setY(player.getY()-player.getSpeed());
		}
	}
	
	private void fillChips(int n) {
		for(int i=0;i<n;i++) {
			gameObjects.add(new Chip(random.nextInt(1000),random.nextInt(1000), 10, Color.ORANGE));
		}
		
	}
	
	private void startGame() { //oyun döngümüzü başlattık
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(isGameRunning)	{
					movePlayer();
					checkCollision();
					gamePanel.repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();					
					}
				}
			}
		}).start();
	}
	
	public void startApplication() {
		gameFrame.setContentPane(gamePanel);
		gameFrame.setVisible(true);
		isGameRunning=true;
		startGame();
	}
	
	
	public void addMouseEvents() {
		gameFrame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
								
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		gameFrame.addMouseMotionListener(new MouseMotionListener() {//bir dosyayı tutup taşıdığım işlem örneğin
			
			@Override
			public void mouseMoved(MouseEvent e) {
				xTarget=e.getX();
				yTarget=e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
								
			}
		});
	}
}
