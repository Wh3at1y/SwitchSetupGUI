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
	
	private JLabel background;
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
		
		background = new JLabel();
		background.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/menuBackground.png")));
		
		title = new JLabel();
		title.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netLogoLong.png")));
		
		irfButton = new JButton("IRF SETUP");
		irfButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupText(irfButton);
		
		linkAggButton = new JButton("LINK AGGREGATION");
		linkAggButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupText(linkAggButton);
		
		snmpButton = new JButton("SNMP SETUP");
		snmpButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource(buttonLoc)));
		setupText(snmpButton);
		
		aboutButton = new JButton("ABOUT");
		aboutButton.setIcon(setupBackground(buttonLoc));
		setupText(aboutButton);
		
		settingsButton = new JButton("SETTINGS");
		settingsButton.setIcon(setupBackground(buttonLoc));
		setupText(settingsButton);
		
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}
	
	private ImageIcon setupBackground(String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(150, 50, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		return backgroundImage;
	}
	
	private void setupText(JButton button)
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
		setLayout(layout);
		
		add(this.irfButton);
		add(this.linkAggButton);
		add(this.snmpButton);
		add(this.aboutButton);
		add(this.settingsButton);
		add(this.title);
		add(this.background);
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
	}
	
	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, title, 180, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, title, -100, SpringLayout.EAST, this);
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
		layout.putConstraint(SpringLayout.WEST, irfButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, irfButton, 0, SpringLayout.SOUTH, title);
	}
}

