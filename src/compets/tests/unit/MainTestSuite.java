package compets.tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import compets.tests.unit.interaction.AnimalInteractBadItem;
import compets.tests.unit.interaction.AnimalInteractGoodItem;
import compets.tests.unit.interaction.AnimalInteractNeutralItem;

/**
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalWallMovementTest.class,
	AnimalInteractGoodItem.class,
	AnimalInteractNeutralItem.class,
	AnimalInteractBadItem.class,
})
public class MainTestSuite {

}
