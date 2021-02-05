package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Gauge {

	private int gauge;
	
	public Gauge() {
		this.gauge = 0;
	}

	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}
	
	public void incrementGauge(int gauge) {
		this.gauge++;
	}
	
	public void decrementGauge(int gauge) {
		this.gauge--;
	}

}
