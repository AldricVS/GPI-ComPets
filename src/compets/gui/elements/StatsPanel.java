package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class StatsPanel extends JPanel{
	
	private InfosPanel infosPanel;

	public StatsPanel(InfosPanel infosPanel) {
		super();
		this.infosPanel = infosPanel;
		setLayout(new BorderLayout());
		setPreferredSize(MainGui.STATS_PANEL_DIMENSION);
		setBackground(Color.GRAY);
	}
}
