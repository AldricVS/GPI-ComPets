package compets.engine.data.animal;

import compets.engine.data.map.Position;

public class Dog implements Animal {
	
	private Behavior behavior;
	private Position position;
	private AnimalState state;
	
	private static final int defaultAction = 50;
	private static final int  defaultHealth = 50;
	
	public Dog(Position position) {
		this.position = position;
		this.behavior = new Behavior(defaultAction,defaultHealth);
		this.state=AnimalState.NEUTRAL;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(int actionGaugeValue, int healthGaugeValue) {
		this.behavior = new Behavior(actionGaugeValue,healthGaugeValue);
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
		state=AnimalState.NEUTRAL;
	}

	@Override
	public String toString() {
		return "Animal [behavior=" + behavior + ", position=" + position + ", state=" + state + "]";
	}
	
}
