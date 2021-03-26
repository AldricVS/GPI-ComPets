package compets.tests.unit.states;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorStatesEnum;
import compets.engine.data.map.Position;
import compets.engine.process.animal.AnimalStateHandler;

/**
 * Check that all condition of a greatly treated animal get the appropriated message
 * @author Maxence
 */
public class AnimalGoodStatesMessageTest {

	private static Position position;
	private static Animal animal;
	private static AnimalStateHandler handler;

	@BeforeClass
	public static void init() {
		position = new Position(0, 0);
		animal = new Dog(position);

		handler = new AnimalStateHandler(animal);
	}
	
	@Test
	public void messageNeutral() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		assertEquals(BehaviorStatesEnum.NO_MSG, handler.checkState());
	}
	
	@Test
	public void messageVeryGoodState() {
		animal.getBehavior().getActionGauge().setValue(Gauge.MAX_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.MAX_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_EXEMPLARY, handler.checkState());
	}
	
	@Test
	public void messageVeryGoodHealth() {
		animal.getBehavior().getActionGauge().setValue(Gauge.DEFAULT_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.MAX_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_VERY_GOOD_HEALTH, handler.checkState());
	}
	
	@Test
	public void messageGoodBehavior() {
		animal.getBehavior().getActionGauge().setValue(3 * Gauge.DEFAULT_GAUGE / 2);
		animal.getBehavior().getHealthGauge().setValue(Gauge.DEFAULT_GAUGE);
		assertEquals(BehaviorStatesEnum.ANIMAL_GOOD_BEHAVIOR, handler.checkState());
	}
}
