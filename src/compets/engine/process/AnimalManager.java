package compets.engine.process;

import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Behavior;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;

public class AnimalManager{
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
		
		int currentXPos= currentPos.getX();
		int currentYPos= currentPos.getY();
		
		int newXPos = rand.nextInt(3) - 1;
		int newYPos = rand.nextInt(3) - 1;
		
		//Vérification des bordures pour un déplacement horizontal
		
		if (currentXPos + newXPos < 0 || map.getBoxAtPosition(currentPos).getClass().isInstance("Wall")) 
			animal.setPosition(currentPos);
		
		else if (currentXPos + newXPos > (xMax - 1) || map.getBoxAtPosition(currentPos).getClass().isInstance("Wall"))
			currentPos.setX(xMax - 1);
		
		else
			currentPos.setX(currentXPos + newXPos);

		//Vérification des bordures pour un déplacement vertical
		
		if (currentYPos + newYPos < 0 || map.getBoxAtPosition(currentPos).getClass().isInstance("Wall"))
			animal.setPosition(currentPos);
		
		else if (currentYPos + newYPos > (yMax - 1) || map.getBoxAtPosition(currentPos).getClass().isInstance("Wall"))
			currentPos.setY(yMax - 1);
		 
		else 
			currentPos.setY(currentYPos + newYPos);

	}
	
	public boolean punish() {
		Position currentPos = this.animal.getPosition();
		Behavior bh = this.animal.getBehavior();
		Gauge jauge = bh.getActionGauge();
		
		boolean choice = false;
		if (map.getBoxAtPosition(currentPos).getClass().isInstance("BadItem")) {
			jauge.increment();
			choice = true;
		} else if (map.getBoxAtPosition(currentPos).getClass().isInstance("GoodItem")) {
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
		if (map.getBoxAtPosition(currentPos).getClass().isInstance("BadItem")) { // si le chien est sur case de mauvaise action
			jauge.decrement();
			choice = true;
		} else if (map.getBoxAtPosition(currentPos).getClass().isInstance("GoodItem")) {
			choice = false;
			jauge.increment();
		}

		return choice;
	}

	public Animal getAnimal() {
		return animal;
	}
}
