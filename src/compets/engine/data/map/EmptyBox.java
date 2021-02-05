package compets.engine.data.map;

import compets.engine.process.visitor.BoxVisitor;

/**
 * @author Maxence
 */
public class EmptyBox extends Box{

	public EmptyBox(Position position) {
		super(position);
	}

	@Override
	public <T> T accept(BoxVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
