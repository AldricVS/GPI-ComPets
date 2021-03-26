package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorValues;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.GoodItem;
import compets.engine.process.animal.AnimalManager;

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

		animal = new Dog(position);
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
		assertEquals(AnimalState.GOOD_ACTION, animal.getState());
	}

	@Test
	public void getReward() {
		assertTrue(manager.reward());
		assertEquals(Gauge.MAX_GAUGE, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.HEALTH_REWARD_FOR_GOOD_ACTION), animal.getBehavior().getHealthGauge().getValue());
	}

	@Test
	public void dontGetPunish() {
		assertFalse(manager.punish());
		assertEquals(Gauge.MAX_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_GOOD_PUNISHED), animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.HEALTH_PUNISH_FOR_GOOD_ACTION), animal.getBehavior().getHealthGauge().getValue());
	}

}
