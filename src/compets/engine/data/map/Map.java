package compets.engine.data.map;

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
