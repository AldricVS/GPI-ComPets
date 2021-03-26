package compets.engine.data.animal;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.AnimalVisitor;

public class Fox extends Animal{

	private static final int defaultAction = 30;
	private static final int  defaultHealth = 30;
	
	public Fox(Position position) {
		super(position, new Behavior(defaultAction,defaultHealth));
	}

	@Override
	public String toString() {
		return "Fox [behavior=" + getBehavior() + ", position=" + getPosition() + ", state=" + getState() + "]";
	}

	@Override
	public <T> T accept(AnimalVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
