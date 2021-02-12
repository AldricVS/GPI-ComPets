package compets.tests.unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.States;
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.Wall;
import compets.engine.process.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalWallMovementTest {

	private static Animal animal;
	private static AnimalManager manager;
	private static Map map;
	private static Position startingPosition;

	@BeforeClass
	public static void init() {
		startingPosition = new Position(1, 1);
		
		map = new Map(3, 3);
		map.getMap()[0][0] = new Wall(new Position(0, 0));
		map.getMap()[0][1] = new Wall(new Position(0, 1));
		map.getMap()[0][2] = new Wall(new Position(0, 2));
		
		map.getMap()[1][0] = new Wall(new Position(1, 0));
		map.getMap()[1][1] = new EmptyBox(new Position(1, 1));
		map.getMap()[1][2] = new Wall(new Position(1, 2));
		
		map.getMap()[2][0] = new Wall(new Position(2, 0));
		map.getMap()[2][1] = new Wall(new Position(2, 1));
		map.getMap()[2][2] = new Wall(new Position(2, 1));

		animal = new Animal(startingPosition);
		animal.setStates(States.NEUTRAL);

		manager = new AnimalManager(animal, map);
	}

	@Test
	public void tryToWalk() {
		for (int i = 0; i < 15; i++) {
			manager.moveAnimal(manager.chooseNextMove());
		}
	}
	
	@AfterClass
	public static void dontBeStuckInWall() {
		assertTrue(animal.getPosition() == startingPosition);
	}

}
