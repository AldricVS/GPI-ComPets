package prototype.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import prototype.engine.Dog;
import prototype.engine.DogManager;
import prototype.engine.Map;

public class MainGui extends JFrame implements Runnable{
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private static final Dimension WINDOW_DIMENSION = new Dimension(WIDTH, HEIGHT);
	
	private static final int TIME_BETWEEN_TURNS = 3000;
	
	private MainPanel mainPanel;
	private ButtonsPanel statsPanel;
	
	private DogManager dogManager;
	private Dog dog = new Dog(0, 0);
	private Map map;
	
	private boolean isPlaying = true;
	
	public MainGui() {
		super("Prototype ComPet's");
//		dogManager = new DogManager(dog, map);
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
		while(isPlaying) {
			//move dog
			mainPanel.repaint();
			try {
				Thread.sleep(TIME_BETWEEN_TURNS);
			} catch (InterruptedException e) {	
				e.printStackTrace();
			}
		}
	}
	
	public void punishDog() {
		boolean isActionGood = dogManager.punish();
		String dialogString;
		if(isActionGood) {
			dialogString = "The dog has been punished, he will understand that it is not right";
		}else {
			dialogString = "The dog doesn't deserve a punition";
		}
		JOptionPane.showMessageDialog(this, dialogString);
	}
	
	public void rewardDog() {
		boolean isActionGood = dogManager.reward();
		String dialogString;
		if(isActionGood) {
			dialogString = "The dog received the reward and is happier";
		}else {
			dialogString = "The dog was messing around, giving him a reward wasn't really a good idea ...";
		}
		JOptionPane.showMessageDialog(this, dialogString);
	}
}
