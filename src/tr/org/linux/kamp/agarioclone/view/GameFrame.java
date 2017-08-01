package tr.org.linux.kamp.agarioclone.view;
import java.awt.Color;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame() {//constructor
		setTitle("Agario Clone");//başlığını belirledik
		setBackground(Color.DARK_GRAY);
		setResizable(true);//boyutlanabilirliğini ayarladık 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//KAPATTIĞIM ZAMAN sistemde çıksın
		setSize(1200,800);//ekran boyutu
		setLocationRelativeTo(null); //??
		setVisible(true);
		
	}
}
