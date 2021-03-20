package compets.gui.management.animal_drawing;

import java.awt.Image;

/**
 * Stores all images needed for an animal
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class AnimalImagesRepository {
	private Image imageGoodState = null;
	private Image imageBadState = null;
	private Image imageNeutralState = null;

	public AnimalImagesRepository(Image imageGoodState, Image imageBadState, Image imageNeutralState) {
		this.imageGoodState = imageGoodState;
		this.imageBadState = imageBadState;
		this.imageNeutralState = imageNeutralState;
	}
	
	public boolean areAllImagesLoaded() {
		return imageBadState != null && imageGoodState != null && imageNeutralState != null;
	}

	public Image getImageGoodState() {
		return imageGoodState;
	}

	public Image getImageBadState() {
		return imageBadState;
	}

	public Image getImageNeutralState() {
		return imageNeutralState;
	}

	public void setImageGoodState(Image imageGoodState) {
		this.imageGoodState = imageGoodState;
	}

	public void setImageBadState(Image imageBadState) {
		this.imageBadState = imageBadState;
	}

	public void setImageNeutralState(Image imageNeutralState) {
		this.imageNeutralState = imageNeutralState;
	}
}
