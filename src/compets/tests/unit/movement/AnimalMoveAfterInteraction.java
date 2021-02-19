package compets.tests.unit.movement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.States;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.NeutralItem;
import compets.engine.process.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalMoveAfterInteraction {

	private static Animal animal;
	private static AnimalManager manager;
	private static Map map;
	private static Position startingPosition;
	private static Position emptyPosition;

	@BeforeClass
	public static void init() {
		startingPosition = new Position(0, 0);
		emptyPosition = new Position(0, 1);
		
		map = new Map(1, 2);
		map.getMap()[startingPosition.getX()][startingPosition.getY()] = new NeutralItem(startingPosition);
		map.getMap()[emptyPosition.getX()][emptyPosition.getY()] = new EmptyBox(emptyPosition);

		animal = new Animal(startingPosition);
		animal.setState(States.NEUTRAL);

		manager = new AnimalManager(animal, map);
		manager.interact();
	}

	@Test
	public void walkAfterInteraction() {
		manager.doSomething();
		assertTrue(animal.getPosition() == emptyPosition);
	}

}
