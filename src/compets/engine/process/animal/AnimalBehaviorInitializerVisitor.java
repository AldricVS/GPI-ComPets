package compets.engine.process.animal;
import compets.engine.data.animal.Cat;
import compets.engine.data.animal.Dog;
import compets.engine.data.animal.Fox;
import compets.engine.data.behavior.AnimalBehaviorValuesRepository;
import compets.engine.data.behavior.BehaviorValues;
import compets.engine.process.visitor.AnimalVisitor;

public class AnimalBehaviorInitializerVisitor implements AnimalVisitor<AnimalBehaviorValuesRepository> {

	@Override
	public AnimalBehaviorValuesRepository visit(Dog dog) {
		AnimalBehaviorValuesRepository repository = new AnimalBehaviorValuesRepository();
		repository.addValue(BehaviorValues.ACTION_BAD, -1);
		repository.addValue(BehaviorValues.ACTION_GOOD, 2);
		repository.addValue(BehaviorValues.ACTION_GOOD_REWARDED, 2);
		repository.addValue(BehaviorValues.ACTION_GOOD_PUNISHED, -2);
		repository.addValue(BehaviorValues.ACTION_BAD_REWARDED, -2);
		repository.addValue(BehaviorValues.ACTION_BAD_PUNISHED, 2);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_REWARDED, -2);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_PUNISHED, -2);
		
		repository.addValue(BehaviorValues.HEALTH_DONE_NOTHING, 1);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_NOTHING, -8);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_GOOD_ACTION, -15);
		repository.addValue(BehaviorValues.HEALTH_REWARD_FOR_GOOD_ACTION, 3);
		repository.addValue(BehaviorValues.HEALTH_GOOD_ACTION_NOT_REWARDED, -3);

		return repository;
	}

	@Override
	public AnimalBehaviorValuesRepository visit(Cat cat) {
		AnimalBehaviorValuesRepository repository = new AnimalBehaviorValuesRepository();
		repository.addValue(BehaviorValues.ACTION_BAD, -2);
		repository.addValue(BehaviorValues.ACTION_GOOD, 1);
		repository.addValue(BehaviorValues.ACTION_GOOD_REWARDED, 1);
		repository.addValue(BehaviorValues.ACTION_GOOD_PUNISHED, -7);
		repository.addValue(BehaviorValues.ACTION_BAD_REWARDED, -3);
		repository.addValue(BehaviorValues.ACTION_BAD_PUNISHED, 3);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_REWARDED, -2);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_PUNISHED, -8);
		
		repository.addValue(BehaviorValues.HEALTH_DONE_NOTHING, 1);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_NOTHING, -8);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_GOOD_ACTION, -20);
		repository.addValue(BehaviorValues.HEALTH_REWARD_FOR_GOOD_ACTION, 2);
		repository.addValue(BehaviorValues.HEALTH_GOOD_ACTION_NOT_REWARDED, -2);

		return repository;
	}

	@Override
	public AnimalBehaviorValuesRepository visit(Fox fox) {
		AnimalBehaviorValuesRepository repository = new AnimalBehaviorValuesRepository();
		repository.addValue(BehaviorValues.ACTION_BAD, -2);
		repository.addValue(BehaviorValues.ACTION_GOOD, 1);
		repository.addValue(BehaviorValues.ACTION_GOOD_REWARDED, 1);
		repository.addValue(BehaviorValues.ACTION_GOOD_PUNISHED, -7);
		repository.addValue(BehaviorValues.ACTION_BAD_REWARDED, -3);
		repository.addValue(BehaviorValues.ACTION_BAD_PUNISHED, 3);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_REWARDED, -2);
		repository.addValue(BehaviorValues.ACTION_NEUTRAL_PUNISHED, -8);
		
		repository.addValue(BehaviorValues.HEALTH_DONE_NOTHING, 1);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_NOTHING, -8);
		repository.addValue(BehaviorValues.HEALTH_PUNISH_FOR_GOOD_ACTION, -20);
		repository.addValue(BehaviorValues.HEALTH_REWARD_FOR_GOOD_ACTION, 2);
		repository.addValue(BehaviorValues.HEALTH_GOOD_ACTION_NOT_REWARDED, -2);
		
		return repository;
	}

}
