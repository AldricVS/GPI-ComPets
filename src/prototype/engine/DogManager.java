package prototype.engine;

import java.util.Random;

public class DogManager {
	private Dog dog;
	private Map map;

	Random rand = new Random();

	public DogManager(Dog dog, Map map) {
		this.dog = dog;
		this.map = map;
	}

	public Dog getDog() {
		return dog;
	}

	/**
	 * x et y coordonn�e de la case � laquelle le chien a �t� puni ou r�compenser
	 * 
	 * @return
	 */
	public boolean punish() {
		int coordX = dog.getCoordX();
		int coordY = dog.getCoordY();

		boolean choice = false;
		if (map.getValues(coordX, coordY) == Config.BAD_CASE) {
			choice = true;
		} else if (map.getValues(coordX, coordY) == Config.GOOD_CASE)
			choice = false;
		
//		System.out.println(coordX + " -- " + coordY);
//		System.out.println(map.getValues(coordX, coordY));

		return choice;

	}

	public boolean reward() {
		int coordX = dog.getCoordX();
		int coordY = dog.getCoordY();
		boolean choice = false;
		if (map.getValues(coordX, coordY) == Config.GOOD_CASE) {
			choice = true;
		} else if (map.getValues(coordX, coordY) == Config.BAD_CASE)
			choice = false;

		return choice;
	}

	public void moveDog() {

//		int randomMoov=rand.nextInt(4);
//		
//		int posY=dog.getCoordY();
//		int posX=dog.getCoordX();
//		
//	
//		//moovRight
//		if(randomMoov==0 && posX<Config.LARGEUR-1) {
//			dog.setCoordX(posX+1);
//		}
//		
//		//moovLeft
//		if(randomMoov==1 && posX>0) {
//			dog.setCoordX(posX-1);
//		}
//		
//		//moovUp
//		if(randomMoov==2 && posY>0) {
//			dog.setCoordY(posY+1);
//		}
//	
//		//moovDown
//		if(randomMoov==3 && posY<Config.LONGUEUR-1) {
//			dog.setCoordY(posY-1);
//		}
//		System.out.println(dog.getCoordX() + " ---- " + dog.getCoordY());

		int dirX = rand.nextInt(3) - 1;
		int dirY = rand.nextInt(3) - 1;

		int xMax = map.getLargeur();
		int yMax = map.getLongueur();

		int dogCoordX = dog.getCoordX();
		if (dogCoordX + dirX < 0) {
			dog.setCoordX(0);
		} else if (dogCoordX + dirX > (xMax - 1)) {
			dog.setCoordX(xMax - 1);
		} else {
			dog.setCoordX(dogCoordX + dirX);
		}

		int dogCoordY = dog.getCoordY();
		if (dogCoordY + dirY < 0) {
			dog.setCoordY(0);
		} else if (dogCoordY + dirY > (yMax - 1)) {
			dog.setCoordY(yMax - 1);
		} else {
			dog.setCoordY(dogCoordY + dirY);
		}
	}
}
