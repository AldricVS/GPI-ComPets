package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Gauge {
	public static int DEFAULT_GAUGE = 50;
	public static final int MIN_GAUGE = 0;
	public static final int MAX_GAUGE = 100;

	private int gauge;

	public Gauge(int defaultGauge) {
		this.gauge = defaultGauge;
		clampValue();
		DEFAULT_GAUGE = this.gauge;
	}

	public int getValue() {
		return gauge;
	}

	public void setValue(int value) {
		this.gauge = value;
	}

	public void increment() {
		if (gauge < MAX_GAUGE) {
			this.gauge++;
		}
	}

	public void decrement() {
		if (gauge > MIN_GAUGE) {
			this.gauge--;
		}
	}

	public void addValue(int value) {
		this.gauge += value;
		clampValue();
	}
	
	public void subValue(int value) {
		this.gauge -= value;
		clampValue();
	}
	
	private void clampValue() {
		if(gauge > MAX_GAUGE) {
			gauge = MAX_GAUGE;
		}else if(gauge < MIN_GAUGE) {
			gauge = MIN_GAUGE;
		}
	}
}
