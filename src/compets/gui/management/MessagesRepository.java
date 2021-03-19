package compets.gui.management;

import java.util.HashMap;

import compets.engine.data.behavior.BehaviorStatesEnum;

/**
 * Stores all messages to show to user depending on the
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class MessagesRepository {

	private HashMap<BehaviorStatesEnum, String> messages = new HashMap<BehaviorStatesEnum, String>();

	public MessagesRepository() {
		// a message for each of the messages states
		messages.put(BehaviorStatesEnum.NO_MSG, "<html><p>Nothing to say....</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_PITIFUL, "<html><p style=\"color:red;\">The Animal is in pitiful state, are you doing it on purpose ?</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_BAD_HEALTH,
				"<html><p style=\"color:red;\">The animal is in really bad shape, you must stop to mistreat him !</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_BAD_HEALTH, "<html><p style=\"color:red;\">The animal is in bad shape, be careful to not hurt him.</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_GOOD_HEALTH, "<html><p style=\"color:green;\">The animal is in very good shape, WONDERFUL !</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_GOOD_HEALTH, "<html><p style=\"color:green;\">The animal is in good shape, keep be nice with him !</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_GOOD_BEHAVIOR,
				"<html><p style=\"color:green;\">Your animal is well behaved, all masters will want it !</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_BAD_BEHAVIOR, "<html><p style=\"color:red;\">Be careful with your animal, he could get mean...</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_BAD_BEHAVIOR,
				"<html><p style=\"color:red;\">Your animal is a real demon ! You must do something NOW !</p></html>");
		messages.put(BehaviorStatesEnum.ANIMAL_EXEMPLARY,
				"<html><p style=\"color:green;\">Your animal is the most exemplary in the world, awesome !</p></html>");
	}

	/**
	 * Get the message in html mode depending on the state enum value received
	 * 
	 * @param currentState
	 * @return
	 */
	public String getAnimalStateMessage(BehaviorStatesEnum currentState) {
		String message = messages.get(currentState);
		if(message == null) {
			return "";
		}else {
			return message;
		}
	}
}
