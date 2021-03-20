package compets.tests.unit.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Position;
import compets.engine.process.SimulationSave;

/**
 * @author Maxence
 */
public class LoadCatTest {

	private static final String FILEPATH = "src/compets/tests/unit/file/testfiles/testFileLoadCat.txt";
	private static SimulationSave SimulationSave;
	
	private static Animal animal;
	private static Position position = new Position(3, 4);
	private static Gauge actionStatus = new Gauge(66);
	private static Gauge healthStatus = new Gauge(28);

	@BeforeClass
	public static void setupAnimalData() throws Exception {
		SimulationSave = new SimulationSave(FILEPATH);
		animal = new Dog(position);
		animal.getBehavior().getActionGauge().setValue(actionStatus.getValue());
		animal.getBehavior().getHealthGauge().setValue(healthStatus.getValue());
		File fileToLoad = new File(FILEPATH);
		if (!fileToLoad.exists()) {
			SimulationSave.save(animal);
		}
	}

	@Test
	public void loadAnimal() {
		Animal newAnimal = null;
		try {
			newAnimal = SimulationSave.loadAnimalData();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			fail();
		}
		assertTrue(newAnimal instanceof Cat);
		assertTrue(position.equals(newAnimal.getPosition()));
		assertEquals(healthStatus.getValue(), newAnimal.getBehavior().getHealthGauge().getValue());
		assertEquals(actionStatus.getValue(), newAnimal.getBehavior().getActionGauge().getValue());
	}

}
