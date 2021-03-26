package compets.engine.process.animal;

import java.util.Random;

import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Behavior;
import compets.engine.data.animal.Gauge;
import compets.engine.data.animal.UserAction;
import compets.engine.data.constants.ActionModifValues;
import compets.engine.data.constants.HealthModifValues;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.map.Box;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * Cette classe permet de gérer le déroulement du jeu
 * 
 * @author Nathan VIRAYIE
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class AnimalManager {
	private final int MAX_NATURAL_HEALTH_REGEN = 65;
	
	private Animal animal;
	private Map map;

	private UserAction userAction = UserAction.NEUTRAL;

	private boolean hasInteracted = false;

	Random rand = new Random();

	public AnimalManager(Animal animal, Map map) {
		this.animal = animal;
		this.map = map;
	}

	/**
	 * The main method to call from the gui. The animal will do something depending
	 * on what he do before : if he moved last turn, he will try to interact with
	 * the item under him.
	 * 
	 */
	public void doSomething() {
		updateBehavior();
		updateWellBeing();
		resetAnimalState();
		tryToInteract();
		
	}

	private void tryToInteract() {
		if (hasInteracted) {
			// Try to move directly
			moveAnimalToNewPosition();
			hasInteracted = false;
		} else {
			// If can interact with something, do it
			Box boxAtPosition = map.getBoxAtPosition(animal.getPosition());
			if (!(boxAtPosition instanceof EmptyBox)) {
				interact();
			} else {
				// Else, move
				moveAnimalToNewPosition();
			}
		}
	}

	/**
	 * Depending on the state of the animal (and before resetting it), increment or
	 * decrement the behavior gauge
	 */
	public void updateBehavior() {
		Gauge actionGauge = animal.getBehavior().getActionGauge();
		switch (animal.getState()) {
		case GOOD_ACTION:
			actionGauge.addValue(ActionModifValues.GOOD_ACTION);
			break;
		case BAD_ACTION:
			actionGauge.addValue(ActionModifValues.BAD_ACTION);
			break;
		default:
			// case NEUTRAL
			break;
		}
	}

	/**
	 * Verifie si l'animal a été bien traité par l'utisateur lors des phases
	 * d'inactivité ou des bonnes actions. Modifie la jauge de bien etre en fontion.
	 */
	public void updateWellBeing() {
		Behavior bh = this.animal.getBehavior();
		Gauge healthGauge = bh.getHealthGauge();
		switch (animal.getState()) {
		case NEUTRAL:
			if (userAction == UserAction.NEUTRAL && healthGauge.getValue() < MAX_NATURAL_HEALTH_REGEN) {
				healthGauge.addValue(HealthModifValues.DONE_NOTHING);
			}
			break;
		case GOOD_ACTION:
			// Decrease well being if done a good action but has recieved no reward
			if (userAction == UserAction.NEUTRAL) {
				healthGauge.addValue(HealthModifValues.GOOD_ACTION_NOT_REWARDED);
			}
			break;
		case BAD_ACTION:
			break;
		default:
			break;
		}
	}

	/**
	 * Force the animal to move to a new random position, so that he is not stuck at
	 * the same place during several turns
	 */
	public void moveAnimalToNewPosition() {
		Position oldPosition = animal.getPosition();
		Position newPosition;
		do {
			moveAnimal(chooseNextMove());
			newPosition = animal.getPosition();
		} while (newPosition.equals(oldPosition));
	}

	/**
	 * Choose randomly the next position the animal want to take
	 */
	public Position chooseNextMove() {
		Position currentPos = animal.getPosition();

		int currentXPos = currentPos.getX();
		int currentYPos = currentPos.getY();

		int newXPos;
		int newYPos;

		// We want the animal to move every turn
		do {
			newXPos = rand.nextInt(3) - 1;
			newYPos = rand.nextInt(3) - 1;
		} while (newXPos == 0 && newYPos == 0);

		Position nextPos = new Position(currentXPos + newXPos, currentYPos + newYPos);
		return nextPos;
	}

	/**
	 * Move the animal to the position, except if he can't (there is a wall or the
	 * position is out of the map)
	 * 
	 * @param position the position where to move
	 */
	public void moveAnimal(Position position) {
		// check if the position is in the map
		if (map.isPositionOnMap(position)) {
			// check if the box in the position is not a wall
			Box boxAtPosition = map.getBoxAtPosition(position);
			if (!(boxAtPosition instanceof Wall)) {
				// we can safely move the animal
				animal.setPosition(position);
			}
		}
	}

	/**
	 * Change l'état de l'animal en fonction de la case sur
	 * laquelle il se trouve
	 * 
	 * Le changement sera réalisé en fonction du dressage reçu.
	 */
	public void interact() {
		Position currentPos = animal.getPosition();
		Gauge jauge = animal.getBehavior().getActionGauge();
		hasInteracted = true;

		int obedience = jauge.getValue();
		int actionChoice = rand.nextInt(Gauge.MAX_GAUGE + 1);

		// Mauvaise action par l'animal
		if (map.getBoxAtPosition(currentPos) instanceof BadItem) {
			if (actionChoice >= obedience) {
				changeState(AnimalState.BAD_ACTION);
			}
		}

		// Bonne action par l'animal
		else if (map.getBoxAtPosition(currentPos) instanceof GoodItem) {
			if (actionChoice <= obedience) {
				changeState(AnimalState.GOOD_ACTION);
			}
		}

		// Possibilité du choix de l'intéraction par l'animal (bonne ou mauvaise)
		else if (map.getBoxAtPosition(currentPos) instanceof NeutralItem) {
			if (actionChoice < obedience - 20) {
				changeState(AnimalState.GOOD_ACTION);
			} else if (actionChoice >= obedience - 20) {
				changeState(AnimalState.BAD_ACTION);
			}
		}
	}

	/**
	 * Permet de changer l'etat de l'animal
	 * 
	 * @param s le nouvelle état dans lequel il prendra
	 */
	public void changeState(AnimalState state) {
		animal.setState(state);
	}

	/**
	 * Permet de réinitialiser l'etat de l'animal
	 */
	public void resetAnimalState() {
		animal.resetState();
		userAction = UserAction.NEUTRAL;
	}

	/**
	 * Renvoi vrai si l'animal a été puni en faisant une mauvaise action. Renvoie
	 * faux sinon.
	 * 
	 * @return
	 */
	public boolean punish() {
		Behavior bh = this.animal.getBehavior();
		Gauge actionGauge = bh.getActionGauge();
		Gauge healthGauge = bh.getHealthGauge();

		boolean choice = false;

		// Only with the animal state, we can know if he is doing something good or bad
		// (no need to check on top of which Item he is)

		switch (animal.getState()) {
		case NEUTRAL:
			actionGauge.addValue(ActionModifValues.NEUTRAL_PUNISHED);
			healthGauge.addValue(HealthModifValues.PUNISH_FOR_NOTHING);
			break;
		case GOOD_ACTION:
			actionGauge.addValue(ActionModifValues.GOOD_ACTION_PUNISHED);;
			healthGauge.addValue(HealthModifValues.PUNISH_FOR_GOOD_ACTION);
			break;
		case BAD_ACTION:
			actionGauge.addValue(ActionModifValues.BAD_ACTION_PUNISHED);
			choice = true;
			break;
		default:
			break;
		}
		userAction = UserAction.PUNISHING;

		return choice;

	}

	/**
	 * Renvoi vrai si l'animal a été récompenser en faisant une bonne action.
	 * Renvoie faux sinon. Le tout en implémentant le comportement de l'animal.
	 * 
	 * @return
	 */
	public boolean reward() {
		Behavior bh = this.animal.getBehavior();
		Gauge actionGauge = bh.getActionGauge();
		Gauge healthGauge = bh.getHealthGauge();

		boolean choice = false;

		switch (animal.getState()) {
		case NEUTRAL:
			actionGauge.addValue(ActionModifValues.NEUTRAL_REWARDED);
			break;
		case GOOD_ACTION:
			choice = true;
			actionGauge.addValue(ActionModifValues.GOOD_ACTION_REWARDED);
			healthGauge.addValue(HealthModifValues.REWARD_FOR_GOOD_ACTION); // increase well-be
			break;
		case BAD_ACTION:
			actionGauge.addValue(ActionModifValues.BAD_ACTION_REWARDED);
			break;
		default:
			break;
		}
		
		userAction = UserAction.REWARDING;
		return choice;
	}

	public Animal getAnimal() {
		return animal;
	}

	public Map getMap() {
		return map;
	}
}
