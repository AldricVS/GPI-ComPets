package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.Animal;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;

public class MainGui extends JFrame implements Runnable{
	private static final int TURN_DELAY = 3000;
	
	private static final Dimension WINDOW_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);
	public static final Dimension MAIN_PANEL_DIMENSION = new Dimension(GuiConfiguration.HEIGHT, GuiConfiguration.HEIGHT);
	
	public static final Dimension INFOS_PANEL_DIMENSION = new Dimension(
				(int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
				GuiConfiguration.HEIGHT
			);
	public static final Dimension STATS_PANEL_DIMENSION = new Dimension(
				(int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
				(int) (GuiConfiguration.HEIGHT * GuiConfiguration.STATS_PANEL_RATIO)
			);
	public static final Dimension BUTTONS_PANEL_DIMENSION = new Dimension(
				(int) (GuiConfiguration.WIDTH - MAIN_PANEL_DIMENSION.getWidth()),
				(int) (GuiConfiguration.HEIGHT * (1.0 - GuiConfiguration.STATS_PANEL_RATIO))
			);

	private MainPanel mainPanel;
	private InfosPanel infosPanel;

	private Animal animal = new Animal(new Position(7, 7));
	private Map map = new Map();
	private AnimalManager animalManager = new AnimalManager(animal, map);
	
	private boolean isPlaying = true;
	
	public MainGui() {
		super("Compet's");

		mainPanel = new MainPanel(this);
		infosPanel = new InfosPanel(this);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(infosPanel, BorderLayout.EAST);
		
		pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
		return animalManager.punish();
	}
	
	public boolean rewardAnimal() {
		return animalManager.reward();
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(TURN_DELAY);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
			if(isPlaying) {
				animalManager.moveAnimal();
				repaint();
			}
		}
	}
	
	
}
