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
	public void moveAnimal() {
		int xMax = map.getColumnCount();
		int yMax = map.getRowCount();

		Position currentPos = animal.getPosition();

		int currentXPos = currentPos.getX();
		int currentYPos = currentPos.getY();

		int newXPos = rand.nextInt(3) - 1;
		int newYPos = rand.nextInt(3) - 1;

		Position nextPos = new Position(currentXPos + newXPos, currentYPos + newYPos);

		// Vérification des bordures pour un déplacement horizontal

		if (currentXPos + newXPos < 0 || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos);
		}

		else if (currentXPos + newXPos > (xMax - 1) || map.getBoxAtPosition(nextPos) instanceof Wall) {
			currentPos.setX(xMax - 1);
		}

		else {
			currentPos.setX(currentXPos + newXPos);
		}
		// Vérification des bordures pour un déplacement vertical

		if (currentYPos + newYPos < 0 || map.getBoxAtPosition(nextPos) instanceof Wall) {
			animal.setPosition(currentPos);
		} else if (currentYPos + newYPos > (yMax - 1) || map.getBoxAtPosition(nextPos) instanceof Wall) {
			currentPos.setY(yMax - 1);
		} else {
			currentPos.setY(currentYPos + newYPos);
		}
	}

	public void interact() {
		Position currentPos = this.animal.getPosition();
		int actionChoise = rand.nextInt(3);

		if (map.getBoxAtPosition(currentPos) instanceof BadItem) {
			if (actionChoise == 1) {
				animal.setStates(States.BAD_ACTION);
			}
		}

		else if (map.getBoxAtPosition(currentPos) instanceof GoodItem) {
			if (actionChoise == 1) {
				animal.setStates(States.GOOD_ACTION);
			}
		}

		else if (map.getBoxAtPosition(currentPos) instanceof NeutralItem) {
			if (actionChoise == 1) {
				animal.setStates(States.GOOD_ACTION);
			} else {
				animal.setStates(States.BAD_ACTION);
			}
		}
		System.out.println(animal.getStates());
	}

	public boolean punish() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();

		boolean choice = false;
		if (map.getBoxAtPosition(currentPos) instanceof BadItem && animal.getStates()==States.BAD_ACTION) {
			jauge.increment();
			choice = true;
		} else if (map.getBoxAtPosition(currentPos) instanceof GoodItem && animal.getStates()==States.GOOD_ACTION) {
			jauge.decrement();
			choice = false;
		}

		return choice;

	}

	public boolean reward() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();

		boolean choice = false;
		if (map.getBoxAtPosition(currentPos) instanceof BadItem && animal.getStates()==States.BAD_ACTION) { // si le chien est sur case de mauvaise action
			jauge.decrement();
			choice = false;
		} else if (map.getBoxAtPosition(currentPos) instanceof GoodItem && animal.getStates()== States.GOOD_ACTION) {
			choice = true;
			jauge.increment();
		}

		return choice;
	}

	public Animal getAnimal() {
		return animal;
	}
}
