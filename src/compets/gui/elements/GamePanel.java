package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;
import compets.engine.process.AnimalStateHandler;

public class GamePanel extends JPanel implements Runnable{

	private static final int AFTER_ACTION_DELAY = 3000;
	private static final int AFTER_MOVE_DELAY = 1000;

	private static final Dimension WINDOW_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);
	public static final Dimension MAP_PANEL_DIMENSION = new Dimension(GuiConfiguration.HEIGHT - GuiConfiguration.MENU_HEIGHT, GuiConfiguration.HEIGHT);

	public static final Dimension INFOS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAP_PANEL_DIMENSION.getWidth()),
			GuiConfiguration.HEIGHT);
	public static final Dimension STATS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAP_PANEL_DIMENSION.getWidth()),
			(int) (GuiConfiguration.HEIGHT * GuiConfiguration.STATS_PANEL_RATIO));
	public static final Dimension BUTTONS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAP_PANEL_DIMENSION.getWidth()),
			(int) (GuiConfiguration.HEIGHT * (1.0 - GuiConfiguration.STATS_PANEL_RATIO)));

	private MainGui mainGui;
	
	private MapPanel mapPanel;
	private InfosPanel infosPanel;

	private Animal animal;
	private Map map;
	private AnimalManager animalManager;
	private AnimalStateHandler animalStateHandler;

	private boolean isGameRunning = true;
	private boolean isPlaying = true;
	
	public GamePanel(MainGui context) {
		super();
		mainGui = context;
		mapPanel = new MapPanel(this);
	}

	public void newGame() {
		animal = new Dog(new Position(6, 11));
		map = new Map();
		animalManager = new AnimalManager(animal, map);
		animalStateHandler = new AnimalStateHandler(animal);
		infosPanel = new InfosPanel(this);
		setLayout(new BorderLayout());
		add(mapPanel, BorderLayout.CENTER);
		add(infosPanel, BorderLayout.EAST);
		initMenuBar();
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuGame = new JMenu("Menu");
		
		JMenuItem itemSaveGame = new JMenuItem("Save game");
		// CTRL-S shortcut
		itemSaveGame.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK) );
		menuGame.add(itemSaveGame);
		
		JMenuItem itemReturnToMenu = new JMenuItem("Return to main menu");
		itemReturnToMenu.addActionListener(new ActionReturnToMenu());
		itemReturnToMenu.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK) );
		menuGame.add(itemReturnToMenu);
		
		JMenuItem itemQuitGame = new JMenuItem("Quit game");
		itemQuitGame.addActionListener(new ActionQuitGame());
		itemQuitGame.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK) );
		menuGame.add(itemQuitGame);
		
		JMenu menuHelp = new JMenu("Help");
		JMenuItem itemHelp = new JMenuItem("Display help");
		itemHelp.addActionListener(new ActionHelp());
		itemHelp.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK) );
		menuHelp.add(itemHelp);
		
		menuBar.add(menuGame);
		menuBar.add(menuHelp);
		
		this.add(menuBar, BorderLayout.NORTH);
	}
	
	public AnimalManager getAnimalManager() {
		return animalManager;
	}
	
	public AnimalStateHandler getAnimalStateHandler() {
		return animalStateHandler;
	}

	public Map getMap() {
		return map;
	}

	public void pause() {
		isPlaying = false;
	}

	public void play() {
		isPlaying = true;
	}

	public boolean punishAnimal() {
		infosPanel.setButtonsEnabled(false);
		return animalManager.punish();
	}

	public boolean rewardAnimal() {
		infosPanel.setButtonsEnabled(false);
		return animalManager.reward();
	}
	
	@Override
	public void run() {
		int delay = AFTER_MOVE_DELAY;
		while (isGameRunning) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isPlaying) {
				infosPanel.setButtonsEnabled(true);
				animalManager.doSomething();
				// If the animal is doing something, the delay will be longer before the next
				// turn (in order to let user do something)
				AnimalState state = animal.getState();
				if (state == AnimalState.NEUTRAL) {
					delay = AFTER_MOVE_DELAY;
				} else {
					delay = AFTER_ACTION_DELAY;
				}
				delay = updateDelay(delay);
				infosPanel.updateMessage();
				repaint();
			}
		}
	}

	private int updateDelay(int delay) {
		// apply a boost of speed depending of the health of the animal
		int healthValue = animal.getBehavior().getHealthGauge().getValue();
		//speed modifier goes from -2 to 4
		int speedModifier = (healthValue/20)-2;
		//pourcent speed goes from 80 to 140
		int pourcentSpeed = (100 + (10 * speedModifier));
		//apply pourcent speed to the delay
		delay = (delay * 100) / pourcentSpeed;
		return delay;
	}
	
	class ActionSaveGame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class ActionHelp implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Pause the game
			isPlaying = false;
			JEditorPane jep = new JEditorPane();
			jep.setEditable(false);
			try {
				jep.setPage("file:data/regles.html");
				JScrollPane scrollPane = new JScrollPane(jep);
				scrollPane.setMaximumSize(MenuPanel.MENU_DIMENSION);
				scrollPane.setPreferredSize(new Dimension(MenuPanel.MENU_DIMENSION.width, 2 * MenuPanel.MENU_DIMENSION.height / 3));
				JOptionPane.showMessageDialog(GamePanel.this, scrollPane);
			} catch (IOException exception) {	
				JOptionPane.showMessageDialog(GamePanel.this, "Impossible d'afficher l'aide (données non trouvées)", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			isPlaying = true;
		}
	}
	
	class ActionQuitGame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	class ActionReturnToMenu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int answer = JOptionPane.showConfirmDialog(GamePanel.this, 
					"Do you really want to return to the menu ? All progress non saved will be lost.", 
					"Return to menu ?", 
					JOptionPane.YES_NO_OPTION
				);
			if(answer == JOptionPane.YES_OPTION) {
				isPlaying = false;
				isGameRunning = false;
				mainGui.switchToMenuPanel();
			}
		}
	}
}