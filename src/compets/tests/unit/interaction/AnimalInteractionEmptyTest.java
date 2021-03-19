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
import compets.engine.data.map.EmptyBox;
import compets.engine.data.map.Map;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalManager;

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
	public void cantInteract() {
		manager.interact();
		assertEquals(AnimalState.NEUTRAL, animal.getState());
	}

	@Test
	public void dontGetReward() {
		assertFalse(manager.reward());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.NEUTRAL_REWARDED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE, animal.getBehavior().getHealthGauge().getValue());
	}

	@Test
	public void getPunish() {
		assertFalse(manager.punish());
		assertEquals(Gauge.DEFAULT_GAUGE + ActionModifValues.NEUTRAL_PUNISHED, animal.getBehavior().getActionGauge().getValue());
		assertEquals(Gauge.DEFAULT_GAUGE + HealthModifValues.PUNISH_FOR_NOTHING, animal.getBehavior().getHealthGauge().getValue());
	}

}
