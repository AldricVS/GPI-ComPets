package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.constants.ActionModifValues;
import compets.engine.data.constants.HealthModifValues;
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
		
		animal = new Dog(position);
		manager = new AnimalManager(animal, map);
	}

	@Before
	public void resetGauge() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		manager.resetAnimalState();
	}

	@Test
	public void dontGetReward() {
		animal.setState(AnimalState.BAD_ACTION);
		assertFalse(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.BAD_ACTION_REWARDED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}
	
	@Test
	public void getPunish() {
		animal.setState(AnimalState.BAD_ACTION);
		assertTrue(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.BAD_ACTION_PUNISHED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}
	
	@Test
	public void getReward() {
		animal.setState(AnimalState.GOOD_ACTION);
		assertTrue(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.GOOD_ACTION_REWARDED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + HealthModifValues.REWARD_FOR_GOOD_ACTION, animal.getBehavior().getHealthGauge().getValue());
	}
	
	@Test
	public void dontGetPunish() {
		animal.setState(AnimalState.GOOD_ACTION);
		assertFalse(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.GOOD_ACTION_PUNISHED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + HealthModifValues.PUNISH_FOR_GOOD_ACTION, animal.getBehavior().getHealthGauge().getValue());
	}

}
