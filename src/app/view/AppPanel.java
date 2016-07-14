package app.view;

import java.awt.Image;

import javax.swing.*;

public class AppPanel extends JPanel
{
	private TabPanel tabPanel;
	private IRFPanel irfPanel;
	private LinkAggPanel linkaggPanel;
	private MainMenuPanel menuPanel;
	
	private JLabel background;
	
	public AppPanel()
	{
		background = new JLabel();
		
		tabPanel = new TabPanel(this);
		irfPanel = new IRFPanel(this);
		linkaggPanel = new LinkAggPanel(this);
		menuPanel = new MainMenuPanel();
		
		buildPanel();
		buildPlacements();
	}

	public void setupBackground(String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(900, 600, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		background.setIcon(backgroundImage);
	}
	
	private void buildPanel()
	{
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, menuPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, menuPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, menuPanel, 0, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, linkaggPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, linkaggPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, linkaggPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, linkaggPanel, 0, SpringLayout.EAST, irfPanel);
		springLayout.putConstraint(SpringLayout.WEST, tabPanel, 650, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, irfPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, irfPanel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, irfPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, irfPanel, 0, SpringLayout.WEST, tabPanel);
		springLayout.putConstraint(SpringLayout.NORTH, tabPanel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, tabPanel, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, tabPanel, 0, SpringLayout.EAST, this);

		this.setLayout(springLayout);
		add(this.tabPanel);
		add(this.irfPanel);
		add(this.linkaggPanel);
		add(this.menuPanel);
		add(this.background);
	}

	private void buildPlacements()
	{
	}
	
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
