package compets.engine.data.map;

/**
 * A rectangle is a point with coordinates and a combo width / height
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class Rectangle {
	private Position position;
	private int width;
	private int height;

	public Rectangle(Position position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public Position getPosition() {
		return position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
