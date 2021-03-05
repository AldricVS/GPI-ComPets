package compets.gui.management;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import compets.engine.data.animal.Gauge;

public class Historical {
	public static final int HISTORICAL_CAPACITY = 10;
	
	private List<Integer> values = new LinkedList<Integer>();

	public Historical() {
		for(int index = 0; index < HISTORICAL_CAPACITY; index++){
			values.add(Gauge.DEFAULT_GAUGE);
		}
	}

	public List<Integer> getValues() {
		return values;
	}

	public void appendValue(int value) {
		values.remove(0);
		values.add(value);
	}

}
