package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuPanel extends JPanel
{
	//layout used to set up gui
	private SpringLayout layout;

	//active panel in main menu
	private AppPanel panel;

	//the main menu buttons
	private JLabel title;
	private JButton irfButton;
	private JButton linkAggButton;
	private JButton snmpButton;
	private JButton settingsButton;
	private JButton aboutButton;
	private JButton changelogButton;

	//image used to place buttons on
	private String buttonLoc = "/resources/netButtonMenu.png";

	/**
	 * constructor to iterate through setting up the main menu
	 * @param panel to be aggregated
	 */
	public MainMenuPanel(AppPanel panel)
	{
		//instantiating layout
		layout = new SpringLayout();

		//instantiating panel
		this.panel = panel;

		//sets the background image
		panel.setupBackground("/resources/menuBackground.png");

		//sets the title of the program
		title = new JLabel();
		setupLogo(title);

		//sets the IRF Setup button
		irfButton = new JButton("IRF SETUP");
		setupMenuButton(irfButton, buttonLoc);

		//sets the Link Aggregation button
		linkAggButton = new JButton("LINK AGGREGATION");
		setupMenuButton(linkAggButton, buttonLoc);

		//sets the SNMP Setup button
		snmpButton = new JButton("SNMP SETUP");
		setupMenuButton(snmpButton, buttonLoc);

		//sets the about button
		aboutButton = new JButton("ABOUT");
		setupButton(aboutButton, buttonLoc);

		//sets the settings button
		settingsButton = new JButton("SETTINGS");
		setupButton(settingsButton, buttonLoc);

		//sets the changelog button
		changelogButton = new JButton("CHANGELOG");
		setupButton(changelogButton, buttonLoc);

		//helper methods
		setupPanel();
		setupPlacements();
		setupListeners();
	}

	/**
	 * method that sets up the background image
	 * @param pic to be used
	 */
	private void setupLogo(JLabel pic)
	{
			ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource("/resources/netLogoLong.png"));
			Image image = backgroundImage.getImage();
			image = image.getScaledInstance(450, 175, java.awt.Image.SCALE_SMOOTH);
			backgroundImage = new ImageIcon(image);
			pic.setIcon(backgroundImage);
	}

	/**
	 * method for setting up the button with a picture
	 * @param button to be set up
	 * @param pictureLoc to be used
	 */
	private void setupButton(JButton button, String pictureLoc)
	{
		//setting the image to the button
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(150, 50, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);

		//setting up the button
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(new Font("Neue", Font.BOLD, 17));
		button.setForeground(Color.DARK_GRAY);
	}

	/**
	 * method setting up the menu
	 * @param button to be set up
	 * @param pictureLoc to be used
	 */
	private void setupMenuButton(JButton button, String pictureLoc)
	{
		//setting the image to the button
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(800, 75, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);

		//setting up the button
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(new Font("Neue", Font.BOLD, 16));
		button.setForeground(Color.DARK_GRAY);
	}

	/**
	 * method setting up the panel with all the components
	 */
	private void setupPanel()
	{
		//initial set up of panel
		this.setVisible(true);
		this.setOpaque(false);
		setLayout(layout);

		//adding components
		add(this.irfButton);
		add(this.linkAggButton);
		add(this.snmpButton);
		add(this.aboutButton);
		add(this.settingsButton);
		add(this.changelogButton);
		add(this.title);
	}

	/**
	 * method setting up all the listeners for each button
	 */
	private void setupListeners()
	{
		//listener for the IRF button
		irfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				//changes panel from the menu to the IRF setup
				panel.getIRFPanel().setVisible(true);
				panel.getIRFPanel().getSwitchList().setVisible(true);
				panel.setupBackground("/resources/irfBackground.png");
				panel.getMenuPanel().setVisible(false);
			}
		});

		//listener for the Link Aggregation button
		linkAggButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				//changes panel from the menu to the Link Aggregation setup
				panel.getLinkPanel().setVisible(true);

				panel.setupBackground("/resources/linkBackground.png");
				panel.getMenuPanel().setVisible(false);
			}
		});

		//listener for the SNMP button
		snmpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				panel.getSNMPPanel().setVisible(true);
				panel.setupBackground("/resources/snmpBackground.png");
				panel.getMenuPanel().setVisible(false);

			}
		});

		//listener for the changelog button
		changelogButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				//text to be shown
				JOptionPane.showMessageDialog(null, "V.3.8"
						+"\n"
						+"\n[Main Changes]"
						+"\nMade UI more user friendly"
						+"\n"
						+"\n[Bugs]"
						+"\nCleaned up code"
						+"\nSanitized input variables", "Change Log", 2
						);
			}
		});
	}

	/**
	 * method setting up the placement of the components in the gui
	 */
	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.WEST, irfButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, changelogButton, 0, SpringLayout.SOUTH, settingsButton);
		layout.putConstraint(SpringLayout.WEST, title, 180, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, linkAggButton, 10, SpringLayout.SOUTH, irfButton);
		layout.putConstraint(SpringLayout.WEST, linkAggButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, linkAggButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, snmpButton, 10, SpringLayout.SOUTH, linkAggButton);
		layout.putConstraint(SpringLayout.WEST, snmpButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, snmpButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, aboutButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, aboutButton, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, settingsButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, settingsButton, -10, SpringLayout.WEST, aboutButton);
		layout.putConstraint(SpringLayout.NORTH, irfButton, 0, SpringLayout.SOUTH, title);
	}
}

