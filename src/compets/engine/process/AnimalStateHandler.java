package compets.engine.process;

import compets.engine.data.animal.Animal;
import compets.engine.data.behavior.BehaviorStatesEnum;

public class AnimalStateHandler {
	private Animal animal;

	public AnimalStateHandler(Animal animal) {
		this.animal = animal;
	}
	
	public BehaviorStatesEnum checkState(){
		return null;
	}
}
