package compets.gui.elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel{

	private InfosPanel infosPanel;
	
	private static final Dimension BUTTON_DIMENSION = new Dimension(
				(int) (MainGui.BUTTONS_PANEL_DIMENSION.getWidth() - MainGui.BUTTONS_PANEL_DIMENSION.getWidth() / 4),
				(int) (MainGui.BUTTONS_PANEL_DIMENSION.getHeight() - MainGui.BUTTONS_PANEL_DIMENSION.getHeight() / 4)
			);
	private static final int PADDING_WIDTH = (int) (MainGui.BUTTONS_PANEL_DIMENSION.getWidth() / 10);
	private static final int PADDING_HEIGHT = (int) (MainGui.BUTTONS_PANEL_DIMENSION.getHeight() / 10);
	private static final Font FONT = new Font("arial", Font.BOLD, PADDING_HEIGHT);
	
	private JButton punishButton = new JButton("Punish");
	private JButton rewardButton = new JButton("Reward");

	public ButtonsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		GridLayout gridLayout = new GridLayout(2, 1);
		gridLayout.setHgap(PADDING_WIDTH);
		gridLayout.setVgap(PADDING_HEIGHT);
		
		setLayout(gridLayout);
		setBorder(BorderFactory.createEmptyBorder(PADDING_HEIGHT, PADDING_WIDTH, PADDING_HEIGHT, PADDING_WIDTH));
		setPreferredSize(MainGui.BUTTONS_PANEL_DIMENSION);
		setBackground(Color.GRAY);
		
		initButton(rewardButton);
		initButton(punishButton);
		punishButton.addActionListener(new ActionPunish());
		rewardButton.addActionListener(new ActionReward());
		
		initFont();
	}
	
	private void initButton(JButton button) {
		button.setPreferredSize(BUTTON_DIMENSION);
		button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(button);
	}
	
	private void initFont() {
		for(Component component : getComponents()) {
			component.setFont(FONT);
		}
	}
	
	class ActionReward implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			MainGui context = infosPanel.getContext();
			context.pause();
			if(context.rewardAnimal()) {
				message = "Animal is happy to have done a good thing";
			}else {
				message = "That was not a good thing to reward the animal";
			}
			JOptionPane.showMessageDialog(context, message);
			context.play();
		}
	}
	
	class ActionPunish implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			MainGui context = infosPanel.getContext();
			context.pause();
			if(context.punishAnimal()) {
				message = "He understood that it was a bad thing";
			}else {
				message = "That was not a good thing to punish the animal";
			}
			JOptionPane.showMessageDialog(context, message);
			context.play();
		}
	}
}
