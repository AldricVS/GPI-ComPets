package prototype.engine;


public class Map {
	private int largeur;
	private int longueur;
	private int [][]map;
	
	public Map(int largeur, int longueur) {
		map = new int [largeur][longueur];
		this.largeur = largeur;
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public int[][] getMap() {
		return map;
	}
	
	
	
	
}
