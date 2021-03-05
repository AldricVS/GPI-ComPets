package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.config.HealthConfig;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Gauge;
import compets.engine.data.animal.States;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.GoodItem;
import compets.engine.process.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalInteractGoodItem {

	private static Map map;
	private static Position position;
	private static Animal animal;
	private static AnimalManager manager;

	@BeforeClass
	public static void init() {
		map = new Map(1, 1);

		position = new Position(0, 0);
		map.getMap()[position.getX()][position.getY()] = new GoodItem(position);

		animal = new Animal(position);
		manager = new AnimalManager(animal, map);
	}
	
	@Before
	public void resetGaugeAndInteract() {
		animal.getBehavior().getActionGauge().setValue(Gauge.MAX_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		manager.resetAnimalState();
		manager.interact();
	}

	@Test
	public void behaviorChangedByInteraction() {
		assertEquals(States.GOOD_ACTION, animal.getStates());
		assertEquals(Gauge.DEFAULT_GAUGE - HealthConfig.DONE_NOTHING, animal.getBehavior().getHealthGauge().getValue());
	}

	@Test
	public void getReward() {
		assertTrue(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + HealthConfig.REWARD_FOR_GOOD_ACTION, animal.getBehavior().getHealthGauge().getValue());
	}

	@Test
	public void dontGetPunish() {
		assertFalse(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE - HealthConfig.PUNISH_FOR_GOOD_ACTION, animal.getBehavior().getHealthGauge().getValue());
	}

}
