package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 200;
	private static final Dimension PANEL_DIMENSION = new Dimension(WIDTH, HEIGHT);
	
	private MainGui context;
	
	private JButton punishButton = new JButton("Punish");
	private JButton rewardButton = new JButton("Reward");

	public ButtonsPanel(MainGui context) {
		this.context = context;
		setLayout(new GridLayout(0, 2));
		setPreferredSize(PANEL_DIMENSION);
		add(rewardButton);
		add(punishButton);
	}
	
	class ActionPunish implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			context.punishDog();
		}
	}
	
	class ActionReward implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			context.rewardDog();
		}
	}
}
