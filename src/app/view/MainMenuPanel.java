package app.view;

import javax.swing.*;

public class MainMenuPanel extends JPanel
{
	private SpringLayout layout;
	
	private JLabel background;
	private JLabel title;
	private JButton irfButton;
	private JButton linkAggButton;
	private JButton snmpButton;
	
	public MainMenuPanel()
	{
		layout = new SpringLayout();
		
		background = new JLabel();
		background.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/menuBackground.jpg")));
		title = new JLabel();
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, title, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, title, -100, SpringLayout.EAST, this);
		title.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/switchSetup.png")));
		
		irfButton = new JButton("IRF BUTTON");
		layout.putConstraint(SpringLayout.WEST, irfButton, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, irfButton, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, irfButton, 0, SpringLayout.EAST, this);
		//irfButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/button (4).png")));
		irfButton.setHorizontalTextPosition(JButton.CENTER);
		irfButton.setVerticalTextPosition(JButton.CENTER);
		layout.putConstraint(SpringLayout.NORTH, irfButton, 20, SpringLayout.SOUTH, title);
		linkAggButton = new JButton("LINK BUTTON");
		layout.putConstraint(SpringLayout.NORTH, linkAggButton, 56, SpringLayout.SOUTH, irfButton);
		layout.putConstraint(SpringLayout.WEST, linkAggButton, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, linkAggButton, -100, SpringLayout.EAST, this);
		snmpButton = new JButton("SNMP Button");
		layout.putConstraint(SpringLayout.SOUTH, linkAggButton, -70, SpringLayout.NORTH, snmpButton);
		layout.putConstraint(SpringLayout.NORTH, snmpButton, 409, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, snmpButton, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, snmpButton, -100, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, snmpButton, -100, SpringLayout.SOUTH, this);
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setVisible(false);
		setLayout(layout);
		
		add(this.irfButton);
		add(this.linkAggButton);
		add(this.snmpButton);
		add(this.title);
		add(this.background);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupPlacements()
	{
		
	}
}

