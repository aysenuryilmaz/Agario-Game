package tr.org.linux.kamp.agarioclone.logic;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import tr.org.linux.kamp.agarioclone.model.Chip;
import tr.org.linux.kamp.agarioclone.model.Difficulty;
import tr.org.linux.kamp.agarioclone.model.Enemy;
import tr.org.linux.kamp.agarioclone.model.GameObject;
import tr.org.linux.kamp.agarioclone.model.Mine;
import tr.org.linux.kamp.agarioclone.model.Player;
import tr.org.linux.kamp.agarioclone.view.GameFrame;
import tr.org.linux.kamp.agarioclone.view.GamePanel;

/**
 * 
 * @author aysenur
 * @version1.0
 *
 */

public class GameLogic {

	private Player player;
	private ArrayList<GameObject> gameObjects;
	// chips that will be removed from the screen
	private ArrayList<GameObject> chipsToRemove;
	// mine that will be removed from the screen
	private ArrayList<GameObject> minesToRemove;
	// enemies that will be removed from the screen
	private ArrayList<GameObject> enemiesToRemove;

	private GameFrame gameFrame;
	private GamePanel gamePanel;

	private int xTarget;
	private int yTarget;

	private Random random ;
	//oyun çalışıyor mu kontrolü için boolean bir değer tuttuk
	private boolean isGameRunning = true;

	public GameLogic(String playerName,Color selectedColor,Difficulty difficulty) {
		player = new Player(10, 10, 20, 5, selectedColor,playerName);

		gameObjects = new ArrayList<GameObject>();
		chipsToRemove = new ArrayList<GameObject>();
		minesToRemove = new ArrayList<GameObject>();
		enemiesToRemove = new ArrayList<GameObject>();

		gameObjects.add(player);

		gameFrame = new GameFrame();
		gamePanel = new GamePanel(gameObjects);
		gamePanel.setSize(640, 480);

		random= new Random();
		
		fillMines(10);
		fillChips(25);
		fillEnemies(10);

		addMouseEvents();
	}

	private synchronized void checkCollision() {

		for (GameObject gameObject : gameObjects) {
			// instead of just a collision check,
			// we want to check if the object completely contain the other object
			// if (player.getRectangle().intersects(gameObject.getRectangle())) {//
			if (player.getRectangle().intersects(gameObject.getRectangle())) {
				if (gameObject instanceof Chip) {// kesişen şey Chip mi diye bakıyorum
					player.setRadious(player.getRadious() + gameObject.getRadious());
					// --arraylist oluşturmaya gerek kalmadan da bu şekilde yapılabilir-----
					// gameObject.setX(1000);
					// gameObject.setY(1000);
					chipsToRemove.add(gameObject);
				}
				
				if (gameObject instanceof Mine) {
					player.setRadious((int) player.getRadious() / 2);
					minesToRemove.add(gameObject);
				}
				if(gameObject instanceof Enemy) {
					if(player.getRadious()>gameObject.getRadious()) {
						player.setRadious(player.getRadious()+gameObject.getRadious());
						enemiesToRemove.add(gameObject);
					}else if(player.getRadious()<gameObject.getRadious()) {
						gameObject.setRadious(gameObject.getRadious()+player.getRadious());
						//GameOver
						isGameRunning=false;
					}
				}
			}
			/**
			 * Enemy objelerine de kendi aralarında yem yeme, mayına çarptığında yanması özelliklerini verebilmek için if koşulunu oluşturduk
			 */
			if(gameObject instanceof Enemy) {
				Enemy enemy =(Enemy) gameObject;
				
				for (GameObject gameObject2 : gameObjects) {
					if(enemy.getRectangle().intersects(gameObject2.getRectangle())) {
						if(gameObject2 instanceof Chip) {
							enemy.setRadious(enemy.getRadious()+gameObject2.getRadious());
							chipsToRemove.add(gameObject2);
						}
						if(gameObject2 instanceof Mine) {
							enemy.setRadious((int)enemy.getRadious()/2);
							minesToRemove.add(gameObject2);
						}
					}
				}
			}
		}
		// loop is completed remove the objects
		gameObjects.removeAll(chipsToRemove);
		gameObjects.removeAll(minesToRemove);
		gameObjects.removeAll(enemiesToRemove);
	}

	/**
	 * addNewObject methodunda yenilen chip kadar yeni obje oluşturuyoruz ve yenilen objeleri ortadan kaldırıyoruz.
	 */
	private synchronized void addNewObjects() {
		fillChips(chipsToRemove.size());
		chipsToRemove.clear();

		fillMines(minesToRemove.size());
		minesToRemove.clear();

		fillEnemies(enemiesToRemove.size());
		enemiesToRemove.clear();
	}

	/**
	 * Mouse konumunu algılayarak player objesinin hareketini sağlamak için oluşturulan bir method.
	 */
	private synchronized void movePlayer() {
		if (xTarget > player.getX()) {
			player.setX(player.getX() + player.getSpeed());
		} else if (xTarget < player.getX()) {
			player.setX(player.getX() - player.getSpeed());
		}

		if (yTarget > player.getY()) {
			player.setY(player.getY() + player.getSpeed());
		} else if (yTarget < player.getY()) {
			player.setY(player.getY() - player.getSpeed());
		}
	}

