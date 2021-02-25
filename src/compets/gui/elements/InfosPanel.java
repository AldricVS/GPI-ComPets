package compets.gui.elements;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class InfosPanel extends JPanel {

	private MainGui context;
	private ButtonsPanel buttonsPanel;
	private StatsPanel statsPanel;

	public InfosPanel(MainGui context) {
		super();
		this.context = context;
		buttonsPanel = new ButtonsPanel(this);
		statsPanel = new StatsPanel(this);
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.INFOS_PANEL_DIMENSION);
		add(statsPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public MainGui getContext() {
		return context;
	}
	
	public void setButtonsEnabled(boolean enabled) {
		buttonsPanel.setButtonsEnabled(enabled);
	}
}
