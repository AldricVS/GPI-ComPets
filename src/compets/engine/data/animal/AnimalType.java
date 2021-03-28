package compets.engine.data.animal;

import java.util.ResourceBundle;

public enum AnimalType {
	DOG,
	CAT,
	FOX;

	@Override
	public String toString() {
		ResourceBundle bundle = ResourceBundle.getBundle("animal_names/animal_names");
		return bundle.getString(this.name());
	}
}
