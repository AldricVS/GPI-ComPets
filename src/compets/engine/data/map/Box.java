package compets.engine.data.map;

import compets.engine.process.visitor.BoxVisitor;

/**
 * @author Maxence
 */
public abstract class Box {

	private Position position;
	
	public Box(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public abstract <T> T accept(BoxVisitor<T> visitor);
	
}
