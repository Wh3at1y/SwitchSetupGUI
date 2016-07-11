package app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TabPanel extends JPanel
{
	private AppPanel panel;
	
	private JButton irfButton;
	private JButton snmpButton;
	private JButton linkaggButton;
	
	public TabPanel(AppPanel panel)
	{
		this.panel = panel;
		
		irfButton = new JButton("IRF Setup Script");
		snmpButton = new JButton("SNMP Setup Script");
		linkaggButton = new JButton("Link Agg Script");
		
		buildPanel();
		buildPlacements();
		buildListeners();
	}

	private void buildPanel()
	{
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, linkaggButton, 20, SpringLayout.SOUTH, snmpButton);
		springLayout.putConstraint(SpringLayout.WEST, linkaggButton, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, linkaggButton, 68, SpringLayout.SOUTH, snmpButton);
		springLayout.putConstraint(SpringLayout.EAST, linkaggButton, 0, SpringLayout.EAST, irfButton);
		springLayout.putConstraint(SpringLayout.NORTH, snmpButton, 20, SpringLayout.SOUTH, irfButton);
		springLayout.putConstraint(SpringLayout.WEST, snmpButton, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, snmpButton, 68, SpringLayout.SOUTH, irfButton);
		springLayout.putConstraint(SpringLayout.EAST, snmpButton, 0, SpringLayout.EAST, irfButton);
		springLayout.putConstraint(SpringLayout.NORTH, irfButton, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, irfButton, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, irfButton, 98, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, irfButton, -10, SpringLayout.EAST, this);
		this.setLayout(springLayout);
		this.setOpaque(false);
		add(this.irfButton);
		add(this.snmpButton);
		add(this.linkaggButton);
	}

	private void buildListeners()
	{
		linkaggButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent clicked)
				{
					panel.setupBackground("/resources/linkBackground.jpg");
					
					panel.getLinkPanel().setVisible(true);
					panel.getIRFPanel().setVisible(false);
				}
			});
		
		irfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				panel.setupBackground("/resources/irfBackground.jpg");
				
				panel.getIRFPanel().setVisible(true);
				panel.getLinkPanel().setVisible(false);
			}
		});
	}
	
	private void buildPlacements()
	{
	}
}
