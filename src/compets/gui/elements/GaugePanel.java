package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import compets.engine.data.animal.Gauge;

public class GaugePanel extends JPanel {
	private JProgressBar progressBar = new JProgressBar(JProgressBar.VERTICAL, Gauge.MIN_GAUGE, Gauge.MAX_GAUGE);
	private Gauge gauge;
	private JLabel titleLabel;

	public GaugePanel(String title, Gauge gauge) {
		setLayout(new BorderLayout());
		this.gauge = gauge;
		titleLabel = new JLabel("<html><strong>" + title + "</strong></html>", SwingConstants.CENTER);
		titleLabel.setFont(new Font("arial", Font.PLAIN, StatsPanel.GAUGE_PANEL_DIMENSION.width / 8));

		add(titleLabel, BorderLayout.SOUTH);
		add(progressBar, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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
