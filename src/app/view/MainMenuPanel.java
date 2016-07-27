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
	private JButton changelogButton;
	
	private String buttonLoc = "/resources/netButtonMenu.png";
	
	public MainMenuPanel(AppPanel panel)
	{
		layout = new SpringLayout();
		
		this.panel = panel;
		
		panel.setupBackground("/resources/menuBackground.png");
		
		title = new JLabel();
		setupLogo(title);
		
		irfButton = new JButton("IRF SETUP");
		setupMenuButton(irfButton, buttonLoc);
		
		linkAggButton = new JButton("LINK AGGREGATION");
		setupMenuButton(linkAggButton, buttonLoc);
		
		snmpButton = new JButton("SNMP SETUP");
		setupMenuButton(snmpButton, buttonLoc);
		
		aboutButton = new JButton("ABOUT");
		setupButton(aboutButton, buttonLoc);
		
		settingsButton = new JButton("SETTINGS");
		setupButton(settingsButton, buttonLoc);
		
		changelogButton = new JButton("CHANGELOG");
		setupButton(changelogButton, buttonLoc);
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}
	
	private void setupLogo(JLabel pic)
	{
			ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource("/resources/netLogoLong.png"));
			Image image = backgroundImage.getImage();
			image = image.getScaledInstance(450, 175, java.awt.Image.SCALE_SMOOTH);
			backgroundImage = new ImageIcon(image);
			pic.setIcon(backgroundImage);
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
		button.setFont(new Font("Neue", Font.BOLD, 17));
		button.setForeground(Color.DARK_GRAY);
	}
	
	private void setupMenuButton(JButton button, String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(800, 75, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);
		
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
		add(this.changelogButton);
		add(this.title);
	}
	
	private void setupListeners()
	{
		irfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				panel.getIRFPanel().setVisible(true);
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
		
		changelogButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				JOptionPane.showMessageDialog(null, "V.2"
						+"\n"
						+"\n[Main Changes]"
						+"\nAdded Main Menu With a New Look And Feel"
						+"\nAdded some code the IRF Setup to speed things up"
						+"\nAdded Re-Number in IRF Setup"
						+"\nAdded Link Aggregation Feature (WIP)"
						+"\nOptimized panel changing"
						+"\nAdded Icon"
						+"\n"
						+"\n[Bugs]"
						+"\nSubmit button on IRF Panel has been changed from 'sumbit'"
						+"\nIRF Priority Algorithm has been fixed"
						+"\nIRF 5800 Switch Interface Ten port has been fixed", "Change Log", 2
						);
			}
		});
	}
	
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

