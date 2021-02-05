package compets.engine.data.animal;

/**
 * @author Maxence
 */
public class Behavior {

	private Gauge deedGauge;
	
	public Behavior() {
		this.deedGauge = new Gauge();
	}

	public Gauge getActionGauge() {
		return deedGauge;
	}

}
