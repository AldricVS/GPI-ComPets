package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.Animal;
import compets.engine.data.map.Box;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;
import compets.engine.process.AnimalManager;
import compets.gui.ColorConstants;

public class MainPanel extends JPanel {

	private MainGui context;
	
	private int boxWidth;
	private int boxHeight;

	public MainPanel(MainGui context) {
		this.context = context;
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.MAIN_PANEL_DIMENSION);
		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Map map = context.getMap();
		Box[][] mapArray = map.getMap();

		
		int numberOfLines = mapArray.length;
		int numberOfColumns = mapArray[0].length;
		boxWidth = getWidth() / numberOfLines;
		boxHeight = getHeight() / numberOfColumns;

		drawBoxes(g, mapArray);
		drawGrid(g);
		drawAnimal(g);

	}

	private void drawBoxes(Graphics g, Box[][] mapArray) {
		int numberOfLines = mapArray.length;
		int numberOfColumns = mapArray[0].length;
		for (int col = 0; col < numberOfColumns; col++) {
			for (int line = 0; line < numberOfLines; line++) {
				Box currentBox = mapArray[col][line];
				Position position = currentBox.getPosition();
				Color boxColor;
				if (currentBox instanceof EmptyBox) {
					boxColor = ColorConstants.EMPTY_BOX_COLOR;
				} else if (currentBox instanceof BadItem) {
					boxColor = ColorConstants.BAD_BOX_COLOR;
				} else if (currentBox instanceof GoodItem) {
					boxColor = ColorConstants.GOOD_BOX_COLOR;
				} else if (currentBox instanceof NeutralItem) {
					boxColor = ColorConstants.NEUTRAL_BOX_COLOR;
				} else if (currentBox instanceof Wall) {
					boxColor = ColorConstants.WALL_BOX_COLOR;
				} else {
					boxColor = Color.white;
				}
				g.setColor(boxColor);
				g.fillRect(position.getX() * boxWidth, position.getY() * boxHeight, boxWidth, boxHeight);
			}
		}
	}

	private void drawGrid(Graphics g) {
		Map map = context.getMap();
		Box[][] mapArray = map.getMap();
		int numberOfLines = mapArray.length;
		int numberOfColumns = mapArray[0].length;
		g.setColor(ColorConstants.GRID_COLOR);
		int panelHeight = MainGui.MAIN_PANEL_DIMENSION.height;
		for (int col = 0; col < numberOfColumns; col++) {
			int posX = col * boxWidth;
			g.drawLine(posX, 0, posX, panelHeight);
		}
		
		int panelWidth = MainGui.MAIN_PANEL_DIMENSION.width;
		for (int line = 0; line < numberOfLines; line++) {
			int posY = line * boxHeight;
			g.drawLine(0, posY, panelWidth, posY);
		}
	}

	private void drawAnimal(Graphics g) {
		AnimalManager animalManager = context.getAnimalManager();
		Animal animal = animalManager.getAnimal();
		Position position = animal.getPosition();
		g.setColor(ColorConstants.ANIMAL_COLOR);
		g.fillOval(position.getX() * boxWidth, position.getY() * boxHeight, boxWidth, boxHeight);
	}
}
