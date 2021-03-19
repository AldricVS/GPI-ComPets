package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Behavior {

	private Gauge actionGauge;
	private Gauge healthGauge;
	
	public Behavior(int defaultaction, int defaulthealth) {
		this.actionGauge = new Gauge(defaultaction);
		this.healthGauge = new Gauge(defaulthealth);
	}

	public Gauge getActionGauge() {
		return actionGauge;
	}

	public Gauge getHealthGauge() {
		return healthGauge;
	}

	@Override
	public String toString() {
		return "Behavior [actionGauge=" + actionGauge.getValue() + ", healthGauge=" + healthGauge.getValue() + "]";
	}
}
