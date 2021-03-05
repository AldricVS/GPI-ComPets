package compets.gui.elements;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Gauge;
import compets.engine.process.AnimalManager;
import compets.gui.management.Historical;

public class StatsPanel extends JPanel {
	private static final Dimension GAUGE_PANEL_DIMENSION = new Dimension(MainGui.INFOS_PANEL_DIMENSION.width / 6, MainGui.INFOS_PANEL_DIMENSION.height);

	private InfosPanel infosPanel;

	private JPanel gaugePanel;
	private JProgressBar progressBar;
	private Gauge gauge = new Gauge();
	private LinkedList<Integer> historical = new LinkedList<Integer>();
	
//	private Historical historical = new Historical();

	private static final int TIME_INTERVAL = 40;
	private JPanel graphPanel;
	private XYSeries series;

	public StatsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		MainGui context = infosPanel.getContext();
		AnimalManager animalManager = context.getAnimalManager();
		Animal animal = animalManager.getAnimal();
		gauge = animal.getBehavior().getActionGauge();
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.STATS_PANEL_DIMENSION);
		setBackground(Color.GRAY);
		initGaugePanel();
		initGraphPanel();
	}

	private void initGaugePanel() {
		gaugePanel = new JPanel();
		gaugePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gaugePanel.setLayout(new BorderLayout());
		progressBar = new JProgressBar(JProgressBar.VERTICAL, Gauge.MIN_GAUGE, Gauge.MAX_GAUGE);
		progressBar.setValue(gauge.getValue());
		Dimension preferredSize = progressBar.getPreferredSize();
		// preferredSize.setSize(GAUGE_PANEL_DIMENSION.width, preferredSize.height);
		progressBar.setPreferredSize(preferredSize);
		gaugePanel.add(progressBar, BorderLayout.CENTER);
		gaugePanel.setPreferredSize(GAUGE_PANEL_DIMENSION);
		add(gaugePanel, BorderLayout.EAST);
	}

	private void initGraphPanel() {
		series = new XYSeries("Behavior");
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		xySeriesCollection.addSeries(series);
		JFreeChart xyChart = ChartFactory.createXYLineChart("Animal's behavior (historical)", "Behavior value", "Time", xySeriesCollection,
				PlotOrientation.VERTICAL, true, false, false);
		
		XYPlot xyPlot = xyChart.getXYPlot();

		// set behavior value (y axis) from 0 to 100
		NumberAxis yAxis = (NumberAxis) xyPlot.getRangeAxis();
		yAxis.setRange(Gauge.MIN_GAUGE, Gauge.MAX_GAUGE);
		yAxis.setTickUnit(new NumberTickUnit(5.0));
//
//		// hide the x axis
		NumberAxis xAxis = (NumberAxis) xyPlot.getDomainAxis();
		xAxis.setVisible(false);
		xAxis.setRange(0, TIME_INTERVAL);

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesPaint(0, Color.RED);
		xyPlot.setRenderer(renderer);

		for(int index = 0; index <= TIME_INTERVAL; index++){
			historical.addLast(50);
		}
		fillSeries();
		
		graphPanel = new ChartPanel(xyChart);
		add(graphPanel, BorderLayout.CENTER);
	}

	private void updateHistorical() {
		int value = gauge.getValue();
		historical.remove();
		historical.addLast(value);
		series.clear();
		fillSeries();
	}

	private void fillSeries() {
		for (int index = 0; index <= TIME_INTERVAL; index++) {
			series.add((double)index, (double)historical.get(index));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateHistorical();
		paintProgressBar();
//		displayList();
	}
	
	private void displayList() {
		for(int index = 0; index < TIME_INTERVAL; index++){
			System.out.print(historical.get(index) + " ");
		}
		System.out.println();
	}

	private void paintProgressBar() {
		int value = gauge.getValue();
		progressBar.setValue(value);
		Color gradientColorFromValue = gradientColorFromValue(value);
		progressBar.setForeground(gradientColorFromValue);
	}

	private Color gradientColorFromValue(int value) {
		// Interpolate green and red value depending on the value and the max / min of
		// the gauge
		int greenAmount = interpolate(0, 255, (double) value / (double) Gauge.MAX_GAUGE);
		int redAmount = 255 - greenAmount;
		return new Color(redAmount, greenAmount, 0);
	}

	private int interpolate(int start, int end, double amount) {
		if (amount <= Gauge.MIN_GAUGE) {
			return start;
		}
		if (amount >= Gauge.MAX_GAUGE) {
			return end;
		}
		return (int) (start + (end - start) * amount);
	}
}
