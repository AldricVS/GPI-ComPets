package compets.engine.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Dog;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;

/**
 * This class contains two main static functions : 
 * <ul>
 * 	<li>One for creating a new game</li>
 * 	<li>One for loading a game from the unique save file</li>
 * </ul>
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 * @author Nathan VIRAYIE <nathan.virayie@icloud.com>
 */
public class GameInitializer {
	private static final Position [] ANIMALPOSITIONS= {new Position(6,3),
			new Position(10,5),
			new Position(3,7),
			new Position(6,11)};
	
	static Random rand = new Random();
	
	/**
	 * Initialise une nouvelle partie
	 * 
	 * @return le gameManager avec déja parametré
	 */
	public static GameManager createNewGame() {
		Position pos = choosePos();
		Animal animal = new Dog(pos);
		Map map = new Map();
		AnimalManager am = new AnimalManager(animal, map);
		AnimalStateHandler ash = new AnimalStateHandler(animal);
		
		return new GameManager(am, ash);
	}
	
	/**
	 * Choisis une position aléatoire parmis celle disponible pour l'animal
	 * 
	 * @return la position choisie
	 */
	private static Position choosePos() {
		int index = rand.nextInt(ANIMALPOSITIONS.length);
		return ANIMALPOSITIONS[index];
	}

	public static GameManager loadGame() {		
		SimulationSave saver = new SimulationSave();
		Animal animal;
		
		try {
			animal = saver.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		Map map = new Map();
		AnimalManager am = new AnimalManager(animal, map);
		AnimalStateHandler ash = new AnimalStateHandler(animal);
		
		return new GameManager(am, ash);
	}
}
