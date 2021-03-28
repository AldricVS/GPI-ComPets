package compets.gui.elements;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import compets.engine.process.GameManager;

public class InfosPanel extends JPanel {

	private GamePanel context;
	private ActionsPanel actionsPanel;
	private StatsPanel statsPanel;

	public InfosPanel(GamePanel context, GameManager gameManager) {
		super();
		this.context = context;
		actionsPanel = new ActionsPanel(this);
		statsPanel = new StatsPanel(this);
		setLayout(new BorderLayout());
		setPreferredSize(GamePanel.INFOS_PANEL_DIMENSION);
		add(statsPanel, BorderLayout.NORTH);
		add(actionsPanel, BorderLayout.SOUTH);
	}

	public GamePanel getContext() {
		return context;
	}
	
	public void setButtonsEnabled(boolean enabled) {
		actionsPanel.setButtonsEnabled(enabled);
	}
	
	public void updateMessage() {
		actionsPanel.updateMessage();
	}
}
