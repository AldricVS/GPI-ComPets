package compets.engine.process.factory;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalType;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.data.map.Position;

public class AnimalFactory {
	
	public static Animal createDog(Position position) {
		return new Dog(position);
	}
	
	public static Animal createCat(Position position) {
		return new Cat(position);
	}
	
	public static Animal createAnimalWithType(AnimalType animalType, Position position) {
		switch (animalType) {
		case DOG:
			return new Dog(position);
		case CAT:
			return new Cat(position);
		case FOX:
			return new Fox(position);
		default:
			return new Dog(position);
		}
	}
}
