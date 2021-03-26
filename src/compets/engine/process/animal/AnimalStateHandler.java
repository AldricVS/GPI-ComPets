package compets.engine.process.animal;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Behavior;
import compets.engine.data.animal.Gauge;
import compets.engine.data.behavior.BehaviorStatesEnum;

/**
 * Cette classe définit les conditions de l'animal selon sa barre de santé et sa barre de dressage
 * 
 * @author VIRAYIE Nathan
 *
 */
public class AnimalStateHandler {
	private Animal animal;

	public AnimalStateHandler(Animal animal) {
		this.animal = animal;
	}
	
	/**
	 * Verifie comment est traité l'animal par l'utilisateur
	 * 
	 * @return l'etat dans lequel est l'animal selon l'enum {@link BehaviorStatesEnum}
	 */
	public BehaviorStatesEnum checkState(){
		Behavior bh = animal.getBehavior();
		Gauge actionGauge = bh.getActionGauge();
		Gauge healthGauge = bh.getHealthGauge();
		
		int actionGaugeValue = actionGauge.getValue();
		int healthGaugeValue = healthGauge.getValue();
		
		//dressage exemplaire
		if (actionGaugeValue >= 90 && healthGaugeValue >= 95) {
			return BehaviorStatesEnum.ANIMAL_EXEMPLARY;
		}
		
		//animal en piteux état
		if ((actionGaugeValue <= 10) && (healthGaugeValue <= 10)) {
			return BehaviorStatesEnum.ANIMAL_PITIFUL;
		}
		
		//bon dressage
		if (actionGaugeValue >= 70) {
			 return BehaviorStatesEnum.ANIMAL_GOOD_BEHAVIOR;
		}
		
		//tres mauvaise santé
		if (healthGaugeValue <= 10) {
			return BehaviorStatesEnum.ANIMAL_VERY_BAD_HEALTH;
		}
		
		//tres mauvais dressage
		if (actionGaugeValue <= 10) {
			 return BehaviorStatesEnum.ANIMAL_VERY_BAD_BEHAVIOR;
		}
		
		//mauvaise santé
		if (healthGaugeValue <= 30) {
			return BehaviorStatesEnum.ANIMAL_BAD_HEALTH;
		}
		
		//mauvais dressage
		if (actionGaugeValue <= 30) {
			 return BehaviorStatesEnum.ANIMAL_BAD_BEHAVIOR;
		}
		
		//tres bonne santé
		if(healthGaugeValue >= 90) {
			return BehaviorStatesEnum.ANIMAL_VERY_GOOD_HEALTH;
		}
		
		//animal en bonne santé
		if(healthGaugeValue >= 70) {
			return BehaviorStatesEnum.ANIMAL_GOOD_HEALTH;
		}
	
		return BehaviorStatesEnum.NO_MSG;
	}
}
