package compets.tests.unit.file;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.AnimalState;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Position;
import compets.engine.process.SimulationSave;

/**
 * @author Maxence
 */
public class SaveAnimalTest {
	
	private static final String FILEPATH = "src/compets/tests/unit/file/testfiles/testFile1.txt";
	private static SimulationSave SimulationSave;
	
	private static Animal animal;
	private static Position position = new Position(7, 6);
	private static Gauge healthStatus = new Gauge(70);
	private static Gauge actionStatus = new Gauge(30);
	private static AnimalState animalState = AnimalState.GOOD_ACTION;

	@BeforeClass
	public static void setupAnimalData() throws Exception {
		SimulationSave = new SimulationSave(FILEPATH);
		animal = new Dog(position);
		animal.getBehavior().getActionGauge().setValue(actionStatus.getValue());
		animal.getBehavior().getHealthGauge().setValue(healthStatus.getValue());
		animal.setState(animalState);
	}

	@Test
	public void saveAnimal() {
		try {
			SimulationSave.save(animal);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
