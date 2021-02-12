package compets.engine.process;

import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Behavior;
import compets.engine.data.animal.Gauge;
import compets.engine.data.animal.States;
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
 *
 */
public class AnimalManager {
	private Animal animal;
	private Map map;

	Random rand = new Random();

	public AnimalManager(Animal animal, Map map) {
		this.animal = animal;
		this.map = map;
	}

	/**
	 * Définit les règles de déplacement de l'animal
	 */
	public void chooseNextMove() {
		int xMax = map.getColumnCount();
		int yMax = map.getRowCount();

		Position currentPos = animal.getPosition();

		int currentXPos = currentPos.getX();
		int currentYPos = currentPos.getY();

		int newXPos = rand.nextInt(3) - 1;
		int newYPos = rand.nextInt(3) - 1;

		Position nextPos = new Position(currentXPos + newXPos, currentYPos + newYPos);// Position future de l'animal

		// Vérification des bordures pour un déplacement horizontal
		if (currentXPos + newXPos < 0 || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos); // bloque l'animal si il veut sortir de la carte ou aller sur un mur
		}

		else if (currentXPos + newXPos > (xMax - 1) || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos);
		}

		else {
			currentPos.setX(currentXPos + newXPos);
		}

		// Vérification des bordures pour un déplacement vertical
		if (currentYPos + newYPos < 0 || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos);
		}

		else if (currentYPos + newYPos > (yMax - 1) || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos);
		}

		else {
			currentPos.setY(currentYPos + newYPos);
		}
	}

	public void moveAnimal(Position p) {}
	
	/**
	 * Définit les intéractions possible par l'animal en fonction de la case sur
	 * laquelle il se trouve
	 * 
	 * Ces derniers seront réalisé en fonction du dressage reçu.
	 */
	public void interact() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();

		int obedience = jauge.getValue();
		int max = Gauge.MAX_GAUGE;
		int actionChoice = rand.nextInt(max + 1);

		// Mauvaise action par l'animal
		if (map.getBoxAtPosition(currentPos) instanceof BadItem) {
			if (actionChoice > obedience) {
				changeState(States.BAD_ACTION);
			}
		}

		// Bonne action par l'animal
		else if (map.getBoxAtPosition(currentPos) instanceof GoodItem) {
			if (actionChoice <= obedience) {
				changeState(States.GOOD_ACTION);
			}
		}

		// Possibilité du choix de l'intéraction par l'animal (bonne ou mauvaise)
		else if (map.getBoxAtPosition(currentPos) instanceof NeutralItem) {
			if (actionChoice < obedience-20) {
				changeState(States.GOOD_ACTION);
			} else if (actionChoice >= obedience-20) {
				changeState(States.BAD_ACTION);
			}
		}
	}
	
	/**
	 * Permet de changer l'etat de l'animal
	 * @param s le nouvelle état dans lequel il prendra
	 */
	public void changeState(States s) {
		animal.setStates(s);
//		System.out.println(animal.getStates());
	}
	
	/**
	 *Permet de réinitialiser l'etat de l'animal 
	 */
	public void reset() {
		animal.resetState();
	}

	/**
	 * Renvoi vrai si l'animal a été puni en faisant une mauvaise action. Renvoi
	 * faux sinon.
	 * 
	 * @return
	 */
	public boolean punish() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();

		boolean choice = false;
//		cas animal sur une mauvaise case et qu'il fait une mauvaise action
		if (map.getBoxAtPosition(currentPos) instanceof BadItem && animal.getStates() == States.BAD_ACTION) {
			jauge.increment();
			choice = true;
		}
//		cas animal sur une bonne case et qu'il fait une bonne action
		else if (map.getBoxAtPosition(currentPos) instanceof GoodItem && animal.getStates() == States.GOOD_ACTION) {
			jauge.decrement();
//			choice = false;
		}

//		System.out.println(choice);
		return choice;

	}

	/**
	 * Renvoi vrai si l'animal a été récompenser en faisant une bonne action. Renvoi
	 * faux sinon.
	 * 
	 * @return
	 */
	public boolean reward() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();

		boolean choice = false;
//		cas animal sur une mauvaise case et qu'il fait une mauvaise action

		if (map.getBoxAtPosition(currentPos) instanceof BadItem && animal.getStates() == States.BAD_ACTION) {
			jauge.decrement();
//			choice = false;
		}
//		cas animal sur une bonne case et qu'il fait une bonne action
		else if (map.getBoxAtPosition(currentPos) instanceof GoodItem && animal.getStates() == States.GOOD_ACTION) {
			choice = true;
			jauge.increment();
		}
//		System.out.println(choice);

		return choice;
	}

	public Animal getAnimal() {
		return animal;
	}
}
