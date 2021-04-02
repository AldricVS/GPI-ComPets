package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import compets.engine.data.animal.AnimalState;
import compets.engine.data.animal.AnimalType;
import compets.engine.data.behavior.BehaviorStatesEnum;
import compets.engine.process.animal.AnimalStateHandler;
import compets.gui.management.MessagesRepository;
import compets.gui.management.animal_drawing.AnimalImageUtility;

public class ActionsPanel extends JPanel{

	private static final int ICON_SIDE_LENGTH = 64;

	private InfosPanel infosPanel;
	
	private AnimalStateHandler animalStateHandler;
	
	private static final Dimension BUTTON_DIMENSION = new Dimension(
				(int) (GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() - GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() / 4 / 2),
				(int) (GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() - GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() / 4)
			);
	private static final int PADDING_WIDTH = (int) (GamePanel.BUTTONS_PANEL_DIMENSION.getWidth() / 10);
	private static final int PADDING_HEIGHT = (int) (GamePanel.BUTTONS_PANEL_DIMENSION.getHeight() / 10);
	private static final Font FONT = new Font("arial", Font.BOLD, 2 * PADDING_HEIGHT / 3);
	
	private JButton punishButton = new JButton();
	private JButton rewardButton = new JButton();
	
	private JPanel buttonsPanel = new JPanel();
	private JPanel messagesPanel = new JPanel();
	private JLabel messageLabel;
	
	private MessagesRepository messagesRepository;
	private ResourceBundle resources;
	
	private Icon badActionIcon;
	private Icon goodActionIcon;

	public ActionsPanel(InfosPanel infosPanel, AnimalImageUtility animalImageUtility) {
		super();
		this.infosPanel = infosPanel;
		
		initIcons(animalImageUtility);
		
		resources = infosPanel.getContext().getResources();
		messagesRepository = new MessagesRepository(resources);
		animalStateHandler = infosPanel.getContext().getAnimalStateHandler();
		setLayout(new GridLayout(1, 2));
		initMessagesPanel();
		initButtonsPanel();
		//initFont();
	}

	private void initIcons(AnimalImageUtility animalImageUtility) {
		// Scale images for being small icons
		Image badActionImage = animalImageUtility.getCorrespondingImage(AnimalState.BAD_ACTION);
		Image badActionResized = badActionImage.getScaledInstance(ICON_SIDE_LENGTH, ICON_SIDE_LENGTH, Image.SCALE_SMOOTH);
		Image goodActionImage = animalImageUtility.getCorrespondingImage(AnimalState.GOOD_ACTION);
		Image goodActionResized = goodActionImage.getScaledInstance(ICON_SIDE_LENGTH, ICON_SIDE_LENGTH, Image.SCALE_SMOOTH);
		// Retrieve images for pop ups
		badActionIcon = new ImageIcon(badActionResized);
		goodActionIcon = new ImageIcon(goodActionResized);
	}
	
	private void initButtonsPanel() {
		punishButton.setText(resources.getString("button_punish"));
		rewardButton.setText(resources.getString("button_reward"));
		
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
		messagesPanel.add(messageLabel, BorderLayout.CENTER);
		add(messagesPanel);
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
			Icon iconToUse;
			GamePanel context = infosPanel.getContext();
			context.pause();
			if(context.rewardAnimal()) {
				message = resources.getString("user_reward_right");
				iconToUse = goodActionIcon;
			}else {
				message = resources.getString("user_reward_wrong");
				iconToUse = badActionIcon;
			}
			JOptionPane.showMessageDialog(ActionsPanel.this, message, "", JOptionPane.INFORMATION_MESSAGE, iconToUse);
			context.play();
		}
	}
	
	class ActionPunish implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			Icon iconToUse;
			GamePanel context = infosPanel.getContext();
			context.pause();
			if(context.punishAnimal()) {
				message = resources.getString("user_punish_right");
				iconToUse = goodActionIcon;
			}else {
				message = resources.getString("user_punish_wrong");
				iconToUse = badActionIcon;
			}
			JOptionPane.showMessageDialog(ActionsPanel.this, message, "", JOptionPane.INFORMATION_MESSAGE, iconToUse);
			context.play();
		}
	}

	public void updateMessage() {
		BehaviorStatesEnum messageState = animalStateHandler.checkState();
		String message = messagesRepository.getAnimalStateMessage(messageState);
		messageLabel.setText(message);
	}
}
