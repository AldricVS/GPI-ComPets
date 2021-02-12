package compets.gui.management;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import compets.engine.data.animal.Animal;

/**
 * Utility class in order to get the animal image with all his states.
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class AnimalImageUtility {
	private final String PATH = "images/Animal/";
	
	private Image imageBadState;
	private Image imageGoodState;
	private Image imageNeutralState;

	/**
	 * Loads the utility and load all images from files.
	 * 
	 * @throws IOException If any file needed is not found, or they cannot be read
	 */
	public AnimalImageUtility() throws IOException {
		File file = new File(PATH + "Animal_GOOD.png");
		imageGoodState = ImageIO.read(file);
		file = new File(PATH + "Animal_BAD.png");
		imageBadState = ImageIO.read(file);
		file = new File(PATH + "Animal_NEUTRAL.png");
		imageNeutralState = ImageIO.read(file);
	}
	
	public Image getCorrespondingImage(Animal animal) {
		switch (animal.getStates()) {
		case GOOD_ACTION:
			return imageGoodState;
		case BAD_ACTION:
			return imageBadState;
		default:
			// By default, return the neutral state image 
			return imageNeutralState;
		}
	}
}
