package compets.tests.unit.states;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorStatesEnum;
import compets.engine.data.map.Position;
import compets.engine.process.AnimalStateHandler;

/**
 * @author Maxence
 */
public class AnimalBadStatesMessageTest {

	private static Position position;
	private static Animal animal;
	private static AnimalStateHandler handler;

	@BeforeClass
	public static void init() {
		position = new Position(0, 0);
		animal = new Animal(position);

		handler = new AnimalStateHandler(animal);
	}
	
	@Test
	public void messageVeryBadState() {
		animal.getBehavior().getActionGauge().setValue(Gauge.MIN_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.MIN_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_VERY_BAD_BEHAVIOR, handler.checkState());
	}
	
	@Test
	public void messageVeryBadHealth() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.MIN_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_VERY_BAD_HEALTH, handler.checkState());
	}
	
	@Test
	public void messageBadBehavior() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE / 2);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_BAD_BEHAVIOR, handler.checkState());
	}
}
