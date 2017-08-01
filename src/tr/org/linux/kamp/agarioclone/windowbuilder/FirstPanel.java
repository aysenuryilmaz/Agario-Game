package tr.org.linux.kamp.agarioclone.windowbuilder;

import javax.swing.JPanel;
import javax.swing.JButton;
import tr.org.linux.kamp.agarioclone.logic.GameLogic;
import tr.org.linux.kamp.agarioclone.model.Difficulty;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JPanel {
	private JTextField txtEnterYourName;
	private JPasswordField passwordField;
	
	private ButtonGroup buttonGroup;

	/**
	 * Create the panel.
	 */
	public FirstPanel() {
		
		JLabel lblNewLabel = new JLabel("User Name:");
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		
		txtEnterYourName = new JTextField();
		txtEnterYourName.setText("NAME");
		txtEnterYourName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setText("********");
		
		JButton btnStart = new JButton("START");
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("BLUE");
		comboBox.addItem("BLACK");
		comboBox.addItem("PINK");
		comboBox.addItem("ORANGE");

		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setSelected(true);
	
		JRadioButton rdbtnNormal = new JRadioButton("Normal");
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		btnStart.addActionListener(new ActionListener() {//butona bastığımda olucak şeyler
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				Color selectedColor = Color.BLACK;
				switch(comboBox.getSelectedItem().toString()) {
				case "BLUE":
					selectedColor=Color.BLUE;
					break;
				case "BLACK":
					selectedColor=Color.BLACK;
					break;
				case "PINK":
					selectedColor=Color.PINK;
					break;
				case "ORANGE":
					selectedColor=Color.ORANGE;
					break;
					default:
						break;
				}
				
				Difficulty difficulty = null;
				
				if(rdbtnEasy.isSelected()) {
					//EASY
					difficulty=Difficulty.EASY;
				}else if(rdbtnNormal.isSelected()) {
					//MEDIUM
					difficulty=Difficulty.NORMAL;
				}else if(rdbtnHard.isSelected()){
					//HARD
					difficulty=Difficulty.HARD;
				}
				
				GameLogic gameLogic =new GameLogic(txtEnterYourName.getText(), selectedColor,difficulty);
				gameLogic.startApplication();
			}
		});
		
		JButton btnAbout = new JButton("ABOUT");
		
		JLabel lblSelectColor = new JLabel("Select Color:");
		
		
		
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		
		
		buttonGroup=new ButtonGroup();
		buttonGroup.add(rdbtnEasy);
		buttonGroup.add(rdbtnNormal);
		buttonGroup.add(rdbtnHard);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtEnterYourName, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(passwordField)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblDifficulty)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnEasy)
										.addComponent(rdbtnNormal)
										.addComponent(rdbtnHard)
										.addComponent(btnStart)
										.addComponent(btnAbout)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSelectColor)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtEnterYourName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectColor, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDifficulty)
						.addComponent(rdbtnEasy, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNormal)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnHard)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAbout)
					.addGap(61))
		);
		setLayout(groupLayout);

		
	}
}
