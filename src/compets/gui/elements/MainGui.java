package compets.gui.elements;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import compets.engine.data.animal.AnimalType;
import compets.engine.process.GameInitializer;
import compets.engine.process.GameManager;

public class MainGui extends JFrame{
	private static final String GAME_WINDOW_NAME = "game";
	private static final String MENU_WINDOW_NAME = "menu";
	
	private GamePanel gamePanel = new GamePanel(this);
	private MenuPanel menuPanel = new MenuPanel(this);
	
	private CardLayout cardLayout = new CardLayout();
	
	public MainGui() {
		getContentPane().setLayout(cardLayout);
		getContentPane().add(gamePanel, GAME_WINDOW_NAME);
		getContentPane().add(menuPanel, MENU_WINDOW_NAME);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		switchToMenuPanel();
	}
	
	public void newGame(AnimalType animalType) {
		switchToGamePanel();
		GameManager gameManager = GameInitializer.createNewGame(animalType);
		gamePanel.initGame(gameManager);
	}
	
	public void loadGame() {
		// Try to get gameManager with save file
		// if null, cannot have it
		try {
			GameManager gameManager = GameInitializer.loadGame();
			switchToGamePanel();
			gamePanel.initGame(gameManager);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void switchToGamePanel() {
		getContentPane().remove(gamePanel);
		gamePanel = new GamePanel(this);
		getContentPane().add(gamePanel, GAME_WINDOW_NAME);
		cardLayout.show(this.getContentPane(), GAME_WINDOW_NAME);
		Thread gameThread = new Thread(gamePanel);
		gameThread.start();
		pack();
		setLocationRelativeTo(null);
	}
	
	public void switchToMenuPanel() {
		cardLayout.show(this.getContentPane(), MENU_WINDOW_NAME);
		pack();
		setLocationRelativeTo(null);
	}
}
