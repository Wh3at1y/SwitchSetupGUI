/**
 * Authors : Sam Montoya (Brettly)
 * 				 Dylan Gardener
 * --------------------
 * This class is applying and using components of the panel.
 */
package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class IRFPanel extends JPanel
{
	/**
	 * Declaration Section
	 */
	private SpringLayout layout;
	private AppPanel panel;
	private Font font;
	
	private List switchList;

	private JRadioButton fortyPortButton;
	private JRadioButton twentyPortButton;

	private JComboBox<Integer> switchNumbers;

	private JFormattedTextField userDomain;

	private JTextArea codePane;
	private JScrollPane textScrollPane;

	private ButtonGroup radioButtons;
	
	private JButton resetButton;
	private JButton copyButton;
	private JButton submitButton;
	private JButton renumberButton;
	private JButton homeButton;

	private JLabel switchLabel;
	private JLabel portLabel;
	private JLabel stackLabel;
	private JLabel domainLabel;
	private JLabel homePicture;
	
	private int domainNum;

	/**
	 * Constructor
	 * Initializes variables from the declaration section
	 */
	public IRFPanel(AppPanel panel)
	{
		font = new Font("Neue", Font.BOLD, 13);
		this.panel = panel;
		layout = new SpringLayout();

		switchList = new List();
		switchList.add("5500 Switch");
		switchList.add("5800 Switch");
		switchList.select(0);

		fortyPortButton = new JRadioButton("48 Port", true);
		fortyPortButton.setOpaque(false);
		twentyPortButton = new JRadioButton("24 Port");
		twentyPortButton.setOpaque(false);
		setupLabels(this.fortyPortButton);
		setupLabels(this.twentyPortButton);

		switchNumbers = new JComboBox<Integer>();
		for (int spot = 1; spot <= 9; spot++)
		{
			switchNumbers.addItem(new Integer(spot));
		}

		userDomain = new JFormattedTextField(domainNum);
		userDomain.setText("10");
		
		radioButtons = new ButtonGroup();
		radioButtons.add(this.fortyPortButton);
		radioButtons.add(this.twentyPortButton);

		codePane = new JTextArea();

		resetButton = new JButton("Reset All");
		copyButton = new JButton("Copy the Code");
		submitButton = new JButton("Submit");
		renumberButton = new JButton("Re-Number");
		homeButton = new JButton("Home");
		
		String buttonLoc = "/resources/netButton.png";
		setupButton(resetButton, buttonLoc);
		setupButton(copyButton, buttonLoc);
		setupButton(submitButton, buttonLoc);
		setupButton(renumberButton, buttonLoc);
		setupButton(homeButton, buttonLoc);
		
		switchLabel = new JLabel("Select the Switch");
		portLabel = new JLabel("Select the Amount of Ports");
		stackLabel = new JLabel("Select the position in the stack");
		domainLabel = new JLabel("Type Domain (Numbers Only)");
		homePicture = new JLabel();
		homePicture.setIcon(new ImageIcon(IRFPanel.class.getResource("/resources/home.png")));
		setupLabels(this.switchLabel);
		setupLabels(this.portLabel);
		setupLabels(this.stackLabel);
		setupLabels(this.domainLabel);
		
		panel.setupBackground("/resources/irfBackground.png");
		
		setupChatPane();
		buildPanel();
		buildPlacements();
		buildListeners();
	}
	
	private void setupButton(JButton button, String pictureLoc)
	{
		ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(120, 35, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);
		
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(font);
		button.setForeground(Color.DARK_GRAY);
	}

	/**
	 * @param label : JLabels in the panel, sets the font and color
	 */
	private void setupLabels(Component label)
	{
		label.setFont(font);
		label.setForeground(Color.DARK_GRAY);
	}
	
	/**
	 * Adds scroll bars, etc
	 */
	private void setupChatPane()
	{
		codePane.setLineWrap(true);
		codePane.setWrapStyleWord(true);
		textScrollPane = new JScrollPane(codePane);
		textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * @param positionC
	 * @param domainC
	 * @param priorityC
	 * @param interfaceInt
	 */
	private void updateTextCode(int positionC, String domainC, int priorityC, int interfaceInt)
	{
		int interfaceInt2 = interfaceInt + 1;
		this.codePane.setText("sys"
		+ "\nirf domain " + domainC + "\nirf member " + positionC + " priority " + priorityC 
		+ "\nInterface Ten " + positionC + "/0/" +  interfaceInt
		+ "\nshut"
		+ "\nInterface Ten " + positionC + "/0/" +  interfaceInt2
		+ "\nshut" 
		+ "\nirf-port " + positionC + "/1"
		+ "\nport group interface ten " + positionC + "/0/" +  interfaceInt + " mode enhanced"
		+ "\nirf-port " + positionC + "/2"
		+ "\nport group interface ten " + positionC + "/0/" +  interfaceInt2 + " mode enhanced"
		+ "\nInterface Ten " + positionC + "/0/" +  interfaceInt
		+ "\nundo shut"
		+ "\nInterface Ten " + positionC + "/0/" + interfaceInt2
		+ "\nundo shut"
		+ "\nqu"
		+ "\nsave");
	}

	/**
	 * Resets the components to defaults
	 */
	private void resetPanel() 
		{
		
		// Reset Switch
		switchList.select(0);
		
		// Reset Port Selection
		fortyPortButton.setSelected(true);
		
		// Reset Position
		switchNumbers.setSelectedIndex(0);
		
		// Reset Domain
		userDomain.setText("10");
		
		// Reset TextBox
		codePane.setText("");
		}

	/**
	 * Copy Text Button Function
	 */
	private void copyText() 
	{
		StringSelection selection = new StringSelection(codePane.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
	
	/**
	 * Adds components to the panel
	 */
	private void buildPanel()
	{
		this.setVisible(false);
		this.setOpaque(false);
		setLayout(this.layout);
		
		add(this.switchList);
		add(this.switchNumbers);
		add(this.fortyPortButton);
		add(this.twentyPortButton);
		add(this.userDomain);
		add(this.textScrollPane);
		add(this.copyButton);
		add(this.resetButton);
		add(this.renumberButton);
		add(this.submitButton);
		add(this.homePicture);
		add(this.homeButton);
		add(this.switchLabel);
		add(this.portLabel);
		add(this.stackLabel);
		add(this.domainLabel);
	}

	/**
	 * Listeners for the buttons
	 */
	private void buildListeners()
	{
		this.submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				int interfaceInt = 53;
				String domainC = userDomain.getText();
				String switchName = switchList.getSelectedItem();
				int positionC = switchNumbers.getSelectedIndex() + 1;
				
				int priority = 32;
					
				for(int counter = 1; counter <= 9; counter++)  
				{
					if(positionC == counter)
						break;
					priority--; priority--;
				}
					
				if(switchName.contains("5800") && fortyPortButton.isSelected())
					interfaceInt = 51;
				else if(switchName.contains("5800") && twentyPortButton.isSelected())
					interfaceInt = 25;
				else if(switchName.contains("5500") && fortyPortButton.isSelected())
					interfaceInt = 53;
				else if(switchName.contains("5500") && twentyPortButton.isSelected())
					interfaceInt = 29;
					
				updateTextCode(positionC, domainC, priority, interfaceInt);
			}
		});
		
		this.homeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				setVisible(false);
				panel.getMenuPanel().setVisible(true);
				panel.setupBackground("/resources/menuBackground.png");
			}
		});
		
		// Listener for Copy Button
		this.copyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				// copies text
				copyText();
			}
		});
		
		// Listener for the Reset Button
		this.resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				// resets all options to original state
				resetPanel();
			}
		});
		
		this.renumberButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				String firstSwitch = JOptionPane.showInputDialog("Enter Current Switch Number");
				String renumberSwitch = JOptionPane.showInputDialog("Enter New Switch Number");
				
				codePane.setText("sys"
						+ "\nirf-port-configuration active"
						+ "\nirf member " + firstSwitch + " renumber " + renumberSwitch
						+ "\nYes"
						+ "\nquit"
						+ "\nreboot"
						+ "\n#"
						+ "\nYes"
						+ "\n#"
						+ "\nYes"
						+ "\n#");
				
			}
		});
	}
	/**
	 * Window Builder Generated Code Garbage
	 */
	private void buildPlacements()
	{
		layout.putConstraint(SpringLayout.WEST, resetButton, -10, SpringLayout.EAST, copyButton);
		layout.putConstraint(SpringLayout.EAST, homeButton, 0, SpringLayout.EAST, textScrollPane);
		layout.putConstraint(SpringLayout.WEST, copyButton, -17, SpringLayout.WEST, textScrollPane);
		layout.putConstraint(SpringLayout.EAST, copyButton, 131, SpringLayout.WEST, textScrollPane);
		layout.putConstraint(SpringLayout.EAST, textScrollPane, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, renumberButton, 0, SpringLayout.NORTH, copyButton);
		layout.putConstraint(SpringLayout.WEST, renumberButton, -10, SpringLayout.EAST, resetButton);
		layout.putConstraint(SpringLayout.SOUTH, resetButton, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, submitButton, 16, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, homeButton, 0, SpringLayout.SOUTH, submitButton);
		layout.putConstraint(SpringLayout.NORTH, homePicture, 12, SpringLayout.NORTH, homeButton);
		layout.putConstraint(SpringLayout.WEST, homePicture, 17, SpringLayout.WEST, homeButton);
		layout.putConstraint(SpringLayout.SOUTH, copyButton, -20, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, switchLabel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, textScrollPane, 30, SpringLayout.EAST, stackLabel);
		layout.putConstraint(SpringLayout.EAST, switchNumbers, 0, SpringLayout.EAST, stackLabel);
		layout.putConstraint(SpringLayout.WEST, switchLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, portLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, stackLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, domainLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, twentyPortButton, 25, SpringLayout.SOUTH, fortyPortButton);
		layout.putConstraint(SpringLayout.WEST, twentyPortButton, 0, SpringLayout.WEST, fortyPortButton);
		layout.putConstraint(SpringLayout.NORTH, switchList, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, textScrollPane, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, textScrollPane, 0, SpringLayout.SOUTH, userDomain);
		layout.putConstraint(SpringLayout.NORTH, userDomain, 50, SpringLayout.SOUTH, switchNumbers);
		layout.putConstraint(SpringLayout.WEST, userDomain, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.EAST, userDomain, 0, SpringLayout.EAST, switchList);
		layout.putConstraint(SpringLayout.WEST, switchNumbers, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.NORTH, fortyPortButton, 50, SpringLayout.SOUTH, switchList);
		layout.putConstraint(SpringLayout.WEST, fortyPortButton, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.SOUTH, portLabel, -10, SpringLayout.NORTH, fortyPortButton);
		layout.putConstraint(SpringLayout.SOUTH, stackLabel, -10, SpringLayout.NORTH, switchNumbers);
		layout.putConstraint(SpringLayout.SOUTH, domainLabel, -10, SpringLayout.NORTH, userDomain);
		layout.putConstraint(SpringLayout.NORTH, switchNumbers, 60, SpringLayout.SOUTH, twentyPortButton);
		layout.putConstraint(SpringLayout.SOUTH, switchList, -375, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, switchList, 25, SpringLayout.WEST, this);
	}
}
