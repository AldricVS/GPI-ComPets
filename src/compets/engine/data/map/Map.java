package compets.engine.data.map;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

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

		// bed (neutral item) at center
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				position = new Position(i + 6, j + 6);
				setBoxOnMap(new NeutralItem(position), position);
			}
		}
		
		//table (good item) at top
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				position = new Position(i + 2, j + 4);
				setBoxOnMap(new GoodItem(position), position);
			}
		}
		
		//Cake (bad item) at bottom
		position = new Position(10, 10);
		setBoxOnMap(new BadItem(position), position);
		
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
			
		// add corresponding props
		try {
			Rectangle rectangle = new Rectangle(new Position(6, 6), 2, 2);
			Prop prop = new Prop("images/Bed.png", rectangle);
			props.add(prop);
			
			rectangle = new Rectangle(new Position(2, 4), 3, 2);
			prop = new Prop("images/Table.png", rectangle);
			props.add(prop);
			
			rectangle = new Rectangle(new Position(10, 10), 1, 1);
			prop = new Prop("images/Cake.png", rectangle);
			props.add(prop);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addGoodProp(Rectangle rectangle, Image image) throws IOException {
		Position position = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				position = new Position(line + position.getY(), col + position.getX());
				setBoxOnMap(new GoodItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addBadProp(Rectangle rectangle, Image image) throws IOException {
		Position position = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				position = new Position(line + position.getY(), col + position.getX());
				setBoxOnMap(new BadItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addNeutralProp(Rectangle rectangle, Image image) throws IOException {
		Position position = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				position = new Position(line + position.getY(), col + position.getX());
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
