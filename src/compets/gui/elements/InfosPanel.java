package compets.gui.elements;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class InfosPanel extends JPanel {

	private MainGui context;
	private ButtonsPanel buttonsPanel = new ButtonsPanel(this);
	private StatsPanel statsPanel = new StatsPanel(this);

	public InfosPanel(MainGui context) {
		super();
		this.context = context;
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.INFOS_PANEL_DIMENSION);
		add(statsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public MainGui getContext() {
		return context;
	}
}
