package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import compets.engine.data.animal.Animal;
import compets.engine.data.animal.Gauge;
import compets.engine.process.AnimalManager;

public class StatsPanel extends JPanel {
	private static final Dimension GAUGE_PANEL_DIMENSION = new Dimension(MainGui.INFOS_PANEL_DIMENSION.width / 3, MainGui.INFOS_PANEL_DIMENSION.height);
//	private static final Dimension GAUGE_DIMENSION = new Dimension(GAUGE_PANEL_DIMENSION.width / 3, MainGui.INFOS_PANEL_DIMENSION.height / 2);

	private InfosPanel infosPanel;

	private JPanel gaugePanel;
	private JPanel graphPanel;
	private JProgressBar progressBar;

	private Gauge gauge = new Gauge();

	public StatsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		MainGui context = infosPanel.getContext();
		AnimalManager animalManager = context.getAnimalManager();
		Animal animal = animalManager.getAnimal();
		gauge = animal.getBehavior().getActionGauge();
		gauge.setValue(100);
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.STATS_PANEL_DIMENSION);
		setBackground(Color.GRAY);
		initGaugePanel();
		initGraphPanel();
	}

	private void initGraphPanel() {
		graphPanel = new JPanel();

	}

	private void initGaugePanel() {
		gaugePanel = new JPanel();
		gaugePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		gaugePanel.setLayout(new BorderLayout());
		progressBar = new JProgressBar(JProgressBar.VERTICAL, Gauge.MIN_GAUGE, Gauge.MAX_GAUGE);
		progressBar.setValue(gauge.getValue());

		Dimension preferredSize = progressBar.getPreferredSize();
		preferredSize.setSize(GAUGE_PANEL_DIMENSION.width / 4, preferredSize.height);
		progressBar.setPreferredSize(preferredSize);
		gaugePanel.add(progressBar, BorderLayout.EAST);
		gaugePanel.setPreferredSize(GAUGE_PANEL_DIMENSION);
		add(gaugePanel, BorderLayout.EAST);
	}

	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintProgressBar();
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
		int greenAmount = interpolate(0, 255, (double)value / (double)Gauge.MAX_GAUGE);
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
