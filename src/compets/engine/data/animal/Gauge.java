package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Gauge {
	private static final int MIN_GAUGE = 0;
	private static final int MAX_GAUGE = 100;

	private int gauge;
	
	public Gauge() {
		this.gauge = MIN_GAUGE;
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
		if(gauge > 0) {
			this.gauge--;
		}
	}

}
