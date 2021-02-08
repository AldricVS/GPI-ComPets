package compets.engine.data.map;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A prop permits to draw an image on the map, with position and size (relative
 * to map)
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class Prop {
	private Image image;

	/**
	 * Rectangle coordinates must be set in map's coordinates 
	 * (for example, 1 width = 1 box)
	 */
	private Rectangle rectangle;

	public Prop(Image image, Rectangle rectangle) {
		this.image = image;
		this.rectangle = rectangle;
	}

	/**
	 * Create the prop and define the image with his file path
	 * 
	 * @param imagePath the path of the image
	 * @param rectangle the rectangle that will permits to draw the image properly
	 * @throws IOException If IO error occurs (such as file not found)
	 */
	public Prop(String imagePath, Rectangle rectangle) throws IOException {
		this.rectangle = rectangle;
		loadImage(imagePath);
	}

	/**
	 * Create the prop and define the image with his file path. The rectangle will
	 * be defined by default, with position (0,0) and size (1,1)
	 * 
	 * @param imagePath the path of the image
	 * @throws IOException If IO error occurs (such as file not found)
	 */
	public Prop(String imagePath) throws IOException {
		loadImage(imagePath);
		this.rectangle = new Rectangle(new Position(0, 0), 1, 1);
	}

	private void loadImage(String imagePath) throws IOException {
		File imageFile = new File(imagePath);
		if (!imageFile.exists()) {
			throw new FileNotFoundException(imagePath + " does not exists");
		}
		image = ImageIO.read(imageFile);
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setPosition(Position newPosition) {
		rectangle.setPosition(newPosition);
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
}
