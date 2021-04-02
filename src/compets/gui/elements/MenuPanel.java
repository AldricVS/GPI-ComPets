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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import compets.config.GuiConfiguration;
import compets.engine.data.animal.AnimalType;
import compets.gui.management.ImagePanel;

public class MenuPanel extends JPanel {
	public static final Dimension MENU_DIMENSION = new Dimension(GuiConfiguration.WIDTH, GuiConfiguration.HEIGHT);

	private static final Dimension BUTTON_LANGUAGE_DIMENSION = new Dimension(MENU_DIMENSION.width / 9, MENU_DIMENSION.height / 9);
	private static final Dimension IMAGE_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, 4 * MENU_DIMENSION.height / 5);
	public static final Dimension BUTTONS_PART_DIMENSION = new Dimension(MENU_DIMENSION.width, MENU_DIMENSION.height / 5);

	private static final String RESOURCE_BUNDLE_NAME = "main_menu/menu";

	ResourceBundle resources = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());

	JButton newGameButton = new JButton(resources.getString("button_new_game"));
	JButton continueButton = new JButton(resources.getString("button_continue"));
	JButton helpButton = new JButton(resources.getString("button_help"));
	JButton exitButton = new JButton(resources.getString("button_exit"));

	JButton changeLanguageButton = new JButton();
	Icon englishIcon;
	Icon frenchIcon;

	private MainGui mainGui;

	public MenuPanel(MainGui mainGui) {
		this.mainGui = mainGui;
		initIcons();
		setLayout(new BorderLayout());
		setPreferredSize(MENU_DIMENSION);
		updateLanguageButton();
		init();
	}

	private void initIcons() {
		englishIcon = initButtonIcon("images/misc/english_flag.png");
		frenchIcon = initButtonIcon("images/misc/french_flag.png");
	}

	private ImageIcon initButtonIcon(String filename) {
		try {
			File file = new File(filename);
			BufferedImage image = ImageIO.read(file);
			Image scaledImage = image.getScaledInstance(2 * BUTTON_LANGUAGE_DIMENSION.width / 3, 2 * BUTTON_LANGUAGE_DIMENSION.height / 3, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void init() {
		initImagePanel();
		initButtonsPanel();
	}

	private void initImagePanel() {
		JLayeredPane layeredPane = new JLayeredPane();

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(0, 0, IMAGE_PART_DIMENSION.width, IMAGE_PART_DIMENSION.height);
		try {
			imagePanel.setImage(ImageIO.read(new File("images/misc/home.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// change language button above the title screen
		changeLanguageButton.setBounds(0, 0, BUTTON_LANGUAGE_DIMENSION.width, BUTTON_LANGUAGE_DIMENSION.height);
		changeLanguageButton.setPreferredSize(new Dimension(BUTTON_LANGUAGE_DIMENSION.width, BUTTON_LANGUAGE_DIMENSION.height));
		changeLanguageButton.addActionListener(new ActionChangeLanguage());

		layeredPane.add(changeLanguageButton);
		layeredPane.add(imagePanel);
		this.add(layeredPane, BorderLayout.CENTER);
	}

	private void initButtonsPanel() {
		fillButtons();
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

	private void fillButtons() {
		newGameButton.setText(resources.getString("button_new_game"));
		continueButton.setText(resources.getString("button_continue"));
		helpButton.setText(resources.getString("button_help"));
		exitButton.setText(resources.getString("button_exit"));

	}

	class ActionNewGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// a panel to choose the animal
			AnimalType animalType = showAnimalChoice();
			if (animalType != null) {
				String options[] = new String[2];
				options[0] = resources.getString("dialog_yes");
				options[1] = resources.getString("dialog_no");
				int answer = JOptionPane.showOptionDialog(MenuPanel.this, resources.getString("new_game_warning"),
						resources.getString("new_game_warning_title"), 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
				if (answer == 0) {
					mainGui.newGame(animalType);
				}
			}
		}

		private AnimalType showAnimalChoice() {
			String options[] = new String[2];
			options[0] = resources.getString("dialog_chose");
			options[1] = resources.getString("dialog_cancel");
			JComboBox<AnimalType> animalTypeComboBox = new JComboBox<AnimalType>(AnimalType.values());
			int answer = JOptionPane.showOptionDialog(MenuPanel.this, animalTypeComboBox, resources.getString("choice_animal_title"), 0,
					JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if (answer == 0) {
				return (AnimalType) animalTypeComboBox.getSelectedItem();
			} else {
				return null;
			}
		}
	}

	class ActionLoadGame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mainGui.loadGame();
		}
	}

	class ActionHelp implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String filename;
			if (Locale.getDefault().equals(Locale.FRANCE)) {
				filename = "regles.html";
			} else {
				filename = "rules.html";
			}
			JEditorPane jep = new JEditorPane();
			jep.setEditable(false);
			try {
				jep.setPage("file:data/" + filename);
				JScrollPane scrollPane = new JScrollPane(jep);
				scrollPane.setMaximumSize(MENU_DIMENSION);
				scrollPane.setPreferredSize(new Dimension(MENU_DIMENSION.width, 2 * MENU_DIMENSION.height / 3));
				JOptionPane.showMessageDialog(MenuPanel.this, scrollPane);
			} catch (IOException exception) {
				JOptionPane.showMessageDialog(MenuPanel.this, "Cannot open help : impossible to find " + filename, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void updateLanguageButton() {
		if (Locale.getDefault().equals(Locale.FRANCE)) {
			changeLanguageButton.setIcon(englishIcon);
		} else {
			changeLanguageButton.setIcon(frenchIcon);
		}
	}

	private void updateLanguageBasedOnLocale() {
		updateLanguageButton();
		resources = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
		fillButtons();
	}

	class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class ActionChangeLanguage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Locale.getDefault().equals(Locale.FRANCE)) {
				Locale.setDefault(Locale.UK);
			} else {
				Locale.setDefault(Locale.FRANCE);
			}

			updateLanguageBasedOnLocale();
		}
	}
}
