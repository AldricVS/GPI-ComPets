package compets.tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import compets.tests.unit.interaction.*;
import compets.tests.unit.movement.MovementTestSuite;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	InteractionTestSuite.class,
	MovementTestSuite.class,
})
public class MainTestSuite {

}
