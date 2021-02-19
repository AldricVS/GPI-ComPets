package compets.tests.unit.movement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalMoveInTheVoidTest.class,
	AnimalMoveIntoWallTest.class,
	WalkInStraightLine.class,
	AnimalMoveAfterInteraction.class,
})
public class MovementTestSuite {

}
