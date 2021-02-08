package compets.gui.management;

import java.awt.Graphics;

import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Position;
import compets.engine.data.map.Rectangle;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;
import compets.engine.process.visitor.BoxVisitor;
import compets.gui.ColorConstants;

/**
 * Box visitor implementation that draw a box with the grid.
 * If needed, a color strategy can be used in order to change box colors (by default, the {@link 
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class PaintBoxVisitor implements BoxVisitor<Void> {

	private Graphics graphics;
	/**
	 * The rectangle to draw on the screen (position, width, height)
	 */
	private Rectangle rectangle;

	private ColorStrategy colorStrategy;

	public PaintBoxVisitor(Graphics graphics, Rectangle rectangle) {
		this.graphics = graphics;
		this.rectangle = rectangle;
		colorStrategy = new BasicColorStartegy();
	}

	public PaintBoxVisitor(Graphics graphics, Rectangle rectangle, ColorStrategy colorStrategy) {
		this.graphics = graphics;
		this.rectangle = rectangle;
		this.colorStrategy = colorStrategy;
	}

	@Override
	public Void visit(EmptyBox node) {
		colorStrategy.setColor(graphics, node);
		draw();
		return null;
	}

	@Override
	public Void visit(Wall node) {
		colorStrategy.setColor(graphics, node);
		draw();
		return null;
	}

	@Override
	public Void visit(GoodItem node) {
		colorStrategy.setColor(graphics, node);
		draw();
		return null;
	}

	@Override
	public Void visit(BadItem node) {
		colorStrategy.setColor(graphics, node);
		draw();
		return null;
	}

	@Override
	public Void visit(NeutralItem node) {
		colorStrategy.setColor(graphics, node);
		draw();
		return null;
	}

	private void draw() {
		Position position = rectangle.getPosition();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();

		//draw the box with the color defined above
		graphics.drawRect(position.getX() * width, position.getY() * height, width, height);

		// draw the grid
		graphics.setColor(ColorConstants.GRID_COLOR);
		graphics.drawRect(position.getX() * width + 1, position.getY() * height + 1, width - 1, height - 1);
	}
}
