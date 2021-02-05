package compets.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.Animal;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;
import prototype.engine.DogManager;

public class MainGui extends JFrame implements Runnable{
	private static final Dimension WINDOW_DIMENSION = new Dimension(GuiConfiguration.WINDOW_WIDTH, GuiConfiguration.WINDOW_HEIGHT);
	public static final Dimension MAIN_PANEL_DIMENSION = new Dimension(GuiConfiguration.WINDOW_WIDTH, GuiConfiguration.WINDOW_HEIGHT);

	private MainPanel mainPanel;

	private Animal animal = new Animal(new Position(7, 7));
	private Map map = new Map();
	private AnimalManager animalManager = new AnimalManager(animal, map);
	
	public MainGui() {
		super("Compet's");

		mainPanel = new MainPanel(this);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		
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

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
			animalManager.moveAnimal();
			repaint();
		}
	}
}
