package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Gauge {
	public static final int DEFAULT_GAUGE = 50;
	public static final int MIN_GAUGE = 0;
	public static final int MAX_GAUGE = 100;

	private int gauge;
	
	public Gauge() {
		this.gauge = DEFAULT_GAUGE;
	}

	public int getValue() {
		return gauge;
	}

	public void setValue(int value) {
		this.gauge = value;
	}
	
	public void increment() {
		if(gauge < MAX_GAUGE) {
			this.gauge++;	
		}
	}
	
	public void decrement() {
		if(gauge > MIN_GAUGE) {
			this.gauge--;
		}
	}

}
