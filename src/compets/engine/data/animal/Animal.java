package compets.engine.data.animal;

import compets.engine.data.map.Position;

public class Animal {
	
	private Behavior behavior;
	private Position position;
	private States state;
	
	public Animal(Position position) {
		this.position = position;
		this.behavior = new Behavior();
		this.state=States.NEUTRAL;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public States getStates() {
		return state;
	}

	public void setState(States animalStates) {
		this.state = animalStates;
	}

	public void resetState() {
		state=States.NEUTRAL;
	}

	@Override
	public String toString() {
		return "Animal [behavior=" + behavior + ", position=" + position + ", state=" + state + "]";
	}
	
}
