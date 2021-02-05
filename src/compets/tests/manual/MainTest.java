package compets.tests.manual;

import compets.gui.MainGui;

public class MainTest {

	public static void main(String[] args) {
		MainGui mainGui = new MainGui();
		Thread thread = new Thread(mainGui);
		thread.start();
	}

}
