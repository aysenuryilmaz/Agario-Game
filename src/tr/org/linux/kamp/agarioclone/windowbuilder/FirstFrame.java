package tr.org.linux.kamp.agarioclone.windowbuilder;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class FirstFrame extends JFrame {

	private FirstPanel contentPane;

	/**
	 * Create the frame.//constructor
	 */
	public FirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new FirstPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("LKD 2017 JAVA");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FirstFrame frame = new FirstFrame();
		//frame.pack();
		frame.setVisible(true);
	
	}



}
