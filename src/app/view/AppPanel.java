package app.view;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
		final ImageIcon[] backgroundImage = {new ImageIcon(IRFPanel.class.getResource(pictureLoc))};
		final Image[] image = {backgroundImage[0].getImage()};
		image[0] = image[0].getScaledInstance(800 + getWidth(), 600 + getHeight(), java.awt.Image.SCALE_FAST);
		backgroundImage[0] = new ImageIcon(image[0]);
		background.setIcon(backgroundImage[0]);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				image[0] = image[0].getScaledInstance(AppPanel.this.getWidth(),AppPanel.this.getHeight(),Image.SCALE_FAST);
				backgroundImage[0] = new ImageIcon(image[0]);
				background.setIcon(backgroundImage[0]);
			}
		});
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
