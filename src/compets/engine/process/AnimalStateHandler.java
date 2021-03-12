package compets.engine.process;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Behavior;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorStatesEnum;

public class AnimalStateHandler {
	private Animal animal;

	public AnimalStateHandler(Animal animal) {
		this.animal = animal;
	}
	
	/**
	 * Verifie dans quel état est la santé et le dressage de l'animal
	 * 
	 * @return l'etat dans lequel est l'animal selon l'enum {@code BehaviorStatesEnum}
	 */
	public BehaviorStatesEnum checkState(){
		Behavior bh = animal.getBehavior();
		Gauge actionGauge = bh.getActionGauge();
		Gauge healthGauge = bh.getHealthGauge();
		
		int actionGaugeValue = actionGauge.getValue();
		int healthGaugeValue = healthGauge.getValue();
		
		if (actionGaugeValue >= 90 && healthGaugeValue >= 95) {
			return BehaviorStatesEnum.ANIMAL_EXEMPLARY;
		}
		
		if (actionGaugeValue >= 80) {
			 return BehaviorStatesEnum.ANIMAL_GOOD_BEHAVIOR;
		}
		
		if (healthGaugeValue <= 10) {
			return BehaviorStatesEnum.ANIMAL_VERY_BAD_HEALTH;
		}
		
		if (healthGaugeValue <= 30) {
			return BehaviorStatesEnum.ANIMAL_BAD_HEALTH;
		}
		
		if (actionGaugeValue <= 10) {
			 return BehaviorStatesEnum.ANIMAL_VERY_BAD_BEHAVIOR;
		}
		
		if (actionGaugeValue <= 30) {
			 return BehaviorStatesEnum.ANIMAL_BAD_BEHAVIOR;
		}
		
		if(healthGaugeValue >= 90) {
			return BehaviorStatesEnum.ANIMAL_VERY_GOOD_HEALTH;
		}
		
		if(healthGaugeValue >= 70) {
			return BehaviorStatesEnum.ANIMAL_GOOD_HEALTH;
		}
	
		return BehaviorStatesEnum.NO_MSG;
	}
}
