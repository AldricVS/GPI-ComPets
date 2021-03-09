package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.NeutralItem;
import compets.engine.process.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalInteractNeutralItem {
	
	private static Map map;
	private static Position position;
	private static Animal animal;
	private static AnimalManager manager;

	@BeforeClass
	public static void init() {
		map = new Map(1, 1);
		
		position = new Position(0, 0);
		map.getMap()[position.getX()][position.getY()] = new NeutralItem(position);
		
		animal = new Animal(position);
		manager = new AnimalManager(animal, map);
	}

	@Test
	public void dontGetReward() {
		animal.setState(AnimalState.BAD_ACTION);
		assertFalse(manager.reward());
	}
	
	@Test
	public void getPunish() {
		animal.setState(AnimalState.BAD_ACTION);
		assertTrue(manager.punish());
	}
	
	@Test
	public void getReward() {
		animal.setState(AnimalState.GOOD_ACTION);
		assertTrue(manager.reward());
	}
	
	@Test
	public void dontGetPunish() {
		animal.setState(AnimalState.GOOD_ACTION);
		assertFalse(manager.punish());
	}

}
