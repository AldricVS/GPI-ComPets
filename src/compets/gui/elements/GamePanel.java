package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;

public class GamePanel extends JPanel implements Runnable{

	private static final int AFTER_ACTION_DELAY = 3000;
	private static final int AFTER_MOVE_DELAY = 1000;

	private static final Dimension WINDOW_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);
	public static final Dimension MAIN_PANEL_DIMENSION = new Dimension(GuiConfiguration.HEIGHT, GuiConfiguration.HEIGHT);

	public static final Dimension INFOS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
			GuiConfiguration.HEIGHT);
	public static final Dimension STATS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
			(int) (GuiConfiguration.HEIGHT * GuiConfiguration.STATS_PANEL_RATIO));
	public static final Dimension BUTTONS_PANEL_DIMENSION = new Dimension((int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
			(int) (GuiConfiguration.HEIGHT * (1.0 - GuiConfiguration.STATS_PANEL_RATIO)));

	private MainGui mainGui;
	
	private MapPanel mainPanel;
	private InfosPanel infosPanel;

	private Animal animal;
	private Map map;
	private AnimalManager animalManager;

	private boolean isPlaying = true;
	
	private boolean isAnimalInGoodShape = true;

	public GamePanel(MainGui context) {
		super();
		mainGui = context;
		mainPanel = new MapPanel(this);
	}
	
	public void newGame() {
		animal = new Animal(new Position(6, 11));
		map = new Map();
		animalManager = new AnimalManager(animal, map);
		infosPanel = new InfosPanel(this);
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		add(infosPanel, BorderLayout.EAST);
	}

	public AnimalManager getAnimalManager() {
		return animalManager;
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
		while (true) {
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
				checkGauges();
				repaint();
			}
		}
	}

	private void checkGauges() {
		Gauge healthGauge = animal.getBehavior().getHealthGauge();
		if(healthGauge.getValue() < 5 && isAnimalInGoodShape) {
			isAnimalInGoodShape = false;
			JOptionPane.showMessageDialog(this, "The animal is in a really bad shape, you really should be nicer with him !");
		}
	}
}