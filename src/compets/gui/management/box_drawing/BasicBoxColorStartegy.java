package compets.gui.management.box_drawing;

import java.awt.Color;
import java.awt.Graphics;

import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * The basic color strategy for elements on the map : 
 * <ul>
 * <li>EmptyBox is grey</li>
 * <li>Wall is black</li>
 * <li>BadItem is red</li>
 * <li>GoodItem is green</li>
 * <li>Neutraltem is orange</li>
 * </ul>
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class BasicBoxColorStartegy implements BoxColorStrategy {
	private static final Color EMPTY_BOX_COLOR = new Color(200, 200, 200);
	private static final Color WALL_COLOR = new Color(74, 74, 74);
	private static final Color GOOD_ITEM_COLOR = new Color(0, 128, 0);
	private static final Color BAD_ITEM_COLOR = new Color(139, 0, 0);
	private static final Color NEUTRAL_ITEM_COLOR = new Color(240, 132, 32);
	
	@Override
	public void setColor(Graphics graphics, EmptyBox emptyBox) {
		graphics.setColor(EMPTY_BOX_COLOR);
	}

	@Override
	public void setColor(Graphics graphics, Wall wall) {
		graphics.setColor(WALL_COLOR);
	}

	@Override
	public void setColor(Graphics graphics, BadItem badItem) {
		graphics.setColor(BAD_ITEM_COLOR);
	}

	@Override
	public void setColor(Graphics graphics, GoodItem goodItem) {
		graphics.setColor(GOOD_ITEM_COLOR);
	}

	@Override
	public void setColor(Graphics graphics, NeutralItem neutralItem) {
		graphics.setColor(NEUTRAL_ITEM_COLOR);
	}

}
