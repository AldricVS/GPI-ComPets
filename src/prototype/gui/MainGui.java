package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import prototype.engine.Dog;
import prototype.engine.DogManager;
import prototype.engine.Map;

public class MainGui extends JFrame implements Runnable {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private static final Dimension WINDOW_DIMENSION = new Dimension(WIDTH, HEIGHT);

	private static final int TIME_BETWEEN_TURNS = 3000;

	private MainPanel mainPanel;
	private ButtonsPanel statsPanel;

	private DogManager dogManager;
	private Dog dog = new Dog(1, 1);
	private Map map = new Map();

	private boolean isPlaying = true;

	private boolean actionAlreadyDone = false;

	public MainGui() {
		super("Prototype ComPet's");
		dogManager = new DogManager(dog, map);
		mainPanel = new MainPanel(this, map, dogManager);
		statsPanel = new ButtonsPanel(this);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setPreferredSize(WINDOW_DIMENSION);

		getContentPane().add(mainPanel, BorderLayout.NORTH);
		getContentPane().add(statsPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Thread thread = new Thread(this);
		thread.run();
	}

	@Override
	public void run() {
		while (isPlaying) {
			actionAlreadyDone = false;
			// move dog
			dogManager.moveDog();
			mainPanel.repaint();
			try {
				Thread.sleep(TIME_BETWEEN_TURNS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void punishDog() {
		if (!actionAlreadyDone) {
			boolean isActionGood = dogManager.punish();
			String dialogString;
			if (isActionGood) {
				dialogString = "The dog has been punished, he will understand that it is not right";
			} else {
				dialogString = "The dog doesn't deserve a punition";
			}
			actionAlreadyDone = true;
			JOptionPane.showMessageDialog(this, dialogString);
			
		}
	}

	public void rewardDog() {
		if (!actionAlreadyDone) {
			boolean isActionGood = dogManager.reward();
			String dialogString;
			if (isActionGood) {
				dialogString = "The dog received the reward and is happier";
			} else {
				dialogString = "The dog was messing around, giving him a reward wasn't really a good idea ...";
			}
			actionAlreadyDone = true;
			JOptionPane.showMessageDialog(this, dialogString);
			
		}
	}
}