	/**
	 * Enemy objesinin Player objesinden büyük ya da küçük olma durumuna göre Enemy objesinin konumunu(nereye gideceğini) ve yapacağı davranışları belirliyoruz.
	 */
	private synchronized void moveEnemies() {
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof Enemy) {
				Enemy enemy=(Enemy)gameObject;//enemy türünde gameobject cast ettik
				if (enemy.getRadious() < player.getRadious()) {
					// kaçacak
					int distance=(int)Point.distance(player.getX(), player.getY(), enemy.getX(), enemy.getY());
					
					int newX=enemy.getX()+enemy.getSpeed();
					int newY=enemy.getY()+enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy,distance,newX,newY)){
						continue;
					}
					
					newX=enemy.getX()+enemy.getSpeed();
					newY=enemy.getY()-enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy,distance,newX,newY)) {
						continue;
					}
					
					newX=enemy.getX()-enemy.getSpeed();
					newY=enemy.getY()+enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy,distance,newX,newY)) {
						continue;
					}
					
					newX=enemy.getX()-enemy.getSpeed();
					newY=enemy.getY()-enemy.getSpeed();
					if(calculateNewDistanceToEnemy(enemy,distance,newX,newY)) {
						continue;
					}
					
					
				} else {
					// Yiyecek
					if (player.getX() > enemy.getX()) {
						enemy.setX(enemy.getX() + enemy.getSpeed());
					} else if (player.getX() < enemy.getX()) {
						enemy.setX(enemy.getX() - enemy.getSpeed());
					}

					if (player.getY() > enemy.getY()) {
						enemy.setY(enemy.getY() + enemy.getSpeed());
					} else if (player.getX() < enemy.getY()) {
						enemy.setY(enemy.getY() - enemy.getSpeed());
					}
				}
			}
		}
	}

	/**
	 * Enemy objesi için Player'ın konumuna göre yeni konumunu ayarlıyoruz.
	 */
	private boolean calculateNewDistanceToEnemy(Enemy enemy,int distance,int x, int y) {
		int newDistance= (int) Point.distance(player.getX(), player.getY(), x, y);
		if(newDistance > distance) {
			enemy.setX(x);
			enemy.setY(y);
			return true;
		}
		return false;
	}
	
	/**
	 * fillChips methodunda verilen ekran boyutu içinde yem oluşmasını sağlıyoruz.
	 * @param n kaç tane yem(chip)oluşturmak istediğimiz değer.
	 */
	private synchronized void fillChips(int n) {
		for (int i = 0; i < n; i++) {
			int x=random.nextInt(gamePanel.getWidth());
			int y=random.nextInt(gamePanel.getHeight());
			if(x>=gamePanel.getWidth()) {
				x-=15;
			}
			if(y>=gamePanel.getHeight()) {
				y-=15;
			}
			gameObjects.add(new Chip(x,y, 10, Color.magenta));
		}

	}

	/**
	 * 
	 *fillMines methodunda verilen ekran boyutu içinde yem oluşmasını sağlıyoruz.
	 * @param n kaç tane mayın(Mine)oluşturmak istediğimiz değer.
	 */
	
	private synchronized void fillMines(int n) {
		for (int i = 0; i < n; i++) {

			int x = random.nextInt(gamePanel.getWidth());
			int y = random.nextInt(gamePanel.getHeight());
			if (x >= gamePanel.getWidth()) {
				x -= 15;
			}
			if (y >= gamePanel.getHeight()) {
				y -= 15;
			}

			Mine mine = new Mine(x, y, 20, Color.GREEN);
			/**
			 * oluşturulan mayın objesi player objesinin üzerine oluşturulmasın diye kullanılan bir koşul.
			 */
			while (player.getRectangle().intersects(mine.getRectangle())) {
				x = random.nextInt(gamePanel.getWidth());
				y = random.nextInt(gamePanel.getHeight());
				if (x >= gamePanel.getWidth()) {
					x -= 15;
				}
				if (y >= gamePanel.getHeight()) {
					y -= 15;
				}
				mine.setX(x);
				mine.setY(y);
			}

			gameObjects.add(mine);
		}
	}

	/**
	 * 
	 *fillEnemies methodunda verilen ekran boyutu(panel) içinde yem oluşmasını sağlıyoruz.
	 * @param n kaç tane düşman(Enemies)oluşturmak istediğimiz değer.
	 */
	
	private void fillEnemies(int n) {
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(gamePanel.getWidth());
			int y = random.nextInt(gamePanel.getHeight());
			if (x >= gamePanel.getWidth()) {
				x -= 15;
			}
			if (y >= gamePanel.getHeight()) {
				y -= 15;
			}
			
			/**
			 * oluşturulan düşman objesi player objesinin üzerine oluşturulmasın diye kullanılan bir koşul.
			 */
			
			Enemy enemy = new Enemy(x, y, (random.nextInt(10) + 25), 1, Color.RED);
			while (player.getRectangle().intersects(enemy.getRectangle())) {
				x = random.nextInt(gamePanel.getWidth());
				y = random.nextInt(gamePanel.getHeight());
				if (x >= gamePanel.getWidth()) {
					x -= 15;
				}
				if (y >= gamePanel.getHeight()) {
					y -= 15;
				}
				enemy.setX(x);
				enemy.setY(y);
			}

			gameObjects.add(enemy);
		}
	}

	private void startGame() { // oyun döngümüzü başlattık
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (isGameRunning) {

					movePlayer();
					moveEnemies();
					checkCollision();
					addNewObjects();
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
		isGameRunning = true;
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

		/**
		 * mouse hareketini algılayan javanın kendi içinde bulunan method.
		 */
		
		gameFrame.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				xTarget = e.getX();
				yTarget = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
	}
}
