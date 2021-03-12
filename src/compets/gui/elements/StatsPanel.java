package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Gauge;
import compets.engine.process.AnimalManager;

public class StatsPanel extends JPanel {
	public static final Dimension GAUGE_PANEL_DIMENSION = new Dimension(GamePanel.INFOS_PANEL_DIMENSION.width / 7, GamePanel.INFOS_PANEL_DIMENSION.height);

	private InfosPanel infosPanel;

	// Gauges
	private JPanel gaugePanel;
	private GaugePanel actionGaugePanel;
	private GaugePanel healthGaugePanel;
	private Gauge actionGauge;
	private Gauge healthGauge;
	
	// Chart
	private static final int TIME_INTERVAL = 150;
	private JPanel graphPanel;
	private XYSeries actionDataSeries;
	private XYSeries healthDataSeries;
	private LinkedList<Integer> actionHistorical = new LinkedList<Integer>();
	private LinkedList<Integer> healthHistorical = new LinkedList<Integer>();

	public StatsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		GamePanel context = infosPanel.getContext();
		
		// Get behavior gauges
		AnimalManager animalManager = context.getAnimalManager();
		Animal animal = animalManager.getAnimal();
		actionGauge = animal.getBehavior().getActionGauge();
		healthGauge = animal.getBehavior().getHealthGauge();
		
		setLayout(new BorderLayout());
		setPreferredSize(GamePanel.STATS_PANEL_DIMENSION);
		setBackground(Color.GRAY);
		initGaugesPanel();
		initGraphPanel();
	}

	private void initGaugesPanel() {
		gaugePanel = new JPanel();
		gaugePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setHgap(5);
		gaugePanel.setLayout(gridLayout);
		
		actionGaugePanel = new GaugePanel("Behavior", actionGauge);
		healthGaugePanel = new GaugePanel("Well-being", healthGauge);
		
		gaugePanel.add(actionGaugePanel);
		gaugePanel.add(healthGaugePanel);
		gaugePanel.setPreferredSize(GAUGE_PANEL_DIMENSION);
		add(gaugePanel, BorderLayout.EAST);
	}

	private void initGraphPanel() {
		actionDataSeries = new XYSeries("Behavior");
		healthDataSeries = new XYSeries("Well-being");
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(actionDataSeries);
		xySeriesCollection.addSeries(healthDataSeries);
		JFreeChart xyChart = ChartFactory.createXYLineChart("Animal's behavior (historical)", "Behavior value", "Time", xySeriesCollection,
				PlotOrientation.VERTICAL, true, false, false);
		
		XYPlot xyPlot = xyChart.getXYPlot();

		// Set behavior value (y axis) from 0 to 100
		NumberAxis yAxis = (NumberAxis) xyPlot.getRangeAxis();
		yAxis.setRange(Gauge.MIN_GAUGE, Gauge.MAX_GAUGE);
		yAxis.setTickUnit(new NumberTickUnit(5.0));

		// Hide the x axis
		NumberAxis xAxis = (NumberAxis) xyPlot.getDomainAxis();
		xAxis.setVisible(false);
		xAxis.setRange(0, TIME_INTERVAL);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		xyPlot.setRenderer(renderer);

		for(int index = 0; index <= TIME_INTERVAL; index++){
			actionHistorical.add(actionGauge.getValue());
			healthHistorical.add(healthGauge.getValue());
		}
		
		graphPanel = new ChartPanel(xyChart);
		add(graphPanel, BorderLayout.CENTER);
	}

	private void updateHistorical() {
		int actionValue = actionGauge.getValue();
		int healthValue = healthGauge.getValue();
		
		actionHistorical.remove();
		actionHistorical.addLast(actionValue);
		actionDataSeries.clear();
		
		healthHistorical.remove();
		healthHistorical.addLast(healthValue);
		healthDataSeries.clear();
		
		fillSeries();
	}

	private void fillSeries() {
		for (int index = 0; index <= TIME_INTERVAL; index++) {
			actionDataSeries.add((double)index, (double)actionHistorical.get(index));
			healthDataSeries.add((double)index, (double)healthHistorical.get(index));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateHistorical();
	}
}
