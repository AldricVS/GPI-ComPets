package compets.engine.data.behavior;

import java.util.HashMap;

public class AnimalBehaviorValuesRepository {
	private HashMap<BehaviorValues, Integer> values = new HashMap<BehaviorValues, Integer>();
	
	public AnimalBehaviorValuesRepository() {
	}
	
	public void addValue(BehaviorValues behaviorValue, int value) {
		values.put(behaviorValue, value);
	}
	
	public int getValue(BehaviorValues behaviorValue) {
		return values.get(behaviorValue);
	}
}
