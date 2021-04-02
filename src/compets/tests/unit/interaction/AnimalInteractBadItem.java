package compets.tests.unit.interaction;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorValues;
import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.data.map.item.BadItem;
import compets.engine.process.animal.AnimalManager;

/**
 * @author Maxence
 */
public class AnimalInteractBadItem {

	private static Map map;
	private static Position position;
	private static Animal animal;
	private static AnimalManager manager;

	@BeforeClass
	public static void init() {
		map = new Map(1, 1);

		position = new Position(0, 0);
		map.getMap()[position.getX()][position.getY()] = new BadItem(position);
	}

	private void resetGaugeAndInteract() {
		animal.getBehavior().getActionGauge().setValue(Gauge.MIN_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		manager.resetAnimalState();
		manager.interact();
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
	public void behaviorChangedByInteraction() {
		resetGaugeAndInteract();
		assertEquals(AnimalState.BAD_ACTION, animal.getState());
	}

	@After
	public void dontGetReward() {
		resetGaugeAndInteract();
		assertFalse(manager.reward());
		assertEquals(Gauge.MIN_GAUGE, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}

	@After
	public void getPunish() {
		resetGaugeAndInteract();
		assertTrue(manager.punish());
		assertEquals(Gauge.MIN_GAUGE + manager.getRepoValue(BehaviorValues.ACTION_BAD_PUNISHED),
				animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}
}
