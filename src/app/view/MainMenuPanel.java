package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class MainMenuPanel extends JPanel
{
	private SpringLayout layout;
	
	private JLabel background;
	private JLabel title;
	private JButton irfButton;
	private JButton linkAggButton;
	private JButton snmpButton;
	private JButton settingsButton;
	private JButton aboutButton;
	
	public MainMenuPanel(AppPanel panel)
	{
		layout = new SpringLayout();
		
		background = new JLabel();
		background.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/menuBackground.png")));
		title = new JLabel();
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, title, 180, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, title, -100, SpringLayout.EAST, this);
		title.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netLogoLong.png")));
		
		irfButton = new JButton("IRF SETUP");
		irfButton.setBorderPainted(false);
		layout.putConstraint(SpringLayout.WEST, irfButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfButton, -200, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, irfButton, 0, SpringLayout.SOUTH, title);
		irfButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netButton.png")));
		setupText(irfButton);
		
		
		linkAggButton = new JButton("LINK AGGREGATION");
		layout.putConstraint(SpringLayout.NORTH, linkAggButton, 10, SpringLayout.SOUTH, irfButton);
		linkAggButton.setBorderPainted(false);
		layout.putConstraint(SpringLayout.WEST, linkAggButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, linkAggButton, -200, SpringLayout.EAST, this);
		linkAggButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netButton.png")));
		linkAggButton.setHorizontalTextPosition(SwingConstants.CENTER);
		linkAggButton.setVerticalTextPosition(SwingConstants.CENTER);
		setupText(linkAggButton);
		
		
		
		snmpButton = new JButton("SNMP SETUP");
		layout.putConstraint(SpringLayout.NORTH, snmpButton, 10, SpringLayout.SOUTH, linkAggButton);
		layout.putConstraint(SpringLayout.WEST, snmpButton, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, snmpButton, -200, SpringLayout.EAST, this);
		snmpButton.setIcon(new ImageIcon(MainMenuPanel.class.getResource("/resources/netButton.png")));
		setupText(snmpButton);
		
		aboutButton = new JButton("ABOUT");
		layout.putConstraint(SpringLayout.SOUTH, aboutButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, aboutButton, 0, SpringLayout.EAST, this);
		aboutButton.setIcon(setupBackground("/resources/netButton.png"));
		setupText(aboutButton);
		
		settingsButton = new JButton("SETTINGS");
		layout.putConstraint(SpringLayout.SOUTH, settingsButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, settingsButton, -10, SpringLayout.WEST, aboutButton);
		settingsButton.setIcon(setupBackground("/resources/netButton.png"));
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
		this.setVisible(false);
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
		
	}
	
	private void setupPlacements()
	{
		
	}
}

