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
public class AnimalBadBehaviorTagTest {

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
		fail("test en creation");
		animal.getBehavior().getActionGauge().setValue(Gauge.MIN_GAUGE);
		animal.getBehavior().getHealthGauge().setValue(Gauge.MIN_GAUGE);
		assertEquals(BehaviorStatesEnum.values()[1], handler.checkState());
	}
}
