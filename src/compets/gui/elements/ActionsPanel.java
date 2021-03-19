package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import compets.engine.data.behavior.BehaviorStatesEnum;
import compets.engine.process.AnimalStateHandler;
import compets.gui.management.MessagesRepository;

public class ActionsPanel extends JPanel{

	private InfosPanel infosPanel;
	
	private AnimalStateHandler animalStateHandler;
	
	private static final Dimension BUTTON_DIMENSION = new Dimension(
				(int) (GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() - GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() / 4 / 2),
				(int) (GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() - GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() / 4)
			);
	private static final int PADDING_WIDTH = (int) (GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() / 10);
	private static final int PADDING_HEIGHT = (int) (GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() / 10);
	private static final Font FONT = new Font("arial", Font.BOLD, PADDING_HEIGHT);
	
	private JButton punishButton = new JButton("Punish");
	private JButton rewardButton = new JButton("Reward");
	
	private JPanel buttonsPanel = new JPanel();
	private JPanel messagesPanel = new JPanel();
	private JLabel messageLabel;
	
	private MessagesRepository messagesRepository = new MessagesRepository();

	public ActionsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		animalStateHandler = infosPanel.getContext().getAnimalStateHandler();
		setLayout(new GridLayout(1, 2));
		initMessagesPanel();
		initButtonsPanel();
		//initFont();
	}
	
	private void initButtonsPanel() {
		GridLayout gridLayout = new GridLayout(2, 1);
		gridLayout.setHgap(PADDING_WIDTH);
		gridLayout.setVgap(PADDING_HEIGHT);
		
		buttonsPanel.setLayout(gridLayout);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(PADDING_HEIGHT * 2, PADDING_WIDTH, PADDING_HEIGHT, PADDING_WIDTH));
		buttonsPanel.setPreferredSize(GamePanel.BUTTONS_PANEL_DIMENSION);
		buttonsPanel.setBackground(Color.GRAY);
		initButton(rewardButton);
		initButton(punishButton);
		punishButton.addActionListener(new ActionPunish());
		rewardButton.addActionListener(new ActionReward());
		add(buttonsPanel);
	}

	private void initMessagesPanel() {
		messagesPanel.setLayout(new BorderLayout());
		messagesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel titleLabel = new JLabel("Animal state :", SwingConstants.CENTER);
		messagesPanel.add(titleLabel, BorderLayout.NORTH);
		messageLabel = new JLabel("", SwingConstants.CENTER);
		messageLabel.setFont(new Font("Arial", Font.PLAIN, GamePanel.BUTTONS_PANEL_DIMENSION.width / 30));
		changeMessageLabel("Nothing to say...", false);
		messagesPanel.add(messageLabel, BorderLayout.CENTER);
		add(messagesPanel);
	}
	
	public void changeMessageLabel(String newMessage, boolean isBadMessage) {
		if(isBadMessage) {
			messageLabel.setText("<html><p style=\"color:red;\">" + newMessage + "</p></html>");
		}else {
			messageLabel.setText("<html><p>" + newMessage + "</p></html>");
		}
		
	}

	public void setButtonsEnabled(boolean enabled) {
		punishButton.setEnabled(enabled);
		rewardButton.setEnabled(enabled);
	}
	
	private void initButton(JButton button) {
		button.setPreferredSize(BUTTON_DIMENSION);
		button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		button.setFont(FONT);
		buttonsPanel.add(button);
	}
	
	class ActionReward implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			GamePanel context = infosPanel.getContext();
			context.pause();
			if(context.rewardAnimal()) {
				message = "Animal is happy to have done a good thing";
			}else {
				message = "That was not a good thing to reward the animal";
			}
			JOptionPane.showMessageDialog(ActionsPanel.this, message);
			context.play();
		}
	}
	
	class ActionPunish implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			GamePanel context = infosPanel.getContext();
			context.pause();
			if(context.punishAnimal()) {
				message = "He understood that it was a bad thing";
			}else {
				message = "That was not a good thing to punish the animal";
			}
			JOptionPane.showMessageDialog(ActionsPanel.this, message);
			context.play();
		}
	}

	public void updateMessage() {
		BehaviorStatesEnum messageState = animalStateHandler.checkState();
		String message = messagesRepository.getAnimalStateMessage(messageState);
		messageLabel.setText(message);
	}
}
