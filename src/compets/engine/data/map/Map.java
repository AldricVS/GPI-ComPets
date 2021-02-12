package compets.engine.data.map;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * @author Maxence
 */
public class Map {

	private Box map[][];
	private ArrayList<Prop> props = new ArrayList<Prop>();
	private int rowCount;
	private int columnCount;

	public Map(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.map = new Box[rowCount][columnCount];
	}

	public Map() {
		this(15, 15);
		Position position;
		// TODO créer une carte complète
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				position = new Position(row, column);
				setBoxOnMap(new EmptyBox(position), position);
			}
		}
		
		try {
			Image bedImage = loadImage("images/Bed.png");
			Image tableImage = loadImage("images/Table.png");
			Image cakeImage = loadImage("images/Cake.png");
			
			Rectangle rectangle = new Rectangle(new Position(6, 7), 3, 3);
			addGoodProp(rectangle, bedImage);
			
			rectangle = new Rectangle(new Position(2, 4), 3, 2);
			addNeutralProp(rectangle, tableImage);
			
			rectangle = new Rectangle(new Position(10, 10), 1, 1);
			addBadProp(rectangle, cakeImage);
			
			//Cake (bad item) at bottom
			position = new Position(10, 10);
			setBoxOnMap(new BadItem(position), position);
		}catch(IOException exception) {
			System.err.println("Cannot load map : " + exception.getMessage());
		}
		
		
		
		//walls arround map
		for(int line = 0; line < 15; line += 14) {
			for(int col = 0; col < 15; col++) {
				position = new Position(line, col);
				setBoxOnMap(new Wall(position), position);
			}
		}
		for(int col = 0; col < 15; col += 14) {
			for(int line = 0; line < 15; line++) {
				position = new Position(line, col);
				setBoxOnMap(new Wall(position), position);
			}
		}
	}
	
	private Image loadImage(String imagePath) throws IOException {
		File imageFile = new File(imagePath);
		if (!imageFile.exists()) {
			throw new FileNotFoundException(imagePath + " does not exists");
		}
		return ImageIO.read(imageFile);
	}
	
	public void addGoodProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new GoodItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addBadProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new BadItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addNeutralProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new NeutralItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}

	private void setBoxOnMap(Box box, Position position) {
		map[position.getX()][position.getY()] = box;
	}

	public Box[][] getMap() {
		return map;
	}

	public Box getBoxAtPosition(Position position) {
		return map[position.getX()][position.getY()];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public ArrayList<Prop> getProps() {
		return props;
	}
}
