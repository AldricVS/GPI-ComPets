package compets.gui.management;

import java.awt.Graphics;

import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * The generic strategy used to define the color of each element on the map
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public interface ColorStrategy {
	
	public void setColor(Graphics graphics, EmptyBox emptyBox);
	public void setColor(Graphics graphics, Wall wall);
	public void setColor(Graphics graphics, BadItem badItem);
	public void setColor(Graphics graphics, GoodItem goodItem);
	public void setColor(Graphics graphics, NeutralItem neutralItem);
}
