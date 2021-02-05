package compets.engine.data.map;

/**
 * @author Maxence
 */
public class Map {

	private int map[][];
	private int rowCount;
	private int columnCount;
	
	public Map(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.map = new int[rowCount][columnCount];
	}

	public int[][] getMap() {
		return map;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

}
