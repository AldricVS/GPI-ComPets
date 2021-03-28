package compets.gui.management;

import java.util.HashMap;
import java.util.ResourceBundle;

import compets.engine.data.behavior.BehaviorStatesEnum;

/**
 * Stores all messages to show to user depending on the
 * 
 * @author Aldric Vitali Silvestre <aldric.vitali@outlook.fr>
 */
public class MessagesRepository {

	private HashMap<BehaviorStatesEnum, String> messages = new HashMap<BehaviorStatesEnum, String>();

	public MessagesRepository(ResourceBundle resources) {
		// a message for each of the messages states
		messages.put(BehaviorStatesEnum.NO_MSG, resources.getString("message_no_msg"));
		messages.put(BehaviorStatesEnum.ANIMAL_PITIFUL, resources.getString("message_pitiful"));
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_BAD_HEALTH, resources.getString("message_very_bad_health"));
		messages.put(BehaviorStatesEnum.ANIMAL_BAD_HEALTH, resources.getString("message_bad_health"));
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_GOOD_HEALTH, resources.getString("message_very_good_health"));
		messages.put(BehaviorStatesEnum.ANIMAL_GOOD_HEALTH, resources.getString("message_good_health"));
		messages.put(BehaviorStatesEnum.ANIMAL_GOOD_BEHAVIOR, resources.getString("message_good_behavior"));
		messages.put(BehaviorStatesEnum.ANIMAL_BAD_BEHAVIOR, resources.getString("message_bad_behavior"));
		messages.put(BehaviorStatesEnum.ANIMAL_VERY_BAD_BEHAVIOR, resources.getString("message_very_bad_behavior"));
		messages.put(BehaviorStatesEnum.ANIMAL_EXEMPLARY, resources.getString("message_exemplary"));
	}

	/**
	 * Get the message in html mode depending on the state enum value received
	 * 
	 * @param currentState
	 * @return
	 */
	public String getAnimalStateMessage(BehaviorStatesEnum currentState) {
		String message = messages.get(currentState);
		if (message == null) {
			return "";
		} else {
			return message;
		}
	}
}
