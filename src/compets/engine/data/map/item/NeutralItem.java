package compets.engine.data.map.item;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.BoxVisitor;

/**
 * @author Maxence
 */
public class NeutralItem extends Item {

	public NeutralItem(Position position) {
		super(position);
	}

	@Override
	public <T> T accept(BoxVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
