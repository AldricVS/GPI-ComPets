package compets.engine.data.animal;

import compets.engine.data.map.Position;

public interface Animal {
	public Behavior getBehavior();
	
	public Position getPosition();

	public void setPosition(Position position);

	public AnimalState getState();

	public void setState(AnimalState animalStates);

	public void resetState();

	public String toString();
}
