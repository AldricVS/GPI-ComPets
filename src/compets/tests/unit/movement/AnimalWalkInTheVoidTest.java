package compets.tests.unit.movement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.States;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalWalkInTheVoidTest {

	private static Animal animal;
	private static AnimalManager manager;
	private static Map map;
	private static Position startingPosition;

	@BeforeClass
	public static void init() {
		startingPosition = new Position(0, 0);
		
		map = new Map(1, 1);
		map.getMap()[0][0] = new EmptyBox(new Position(0, 0));

		animal = new Animal(startingPosition);
		animal.setState(States.NEUTRAL);

		manager = new AnimalManager(animal, map);
		
		for (int i = 0; i < 10; i++) {
			manager.moveAnimal(manager.chooseNextMove());
		}
	}

	@Test
	public void tryToWalk() {
		assertTrue(animal.getPosition() == startingPosition);
	}

}
