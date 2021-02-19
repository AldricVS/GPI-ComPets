package compets.engine.data.map;

/**
 * @author Maxence
 */
public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Compare with another Position and check if they are the same
	 * @param position the position to compare to
	 * @return true if they are the same, false otherwise
	 */
	public boolean equals(Position position) {
		return ((position.getX() == x) && (position.getY() == y));
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
