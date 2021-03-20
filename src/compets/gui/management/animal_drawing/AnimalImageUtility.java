package compets.gui.management.animal_drawing;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;

/**
 * Utility class in order to get the animal image with all his states.
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class AnimalImageUtility {
	private AnimalImagesRepository animalImagesRepository;

	/**
	 * Loads the utility and load all images from files. All images not found will be just marked as null.
	 */
	public AnimalImageUtility(Animal animal){
		AnimalImageLoadVisitor animalVisitor = new AnimalImageLoadVisitor();
		animalImagesRepository = animal.accept(animalVisitor);
	}
	
	public Image getCorrespondingImage(Animal animal) {
		switch (animal.getState()) {
		case GOOD_ACTION:
			return animalImagesRepository.getImageGoodState();
		case BAD_ACTION:
			return animalImagesRepository.getImageBadState();
		default:
			// By default, return the neutral state image 
			return animalImagesRepository.getImageNeutralState();
		}
	}
}
