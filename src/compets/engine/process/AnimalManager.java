package compets.engine.process;

import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;

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
		/***DIMENSIONS DE LA CARTE EN DUR******/
		int xMax = 15;
		int yMax = 15;
		/***************************************/
		
		
		Position currentPos = animal.getPosition();
		
		int currentXPos= currentPos.getX();
		int currentYPos= currentPos.getY();
		
		int newXPos = rand.nextInt(3) - 1;
		int newYPos = rand.nextInt(3) - 1;
		
		//verification des bordures
		if (currentXPos + newXPos < 0) 
			animal.setPosition(currentPos);
		
		else if (currentXPos + newXPos > (xMax - 1))
			currentPos.setX(xMax - 1);
		
		else
			currentPos.setX(currentXPos + newXPos);

		if (currentYPos + newYPos < 0)
			animal.setPosition(currentPos);
		
		else if (currentYPos + newYPos > (yMax - 1)) 
			currentPos.setY(yMax - 1);
		 
		else 
			currentPos.setY(currentYPos + newYPos);

	}
}
