package compets.tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalWallMovementTest.class,
	AnimalInteractBadItem.class,
	AnimalInteractGoodItem.class,
})
public class MainTestSuite {

}
