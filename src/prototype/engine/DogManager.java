package prototype.engine;

import java.util.Random;

public class DogManager {
	private Dog dog;
	private Map map;
	
	
	public DogManager(Dog dog, Map map) {
		this.dog = dog;
	}

	/**
	 * x et y coordonnée de la case à laquelle le chien a été puni ou récompenser
	 * @return 
	 */
	public boolean punish() {
		int coordX=dog.getCoordX();
		int coordY=dog.getCoordY();
		
		boolean choice =false;
		if(map.getValues(coordX,coordY)==Config.BAD_CASE) {
			choice=true;
		}
		else if(map.getValues(coordX,coordY)==Config.GOOD_CASE)
			choice=false;
		
		return choice;

	}
	
	public boolean reward() {
		int coordX=dog.getCoordX();
		int coordY=dog.getCoordY();
		boolean choice =false;
		if(map.getValues(coordX,coordY)==Config.GOOD_CASE) {
			choice=true;
		}
		else if(map.getValues(coordX,coordY)==Config.BAD_CASE)
			choice=false;
		
		return choice;
	}
	
	public void moovDog(){
		Random rand = new Random();
		int randomMoov=rand.nextInt(4);
		
		int posY=dog.getCoordY();
		int posX=dog.getCoordX();
		
	
		//moovRight
		if(randomMoov==0 && posX<Config.LARGEUR-1) {
			dog.setCoordX(posX+1);
		}
		
		//moovLeft
		if(randomMoov==1 && posX>0) {
			dog.setCoordX(posX-1);
		}
		
		//moovUp
		if(randomMoov==2 && posY>0) {
			dog.setCoordY(posY+1);
		}
	
		//moovDown
		if(randomMoov==3 && posY<Config.LONGUEUR-1) {
			dog.setCoordY(posY-1);
		}
		
	}
}
