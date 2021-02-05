package compets.engine.process;

import java.util.Random;

import compets.engine.data.animal.Animal;
import compets.engine.data.map.Box;
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
		int xMax = map.getColumnCount();
		int yMax = map.getRowCount();		
		
		 Box[][] map2 = map.getMap();
		
		Position currentPos = animal.getPosition();
		
		int currentXPos= currentPos.getX();
		int currentYPos= currentPos.getY();
		
		int newXPos = rand.nextInt(3) - 1;
		int newYPos = rand.nextInt(3) - 1;
		
		//Vérification des bordures pour un déplacement horizontal
//		bool isWall = map[currentXPos + newXPos][currentYPos].verifWall();
		
		if (currentXPos + newXPos < 0) //|| isWall
			animal.setPosition(currentPos);
		
		else if (currentXPos + newXPos > (xMax - 1)) //|| isWall
			currentPos.setX(xMax - 1);
		
		else
			currentPos.setX(currentXPos + newXPos);

		//Vérification des bordures pour un déplacement vertical
//		bool isWall = map[currentXPos][currentYPos + newYPos].verifWall();
		
		if (currentYPos + newYPos < 0)	//|| isWall
			animal.setPosition(currentPos);
		
		else if (currentYPos + newYPos > (yMax - 1)) //|| isWall
			currentPos.setY(yMax - 1);
		 
		else 
			currentPos.setY(currentYPos + newYPos);

	}
}
