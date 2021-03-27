package compets.tests.manual;

import java.util.Locale;
import java.util.ResourceBundle;

import compets.engine.data.behavior.AnimalBehaviorValuesRepository;
import compets.engine.data.behavior.BehaviorValues;

public class TestExplosion {
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("main_menu/menu", Locale.FRENCH);
		System.out.println(Locale.getDefault() == Locale.FRANCE);
		String string = bundle.getString("button_new_game");
		System.out.println(string);
	}
}
