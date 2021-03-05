package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Behavior {

	private Gauge actionGauge;
	private Gauge healthGauge;
	
	public Behavior() {
		this.actionGauge = new Gauge();
		this.healthGauge = new Gauge();
	}

	public Gauge getActionGauge() {
		return actionGauge;
	}

	public Gauge getHealthGauge() {
		return healthGauge;
	}
}
