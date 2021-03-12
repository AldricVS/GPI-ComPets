package compets.engine.data.behavior;

/**
 * Contains all different message that can be recieved from {@link compets.engine.process.AnimalStateHandler#checkState() AnimalStateHandler}
 */
public enum BehaviorStatesEnum {
	NO_MSG,
	ANIMAL_VERY_BAD_HEALTH,
	ANIMAL_BAD_HEALTH,
	ANIMAL_VERY_GOOD_HEALTH,
	ANIMAL_GOOD_HEALTH,
	ANIMAL_GOOD_BEHAVIOR,
	ANIMAL_BAD_BEHAVIOR,
	ANIMAL_VERY_BAD_BEHAVIOR,
	ANIMAL_EXEMPLARY	
}
