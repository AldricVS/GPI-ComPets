package compets.tests.unit.interaction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalInteractBadItem.class,
	AnimalInteractGoodItem.class,
	AnimalInteractionEmptyTest.class,
	AnimalInteractNeutralItem.class
})
public class InteractionTestSuite {

}
