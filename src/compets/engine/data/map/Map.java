package compets.engine.data.map;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * @author Maxence
 */
public class Map {

	private Box map[][];
	private ArrayList<Prop> props = new ArrayList<Prop>();
	private int rowCount;
	private int columnCount;

	public Map(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.map = new Box[rowCount][columnCount];
	}

	public Map() {
		this(15, 15);
		Position position;
		// TODO créer une carte complète
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				position = new Position(row, column);
				setBoxOnMap(new EmptyBox(position), position);
			}
		}
		
		try { 
			Rectangle rectangle;
			Image bedImage = loadImage("images/Bed.png");
			Image tableImage = loadImage("images/Table.png");
			Image cakeImage = loadImage("images/Cake.png");
			Image wallImage = loadImage("images/Cake.png");
			Image wcImage = loadImage("images/Bed.png");
			Image baignoireImage = loadImage("images/Bed.png");
			Image lavaboImage = loadImage("images/Bed.png");
			Image chevetImage = loadImage("images/Bed.png");
			Image armoireImage = loadImage("images/Bed.png");
			Image litiereImage = loadImage("images/Bed.png");
			Image frigoImage = loadImage("images/Bed.png");
			Image cuisiniereImage = loadImage("images/Bed.png");
			Image evierImage = loadImage("images/Bed.png");
			Image meubleImage = loadImage("images/Bed.png");
			Image gamelleImage = loadImage("images/Bed.png");
			Image jeuImage = loadImage("images/Bed.png");
			Image canapeImage = loadImage("images/Bed.png");
			Image fauteuilImage = loadImage("images/Bed.png");
			Image tableBasseImage = loadImage("images/Bed.png");
			Image panierImage = loadImage("images/Bed.png");
			Image autreJeuImage = loadImage("images/Bed.png");
			Image teleImage = loadImage("images/Bed.png");
			
			
			
			// placement des murs
			 for ( int i = 0 ; i < 15 ; i ++ ) {
				 for ( int j = 0 ; j < 15 ; j ++ ) {
					 if ( ( i==0) || (i==14) || (j==0) || (j==14)  // encadrement 
							|| ((j==4) && (i<7)) || ((j==6) && ( ((i>6)&&(i<9)) || (i==12) || (i==13)) ) || ((j==9) && ((i==1)||(i==5)) ) // murs horizontaux
							|| ((i==6) && ((j==1)|| ((j>3) && (j<10))) ) // mur verticaux
							 ){
						// rectangle = new Rectangle(new Position(i, j), 1, 1);
						// addNeutralProp(rectangle, wallImage);
						position = new Position(i, j);
						setBoxOnMap(new Wall(position), position);

					 }
					 else if ( (i==1) && (j==1) ) { 
						 // toilette 
						 rectangle = new Rectangle(new Position(i, j), 1, 1);
						addGoodProp(rectangle, wcImage);
					 }
					 else if ( (j==3) && (i==1) ) { 
						 // baignoire 
						 rectangle = new Rectangle(new Position(i, j), 3, 1);
						addGoodProp(rectangle, baignoireImage);
					 }
					 else if ( (j==1) && (i==3)) {
						//lavabo 
						 rectangle = new Rectangle(new Position(i, j), 2, 1);
						addGoodProp(rectangle, lavaboImage);
					 }
					 else if ( (j==1) && ((i==8) || (i==11)) ) {
							//chevet  
							 rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, chevetImage);
					}
					 else if ( (j==1) && (i==9)) {
							//lit 
						 	rectangle = new Rectangle(new Position(i, j), 2, 3);
							addGoodProp(rectangle, bedImage);
					}
					 else if ( (j==3) && (i==13)) {
							//armoir
						 	rectangle = new Rectangle(new Position(i, j), 1, 2);
							addGoodProp(rectangle, armoireImage);
					}
					 else if ( (j==1) && (i==5)) {
							//litiere
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, litiereImage);
					}
					 else if ( (j==5) && (i==1)) {
							//frigo
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, frigoImage);
					}
					 else if ( (j==5) && (i==2)) {
							//cuisiniere
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, cuisiniereImage);
					}
					 else if ( (j==5) && (i==3)) {
							//evier
						 	rectangle = new Rectangle(new Position(i, j), 2, 1);
							addGoodProp(rectangle, evierImage);
					}
					else if ( ((j>4) && (j<9)) && (i==5)) {
							//meuble
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, meubleImage);
					}
					 else if ( (j==8) && (i==1)) {
							//gamelle
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, gamelleImage);
					}
					 else if ( (j==11) && (i==2)) {
							//table
						 	rectangle = new Rectangle(new Position(i, j), 3, 2);
							addNeutralProp(rectangle, tableImage);
					}
					 else if ( (j==13) && (i==6)) {
							//jeu
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addNeutralProp(rectangle, jeuImage);
					}
					 else if ( (j==8) && (i==13)) {
							//canape
						 	rectangle = new Rectangle(new Position(i, j), 1, 4);
							addGoodProp(rectangle, canapeImage);
					}
					 else if ( (j==12) && (i==11)) {
							//fauteuil
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle, fauteuilImage);
					}
					 else if ( (j==9) && (i==11)) {
							//table basse
						 	rectangle = new Rectangle(new Position(i, j), 1, 2);
							addGoodProp(rectangle,tableBasseImage);
					}
					 else if ( (j==7) && (i==13)) {
							//panier
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addGoodProp(rectangle,panierImage);
					}
					 else if ( (j==7) && (i==7)) {
							//tele
						 	rectangle = new Rectangle(new Position(i, j), 1, 2);
							addGoodProp(rectangle,teleImage);
					}
					 else if ( (j==9) && (i==7)) {
							//autre jeu
						 	rectangle = new Rectangle(new Position(i, j), 1, 1);
							addNeutralProp(rectangle, autreJeuImage);
					}
					 
				 }
				 
			 }
			
			rectangle = new Rectangle(new Position(11, 10), 1, 1);
			addBadProp(rectangle, cakeImage);
			
			//Cake (bad item) at bottom
			position = new Position(11, 10);
			setBoxOnMap(new BadItem(position), position);
			
		}catch(IOException exception) {
			System.err.println("Cannot load map : " + exception.getMessage());
		}
		
		
		
		//walls arround map
	/*	for(int line = 0; line < 15; line += 14) {
			for(int col = 0; col < 15; col++) {
				position = new Position(line, col);
				setBoxOnMap(new Wall(position), position);
			}
		}
		for(int col = 0; col < 15; col += 14) {
			for(int line = 0; line < 15; line++) {
				position = new Position(line, col);
				setBoxOnMap(new Wall(position), position);
			}
		}*/
	}
	
	private Image loadImage(String imagePath) throws IOException {
		File imageFile = new File(imagePath);
		if (!imageFile.exists()) {
			throw new FileNotFoundException(imagePath + " does not exists");
		}
		return ImageIO.read(imageFile);
	}
	
	public void addGoodProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new GoodItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addBadProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new BadItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}
	
	public void addNeutralProp(Rectangle rectangle, Image image){
		Position rectPosition = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		for(int col = 0; col < width; col++) {
			for(int line = 0; line < height; line++) {
				Position position = new Position(col + rectPosition.getX(), line + rectPosition.getY());
				setBoxOnMap(new NeutralItem(position), position);
			}
		}
		Prop prop = new Prop(image, rectangle);
		props.add(prop);
	}

	private void setBoxOnMap(Box box, Position position) {
		map[position.getX()][position.getY()] = box;
	}

	public Box[][] getMap() {
		return map;
	}

	public Box getBoxAtPosition(Position position) {
		return map[position.getX()][position.getY()];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public ArrayList<Prop> getProps() {
		return props;
	}
}
