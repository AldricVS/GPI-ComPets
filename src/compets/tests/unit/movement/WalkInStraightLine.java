package compets.tests.unit.movement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Dog;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.Wall;
import compets.engine.process.animal.AnimalManager;

/**
 * @author Maxence
 */
public class WalkInStraightLine {

	private static Animal animal;
	private static AnimalManager manager;
	private static Map map;
	private static Position startingPosition;
	private static Position actualPosition;

	@BeforeClass
	public static void init() {
		startingPosition = new Position(0, 0);
		actualPosition = new Position(0, 0);
		
		map = new Map(1, 5);
		map.getMap()[0][0] = new EmptyBox(new Position(0, 0));
		map.getMap()[0][1] = new EmptyBox(new Position(0, 1));
		map.getMap()[0][2] = new EmptyBox(new Position(0, 2));
		map.getMap()[0][3] = new EmptyBox(new Position(0, 3));
		map.getMap()[0][4] = new EmptyBox(new Position(0, 4));

		animal = new Dog(startingPosition);
		manager = new AnimalManager(animal, map);
	}

	@Test
	public void walkInStraightLine() {
		manager.moveAnimal(manager.chooseNextMove());
		assertTrue(animal.getPosition() != actualPosition);
	}
	
	@After
	public void changePreviousPositionToWall() {
		map.getMap()[actualPosition.getX()][actualPosition.getY()] = new Wall(actualPosition);
		actualPosition = animal.getPosition();
	}

}
