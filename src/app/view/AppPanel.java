package app.view;

import java.awt.Image;

import javax.swing.*;

public class AppPanel extends JPanel
{
	//instance variables of subclasses
	private IRFPanel irfPanel;
	private LinkAggPanel linkaggPanel;
	private SNMPPanel snmpPanel;
	private MainMenuPanel menuPanel;

	//instance variable carrying the background image
	private JLabel background;
	
	public AppPanel()
	{
		//initializing instance variables
		background = new JLabel();
		irfPanel = new IRFPanel(this);
		linkaggPanel = new LinkAggPanel(this);
		menuPanel = new MainMenuPanel(this);
		snmpPanel = new SNMPPanel(this);

		//helper method for cleaner look
		buildPanel();

	}

	public void setupBackground(String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(800, 600, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		background.setIcon(backgroundImage);
	}


	private void buildPanel()
	{
		//setting up the SpringLayout for the panel
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.EAST, linkaggPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, irfPanel, 0, SpringLayout.EAST, this);

		springLayout.putConstraint(SpringLayout.EAST, snmpPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, snmpPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, snmpPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, snmpPanel, 0, SpringLayout.NORTH, this);

		springLayout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, menuPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, menuPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, menuPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, linkaggPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, linkaggPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, linkaggPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, irfPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, irfPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, irfPanel, 0, SpringLayout.SOUTH, this);

		//declaring the layout of the panel
		this.setLayout(springLayout);

		//adding the components to the panel
		add(this.snmpPanel);
		add(this.irfPanel);
		add(this.linkaggPanel);
		add(this.menuPanel);
		add(this.background);
	}

	/**
	 * getter for the SNMPPanel
	 * @return snmpPanel
	 */
	public SNMPPanel getSNMPPanel() { return this.snmpPanel;}
	
	public LinkAggPanel getLinkPanel()
	{
		return this.linkaggPanel;
	}
	
	public IRFPanel getIRFPanel()
	{
		return this.irfPanel;
	}
	
	public MainMenuPanel getMenuPanel()
	{
		return this.menuPanel;
	}
}
