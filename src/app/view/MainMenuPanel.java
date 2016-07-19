package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuPanel extends JPanel
{
	private SpringLayout layout;
	
	private AppPanel panel;
	
	private JLabel title;
	private JButton irfButton;
	private JButton linkAggButton;
	private JButton snmpButton;
	private JButton settingsButton;
	private JButton aboutButton;
	
	private String buttonLoc = "/resources/netButton.png";
	
	public MainMenuPanel(AppPanel panel)
	{
		layout = new SpringLayout();
		
		this.panel = panel;
		
		panel.setupBackground("/resources/menuBackground.png");
		
		title = new JLabel();
		layout.putConstraint(SpringLayout.WEST, title, 200, SpringLayout.WEST, this);
		title.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netLogoLong.png")));
		
		irfButton = new JButton("IRF SETUP");
		irfButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupButtonText(irfButton);
		
		linkAggButton = new JButton("LINK AGGREGATION");
		linkAggButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupButtonText(linkAggButton);
		
		snmpButton = new JButton("SNMP SETUP");
		snmpButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupButtonText(snmpButton);
		
		aboutButton = new JButton("ABOUT");
		setupButton(aboutButton, buttonLoc);
		
		settingsButton = new JButton("SETTINGS");
		setupButton(settingsButton, buttonLoc);
		
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}
	
	private void setupButton(JButton button, String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(150, 50, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);
		
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(new Font("Neue", Font.BOLD, 16));
		button.setForeground(Color.DARK_GRAY);
	}
	
	private void setupButtonText(JButton button)
	{
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(new Font("Neue", Font.BOLD, 16));
		button.setForeground(Color.DARK_GRAY);
	}
	
	private void setupPanel()
	{
		this.setVisible(true);
		this.setOpaque(false);
		setLayout(layout);
		
		add(this.irfButton);
		add(this.linkAggButton);
		add(this.snmpButton);
		add(this.aboutButton);
		add(this.settingsButton);
		add(this.title);
	}
	
	private void setupListeners()
	{
		irfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				panel.getIRFPanel().setVisible(true);
				//panel.setupBackground("/resources/irfBackground.png");
				panel.getMenuPanel().setVisible(false);
			}
		});
		
		linkAggButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				panel.getLinkPanel().setVisible(true);
				panel.setupBackground("/resources/linkBackground.png");
				panel.getMenuPanel().setVisible(false);
			}
		});
	}
	
	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.WEST, irfButton, 200, SpringLayout.WEST, this);
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
		layout.putConstraint(SpringLayout.EAST, irfButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, irfButton, 0, SpringLayout.SOUTH, title);
	}
}

