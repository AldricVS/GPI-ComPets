package compets.tests.unit.states;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Main test Suite for checking {@link compets.engine.data.behavior.BehaviorStatesEnum BehaviorStatesEnums}
 * @author Maxence
 */
@RunWith(Suite.class)
@SuiteClasses({
	AnimalBadStatesMessageTest.class,
	AnimalGoodStatesMessageTest.class,
})
public class AnimalStatesTestSuite {
}
