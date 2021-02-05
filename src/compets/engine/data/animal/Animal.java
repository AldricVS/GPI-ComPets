package compets.engine.data.animal;

import compets.engine.data.map.Position;

public class Animal {
	
	private Behavior behavior;
	private Position position;
	
	public Animal(Position position) {
		this.position = position;
		this.behavior = new Behavior();
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
}
