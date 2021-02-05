package compets.engine.process.visitor;

import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Wall;
import compets.engine.data.map.item.BadItem;
import compets.engine.data.map.item.GoodItem;
import compets.engine.data.map.item.NeutralItem;

/**
 * Generic tree visitor supporting all type type.
 * 
 * @author Maxence
 */
public interface BoxVisitor<T> {

	T visit(EmptyBox node);

	T visit(Wall node);

	T visit(GoodItem node);

	T visit(BadItem node);

	T visit(NeutralItem node);

}
