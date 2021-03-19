package compets.gui.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import compets.config.GuiConfiguration;
import compets.gui.management.ImagePanel;

public class MenuPanel extends JPanel {
	public static final Dimension MENU_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);
	
	private static final Dimension TITLE_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height / 5);
	private static final Dimension IMAGE_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height);
	public static final Dimension BUTTONS_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height / 5);

	JButton newGameButton = new JButton("New Game");
	JButton continueButton = new JButton("Continue");
	JButton helpButton = new JButton("Help");
	JButton exitButton = new JButton("Exit");

	private MainGui mainGui;

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
		ImagePanel centerPanel = new ImagePanel();
		try {
			centerPanel.setImage(ImageIO.read(new File("images/Animal/Animal_ALL.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(centerPanel, BorderLayout.CENTER);
	}

	private void initButtonsPanel() {
		JPanel buttonsPanel = new JPanel();
		int hgap = MENU_DIMENSION.width / 40;
		buttonsPanel.setBorder(new EmptyBorder(hgap, hgap, hgap, hgap));
		GridLayout gridLayout = new GridLayout();
		gridLayout.setHgap(hgap);
		buttonsPanel.setLayout(gridLayout);
		buttonsPanel.setPreferredSize(BUTTONS_PART_DIMENSION);

		Font buttonFont = new Font("Arial", Font.PLAIN, BUTTONS_PART_DIMENSION.width / 30);
		newGameButton.setFont(buttonFont);
		continueButton.setFont(buttonFont);
		helpButton.setFont(buttonFont);
		exitButton.setFont(buttonFont);
		
		newGameButton.addActionListener(new ActionNewGame());
		continueButton.addActionListener(new ActionLoadGame());
		helpButton.addActionListener(new ActionHelp());
		exitButton.addActionListener(new ActionExit());

		buttonsPanel.add(newGameButton);
		buttonsPanel.add(continueButton);
		buttonsPanel.add(helpButton);
		buttonsPanel.add(exitButton);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}

	class ActionNewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int answer = JOptionPane.showConfirmDialog(MenuPanel.this, 
					"Do you really want to create a new game ? Any game previously saved could be lost.", 
					"Load ?", 
					JOptionPane.YES_NO_OPTION
				);
			if(answer == JOptionPane.YES_OPTION) {
				mainGui.newGame();
			}
		}
	}
	
	class ActionLoadGame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainGui.loadGame();
		}
	}
	
	class ActionHelp implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JEditorPane jep = new JEditorPane();
			jep.setEditable(false);
			try {
				jep.setPage("file:data/regles.html");
				JScrollPane scrollPane = new JScrollPane(jep);
				scrollPane.setMaximumSize(MENU_DIMENSION);
				scrollPane.setPreferredSize(new Dimension(MENU_DIMENSION.width, 2 * MENU_DIMENSION.height / 3));
				JOptionPane.showMessageDialog(MenuPanel.this, scrollPane);
			} catch (IOException exception) {	
				JOptionPane.showMessageDialog(MenuPanel.this, "Impossible d'afficher l'aide (données non trouvées)", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
