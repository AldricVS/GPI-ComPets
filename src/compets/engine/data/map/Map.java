package compets.engine.data.map;

import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * @author Maxence
 */
public class Map {

	private Box map[][];
	private int rowCount;
	private int columnCount;
	
	public Map(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.map = new Box[rowCount][columnCount];
	}
	
	public Map() {
		this(15,  15);
		// TODO créer une carte complète
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				Position position = new Position(row, column);
				switch ((row + column)%6) {
				default:
				case 1:
					setBoxOnMap(new NeutralItem(position), position);
				case 2:
					setBoxOnMap(new EmptyBox(position), position);
					break;
				case 3:
					setBoxOnMap(new GoodItem(position), position);
					break;
				case 4:
					setBoxOnMap(new Wall(position), position);
					break;
				case 5:
					setBoxOnMap(new BadItem(position), position);
					break;
				}
			}
		}
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

}
