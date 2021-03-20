package compets.engine.data.animal;

import compets.engine.data.map.Position;
import compets.engine.process.visitor.AnimalVisitor;

public interface Animal {
	public Behavior getBehavior();
	
	public void setBehavior(int actionGaugeValue, int healthGaugeValue);
	
	public Position getPosition();

	public void setPosition(Position position);

	public AnimalState getState();

	public void setState(AnimalState animalStates);

	public void resetState();

	public String toString();
	
	public abstract <T> T accept(AnimalVisitor<T> visitor);
}
