package compets.engine.data.animal;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.AnimalVisitor;

public class Dog extends Animal {
	
	private static final int defaultAction = 50;
	private static final int  defaultHealth = 50;
	
	public Dog(Position position) {
		super(position, new Behavior(defaultAction,defaultHealth));
	}

	@Override
	public String toString() {
		return "Dog [behavior=" + getBehavior() + ", position=" + getPosition() + ", state=" + getState() + "]";
	}
	
	@Override
	public <T> T accept(AnimalVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
