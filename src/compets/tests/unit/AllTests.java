package compets.tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import compets.tests.unit.file.FileTestSuite;
import compets.tests.unit.interaction.InteractionTestSuite;
import compets.tests.unit.movement.MovementTestSuite;
import compets.tests.unit.states.AnimalStatesTestSuite;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	InteractionTestSuite.class,
	AnimalStatesTestSuite.class,
	MovementTestSuite.class,
	FileTestSuite.class,
})
public class AllTests {

}
