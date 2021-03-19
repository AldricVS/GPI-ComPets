package compets.tests.unit.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Gauge;
import compets.engine.data.map.Position;
import compets.engine.process.SimulationSave;

/**
 * @author Maxence
 */
public class SaveAnimalTest {

	private static final String FILEPATH = "src/compets/tests/unit/file/testfiles/testFileSave.txt";
	private static final String FILEPATH_COMPARE = "src/compets/tests/unit/file/testfiles/testFileSaveCompare.txt";
	private static SimulationSave SimulationSave;

	private static Animal animal;
	private static Position position = new Position(7, 6);
	private static Gauge healthStatus = new Gauge(70);
	private static Gauge actionStatus = new Gauge(30);

	@BeforeClass
	public static void setupAnimalData() throws Exception {
		SimulationSave = new SimulationSave(FILEPATH);
		animal = new Dog(position);
		animal.getBehavior().getActionGauge().setValue(actionStatus.getValue());
		animal.getBehavior().getHealthGauge().setValue(healthStatus.getValue());
	}

	@Test
	public void saveAnimal() {
		try {
			SimulationSave.save(animal);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			fail();
		}
		// compare files
		File savedFile = new File(FILEPATH);
		File compareFile = new File(FILEPATH_COMPARE);
		assertTrue(compareFiles(savedFile, compareFile));
	}

	/**
	 * @param savedFile
	 * @param compareFile
	 */
	private boolean compareFiles(File savedFile, File compareFile) {
		if (!savedFile.exists() || !compareFile.exists()) {
			System.err.println("One the test files doesn't exist");
			return false;
		}
		List<String> savedFileLines = null;
		List<String> compareFileLines = null;
		try {
			savedFileLines = Files.readAllLines(savedFile.toPath());
			compareFileLines = Files.readAllLines(compareFile.toPath());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		if ((savedFileLines == null) || (compareFileLines == null)) {
			System.err.println("One the test files is empty");
			return false;
		}
		if (compareFileLines.size() != savedFileLines.size()) {
			System.err.println("The files are not the same size");
			return false;
		}
		String savedLine, compareLine;
		for (int index = 0; index < savedFileLines.size(); index++) {
			savedLine = savedFileLines.get(index);
			compareLine = compareFileLines.get(index);
			if (savedLine.compareTo(compareLine) != 0) {
				System.err.println("Files are not the same, difference at line " + (index + 1));
				return false;
			}
		}
		return true;
	}

}
