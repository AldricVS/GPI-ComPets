package compets.engine.process;

import java.io.IOException;
import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalType;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.animal.AnimalManager;
import compets.engine.process.animal.AnimalStateHandler;

/**
 * This class contains two main static functions :
 * <ul>
 * <li>One for creating a new game</li>
 * <li>One for loading a game from the unique save file</li>
 * </ul>
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 * @author Nathan VIRAYIE <nathan.virayie@icloud.com>
 */
public class GameInitializer {
	private static final Position[] ANIMALPOSITIONS = { 
			new Position(6, 3), 
			new Position(10, 5), 
			new Position(3, 7), 
			new Position(6, 11) 
		};

	static Random rand = new Random();

	/**
	 * Initialise une nouvelle partie
	 * 
	 * @return le gameManager déja parametré
	 */
	public static GameManager createNewGame(AnimalType animalType) {
		Position pos = chooseRandomPosition();
		Animal animal;
		switch (animalType) {
		case DOG:
			animal = new Dog(pos);
			break;
		case CAT:
			animal = new Cat(pos);
			break;
		case FOX:
			animal = new Fox(pos);
			break;
		default:
			animal = new Dog(pos);
			break;
		}
		Map map = new Map();
		AnimalManager animalManager = new AnimalManager(animal, map);
		AnimalStateHandler animalStateHandler = new AnimalStateHandler(animal);

		return new GameManager(animalManager, animalStateHandler);
	}

	/**
	 * Choisis une position aléatoire parmis celle disponible pour l'animal
	 * 
	 * @return la position choisie
	 */
	private static Position chooseRandomPosition() {
		int index = rand.nextInt(ANIMALPOSITIONS.length);
		return ANIMALPOSITIONS[index];
	}

	public static GameManager loadGame() throws IOException {
		SimulationSave saver = new SimulationSave();
		Animal animal;

		animal = saver.loadAnimalData();

		Map map = new Map();
		AnimalManager am = new AnimalManager(animal, map);
		AnimalStateHandler ash = new AnimalStateHandler(animal);

		return new GameManager(am, ash);
	}
}
