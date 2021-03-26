package compets.engine.data.animal;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.AnimalVisitor;

public class Cat extends Animal {
	
	private static final int defaultAction = 30;
	private static final int  defaultHealth = 80;
	
	public Cat(Position position) {
		super(position, new Behavior(defaultAction,defaultHealth));
	}

	@Override
	public String toString() {
		return "Cat [behavior=" + getBehavior() + ", position=" + getPosition() + ", state=" + getState() + "]";
	}

	@Override
	public <T> T accept(AnimalVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
