package compets.gui.elements;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public void newGame() {
		switchToGamePanel();
		GameManager gameManager = GameInitializer.createNewGame();
		gamePanel.initGame(gameManager);
	}
	
	public void loadGame() {
		switchToGamePanel();
		
	}

	public void switchToGamePanel() {
		cardLayout.removeLayoutComponent(gamePanel);
		gamePanel = new GamePanel(this);
		getContentPane().add(gamePanel, GAME_WINDOW_NAME);
		cardLayout.show(this.getContentPane(), GAME_WINDOW_NAME);
		gamePanel.newGame();
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
