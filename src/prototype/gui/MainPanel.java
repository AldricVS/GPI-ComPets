package prototype.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import prototype.engine.Dog;
import prototype.engine.DogManager;
import prototype.engine.Map;

public class MainPanel extends JPanel {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private static final Dimension PANEL_DIMENSION = new Dimension(WIDTH, HEIGHT);
	
	private MainGui context;
	private DogManager dogManager;
	private Map map;

	public MainPanel(MainGui context, Map map, DogManager dogManager) {
		this.context = context;
		this.map = map;
		this.dogManager = dogManager;
		setPreferredSize(PANEL_DIMENSION);
		setBackground(Color.black);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
//		int[][] mapArray = map.getMap();

		int[][] mapArray = { {0, 1, 2}, {2, 1, 0}, {1, 0, 0}};
		
		int boxWidth = WIDTH / mapArray.length;
		int boxHeight = HEIGHT / mapArray[0].length;
		
		for(int col = 0; col < mapArray.length; col++) {
			for(int line = 0; line < mapArray[col].length; line++) {
				int value = mapArray[col][line];

				switch(value) {
				case 0:
					g.setColor(Color.GRAY);
					break;
				case 1:
					g.setColor(new Color(20, 148, 20));
					break;
				case 2:
					g.setColor(new Color(216, 31, 42));
					break;
				}
				
				g.fillRect(line * boxWidth, col * boxHeight, boxWidth, boxHeight);
				g.setColor(Color.BLACK);
				g.drawRect(line * boxWidth - 1, col * boxHeight - 1, boxWidth - 1, boxHeight - 1);
			}
		}
		
//		Dog dog = dogManager.getDog();
//		int coordX = dog.getCoordX();
//		int coordY = dog.getCoordY();
		
		int coordX = 2;
		int coordY = 2;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(coordX * boxWidth, coordY * boxHeight, (coordX + 1) * boxWidth, (coordY + 1) * boxHeight);
		g2d.drawLine(coordX * boxWidth, (coordY + 1) * boxHeight, (coordX + 1) * boxWidth, coordY * boxHeight);
	}
	
	
}