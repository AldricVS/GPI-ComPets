package compets.tests.manual;

import compets.engine.data.behavior.AnimalBehaviorValuesRepository;
import compets.engine.data.behavior.BehaviorValues;

public class TestExplosion {
	public static void main(String[] args) {
		AnimalBehaviorValuesRepository repository = new AnimalBehaviorValuesRepository();
		int i = repository.getValue(BehaviorValues.ACTION_BAD);
		System.out.println(i);
	}
}
