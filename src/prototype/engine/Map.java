package prototype.engine;


public class Map {
	private int largeur;
	private int longueur;
	private int [][]map;
	
	public Map() {
		this.largeur = Config.LARGEUR;
		this.longueur = Config.LONGUEUR;
		map = new int [largeur][longueur];

		for(int indexLargeur=0; indexLargeur<largeur;indexLargeur++) {
			for(int indexLongeur=0; indexLongeur<longueur;indexLongeur++) {
				map[indexLargeur][indexLongeur]=Config.NORMAL_CASE;
			}
		}
		
		//position des case spé
		map[1][2]=Config.BAD_CASE;
		map[2][1]=Config.BAD_CASE;
		map[1][1]=Config.GOOD_CASE;
		map[2][2]=Config.GOOD_CASE;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public int getValues(int coordX, int coordY) {
		return map[coordX][coordY];
	}
	
	
	
	
}
