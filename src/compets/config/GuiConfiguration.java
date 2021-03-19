package compets.config;

public class GuiConfiguration {
	//Change those constants if needed
	public static final double RATIO = 16.0/9.0;
	public static final int HEIGHT = 700;
	public static final double STATS_PANEL_RATIO = 3.5/5.0;
	
	//Do not change those constants, they are modified with the ones above
	public static final int WIDTH = (int)(HEIGHT * RATIO);
	public static final int MAIN_PANEL_SIZE = HEIGHT < WIDTH ? HEIGHT : WIDTH;
}
