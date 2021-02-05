package compets.engine.data.map;

import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;

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
				switch ((row + column)%5) {
				default:
				case 1:
				case 2:
					map[row][column] = new EmptyBox(new Position(row, column));
					break;
				case 3:
					map[row][column] = new Wall(new Position(row, column));
					break;
				case 4:
					map[row][column] = new GoodItem(new Position(row, column));
					break;
				case 5:
					map[row][column] = new BadItem(new Position(row, column));
					break;
				}
			}
		}
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
