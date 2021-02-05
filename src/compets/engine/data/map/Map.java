package compets.engine.data.map;

/**
 * @author Maxence
 */
public class Map {

	private static Box map[][];
	private int rowCount;
	private int columnCount;
	
	public Map(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		map = new Box[rowCount][columnCount];
	}

	public Box[][] getMap() {
		return map;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

}
