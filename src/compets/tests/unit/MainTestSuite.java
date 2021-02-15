package compets.tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import compets.tests.unit.interaction.*;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalWallMovementTest.class,
	AnimalWalkInTheVoidTest.class,
	AnimalInteractGoodItem.class,
	AnimalInteractNeutralItem.class,
	AnimalInteractBadItem.class,
})
public class MainTestSuite {

}
