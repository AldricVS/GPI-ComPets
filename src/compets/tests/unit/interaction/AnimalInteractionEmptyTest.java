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
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.animal.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalInteractionEmptyTest {

	private static Map map;
	private static Position position;
	private static Animal animal;
	private static AnimalManager manager;

	@BeforeClass
	public static void init() {
		map = new Map(1, 1);

		position = new Position(0, 0);
		map.getMap()[position.getX()][position.getY()] = new EmptyBox(position);
	}

	public void resetGauge() {
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
	public void cantInteract() {
		resetGauge();
		manager.interact();
		assertEquals(AnimalState.NEUTRAL, animal.getState());
	}

	@After
	public void dontGetReward() {
		resetGauge();
		assertFalse(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_NEUTRAL_REWARDED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}

	@After
	public void getPunish() {
		resetGauge();
		assertFalse(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_NEUTRAL_PUNISHED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + manager.getRepoValue(BehaviorValues.HEALTH_PUNISH_FOR_NOTHING),
				animal.getBehavior().getHealthGauge().getValue());
	}

}
