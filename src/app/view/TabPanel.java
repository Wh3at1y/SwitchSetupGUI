package app.view;

import javax.swing.*;

public class TabPanel extends JPanel
{
	private JButton irfButton;
	
	public TabPanel()
	{
		irfButton = new JButton("IRF Setup");
		
		buildPanel();
		buildPlacements();
		buildListeners();
	}

	private void buildPanel()
	{
		this.setLayout(new SpringLayout());
		add(this.irfButton);
	}

	private void buildListeners()
	{
		
	}
	
	private void buildPlacements()
	{
	}
}
