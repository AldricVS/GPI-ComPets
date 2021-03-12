package compets.gui.elements;

import java.awt.CardLayout;

import javax.swing.JFrame;

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

	public void switchToGamePanel() {
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
