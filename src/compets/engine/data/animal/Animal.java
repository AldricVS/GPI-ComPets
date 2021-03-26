package compets.engine.data.animal;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.AnimalVisitor;

public abstract class Animal {

	private Behavior behavior;
	private Position position;
	private AnimalState state;

	protected Animal(Position position, Behavior behavior) {
		this.position = position;
		this.behavior = behavior;
		this.state = AnimalState.NEUTRAL;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(int actionGaugeValue, int healthGaugeValue) {
		this.behavior = new Behavior(actionGaugeValue, healthGaugeValue);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public AnimalState getState() {
		return state;
	}

	public void setState(AnimalState animalStates) {
		this.state = animalStates;
	}

	public void resetState() {
		state = AnimalState.NEUTRAL;
	}

	public abstract String toString();

	public abstract <T> T accept(AnimalVisitor<T> visitor);
}
