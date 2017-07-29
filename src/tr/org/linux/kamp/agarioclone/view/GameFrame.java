package tr.org.linux.kamp.agarioclone.view;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("Agario Clone");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//KAPATTIĞIM ZAMAN sistemde çıksın
		setSize(640,480);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
