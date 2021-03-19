package compets.engine.process;

/**
 * Basic class that contains all game relative data.
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class GameManager {
	private AnimalManager animalManager;
	private AnimalStateHandler animalStateHandler;
	
	public GameManager(AnimalManager animalManager, AnimalStateHandler animalStateHandler) {
		this.animalManager = animalManager;
		this.animalStateHandler = animalStateHandler;
	}

	public AnimalManager getAnimalManager() {
		return animalManager;
	}

	public AnimalStateHandler getAnimalStateHandler() {
		return animalStateHandler;
	}

	public void setAnimalManager(AnimalManager animalManager) {
		this.animalManager = animalManager;
	}

	public void setAnimalStateHandler(AnimalStateHandler animalStateHandler) {
		this.animalStateHandler = animalStateHandler;
	}
}
