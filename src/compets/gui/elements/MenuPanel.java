package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import compets.config.GuiConfiguration;

public class MenuPanel extends JPanel {
	private static final Dimension MENU_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);
	
	private static final Dimension TITLE_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height / 5);
	private static final Dimension IMAGE_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height);
	private static final Dimension BUTTONS_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height / 5);
	
	private MainGui mainGui;
	
	private boolean canDisplayAnimal = true;
	
	public MenuPanel(MainGui mainGui) {
		this.mainGui = mainGui;
		setLayout(new BorderLayout());
		setPreferredSize(MENU_DIMENSION);
		init();
	}

	private void init() {
		initTitlePanel();
		initCenterPanel();
		initButtonsPanel();
	}

	private void initTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("Compet's", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, IMAGE_PART_DIMENSION.width / 10));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		titlePanel.setPreferredSize(TITLE_PART_DIMENSION);
		this.add(titlePanel, BorderLayout.NORTH);
	}
	private void initCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon("images/Animal/Animal_ALL.png"));
		centerPanel.add(imageLabel, BorderLayout.CENTER);
		centerPanel.setPreferredSize(IMAGE_PART_DIMENSION);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	private void initButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(BUTTONS_PART_DIMENSION);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}
}
