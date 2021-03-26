package compets.gui.management.animal_drawing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.process.visitor.AnimalVisitor;

public class AnimalImageLoadVisitor implements AnimalVisitor<AnimalImagesRepository> {
	private static final String DOG_IMAGES_PATH = "images/Animal/Dog/";
	private static final String CAT_IMAGES_PATH = "images/Animal/Cat/";
	

	@Override
	public AnimalImagesRepository visit(Dog dog) {
		Image imgNeutral = loadImage(DOG_IMAGES_PATH + "Dog_NEUTRAL.png");
		Image imgGood = loadImage(DOG_IMAGES_PATH + "Dog_GOOD.png");
		Image imgBad = loadImage(DOG_IMAGES_PATH + "Dog_BAD.png");
		return new AnimalImagesRepository(imgGood, imgBad, imgNeutral);
	}

	@Override
	public AnimalImagesRepository visit(Cat dog) {
		Image imgNeutral = loadImage(CAT_IMAGES_PATH + "Cat_NEUTRAL.png");
		Image imgGood = loadImage(CAT_IMAGES_PATH + "Cat_GOOD.png");
		Image imgBad = loadImage(CAT_IMAGES_PATH + "Cat_BAD.png");
		return new AnimalImagesRepository(imgGood, imgBad, imgNeutral);
	}

	private Image loadImage(String imageName){
		File file = new File(imageName);
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			System.err.println(String.format("Cannot read \"%s\" : %s", imageName, e.getMessage()));
			return null;
		}
	}

	@Override
	public AnimalImagesRepository visit(Fox fox) {
		// TODO Auto-generated method stub
		return null;
	}
}
