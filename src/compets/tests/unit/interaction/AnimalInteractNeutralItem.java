package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorValues;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.animal.Cat;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.NeutralItem;
import compets.engine.process.animal.AnimalManager;

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
	}

	private void resetGauge() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		manager.resetAnimalState();
	}

	@Test
	public void testDogInteraction() {
		animal = new Dog(position);
		manager = new AnimalManager(animal, map);
	}

	@Test
	public void testCatInteraction() {
		animal = new Cat(position);
		manager = new AnimalManager(animal, map);
	}

	@Test
	public void testFoxInteraction() {
		animal = new Fox(position);
		manager = new AnimalManager(animal, map);
	}

	@After
	public void dontGetReward() {
		resetGauge();
		animal.setState(AnimalState.BAD_ACTION);
		assertFalse(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_BAD_REWARDED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}

	@After
	public void getPunish() {
		resetGauge();
		animal.setState(AnimalState.BAD_ACTION);
		assertTrue(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_BAD_PUNISHED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}

	@After
	public void getReward() {
		resetGauge();
		animal.setState(AnimalState.GOOD_ACTION);
		assertTrue(manager.reward());
		animal.getBehavior().getActionGauge();
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_GOOD_REWARDED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.HEALTH_REWARD_FOR_GOOD_ACTION),
				animal.getBehavior().getHealthGauge().getValue());
	}

	@After
	public void dontGetPunish() {
		resetGauge();
		animal.setState(AnimalState.GOOD_ACTION);
		assertFalse(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_GOOD_PUNISHED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.HEALTH_PUNISH_FOR_GOOD_ACTION),
				animal.getBehavior().getHealthGauge().getValue());
	}

}
