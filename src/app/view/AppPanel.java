package app.view;

import java.awt.Color;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import app.controller.AppController;
// Clipboard Import
import java.awt.Toolkit;
import java.awt.datatransfer.*;

public class AppPanel extends JPanel
{
	private SpringLayout layout;

	private List switchList;

	private JRadioButton fortyPortButton;
	private JRadioButton twentyPortButton;

	private JComboBox<Integer> switchNumbers;

	private JFormattedTextField userDomain;

	private ButtonGroup radioButtons;

	private JTextArea codePane;
	private JScrollPane textScrollPane;

	private JButton resetButton;
	private JButton copyButton;
	private JButton submitButton;

	private JLabel switchLabel;
	private JLabel portLabel;
	private JLabel stackLabel;
	private JLabel domainLabel;
	private int domainNum;

	public AppPanel(AppController controller)
	{
		layout = new SpringLayout();

		switchList = new List();
		layout.putConstraint(SpringLayout.SOUTH, switchList, -375, SpringLayout.SOUTH, this);
		switchList.add("5500 Switch");
		switchList.add("5800 Switch");
		switchList.select(0);

		// Auto Select 48 Port
		fortyPortButton = new JRadioButton("48 Port", true);
		twentyPortButton = new JRadioButton("24 Port");

		switchNumbers = new JComboBox<Integer>();
		layout.putConstraint(SpringLayout.NORTH, switchNumbers, 60, SpringLayout.SOUTH, twentyPortButton);

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

		resetButton = new JButton("Reset Options");
		layout.putConstraint(SpringLayout.SOUTH, resetButton, -40, SpringLayout.SOUTH, this);
		copyButton = new JButton("Copy the Code");
		layout.putConstraint(SpringLayout.SOUTH, copyButton, -40, SpringLayout.SOUTH, this);
		
		submitButton = new JButton("Sumbit");
		layout.putConstraint(SpringLayout.WEST, submitButton, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, 0, SpringLayout.SOUTH, copyButton);
		layout.putConstraint(SpringLayout.EAST, submitButton, 0, SpringLayout.EAST, userDomain);

		switchLabel = new JLabel("Select the Switch");
		portLabel = new JLabel("Select the Amount of Ports");
		stackLabel = new JLabel("Select the position in the stack");
		domainLabel = new JLabel("Type Domain (Numbers Only)");
		
		setupChatPane();
		buildPanel();
		buildPlacements();
		buildListeners();
	}

	private void setupChatPane()
	{
		codePane.setLineWrap(true);
		codePane.setWrapStyleWord(true);
		textScrollPane = new JScrollPane(codePane);
		layout.putConstraint(SpringLayout.EAST, textScrollPane, -20, SpringLayout.EAST, this);
		textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void updateTextCode(int positionC, String domainC, int priorityC, int interfaceInt)
	{
		int interfaceInt2 = interfaceInt + 1;
		this.codePane
				.setText("sys"
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

	private boolean selectionChecker()
	{
		boolean isFilledOut = true;
		return isFilledOut;
	}
	
	private void buildPanel()
	{
		setLayout(this.layout);
		this.setBackground(Color.WHITE);
		add(this.switchList);
		add(this.switchNumbers);
		add(this.fortyPortButton);
		add(this.twentyPortButton);
		add(this.userDomain);
		add(this.textScrollPane);
		add(this.copyButton);
		add(this.resetButton);
		add(this.switchLabel);
		add(this.portLabel);
		add(this.stackLabel);
		add(this.domainLabel);
		add(this.submitButton);
	}

	private void buildListeners()
	{
		this.submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				if(selectionChecker() == true)
				{
					// int positionC, int domainC, int priorityC, int interfaceInt
					int interfaceInt = 53;
					String domainC = userDomain.getText();
					String switchName = switchList.getSelectedItem();
					int positionC = switchNumbers.getSelectedIndex() + 1;
					
					int priority = 32;
					int newPriority = priority;
					
					for(int counter = 1; counter <= 9; counter++)  
					{
						if (positionC == counter) 
						{
							priority = newPriority;
						}
						else
						{
							newPriority = priority - 2;
						}
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
			}
		});
		
		this.copyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				copyText();
			}
		});
		this.resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				resetPanel();
			}
		});
	}

	private void copyText() {
		StringSelection selection = new StringSelection(codePane.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
	
	private void resetPanel() {
		
		// Reset Switch
		switchList.select(0);
		
		// Reset Port Selection
		fortyPortButton.setSelected(true);
		
		// Reset Position
		switchNumbers.setSelectedIndex(0);
		
		// Reset Domain
		userDomain.setText("10");
		
		// Reset TextBox
		codePane.setText(null);
	}
	
	private void buildPlacements()
	{
		layout.putConstraint(SpringLayout.NORTH, twentyPortButton, 25, SpringLayout.SOUTH, fortyPortButton);
		layout.putConstraint(SpringLayout.WEST, twentyPortButton, 0, SpringLayout.WEST, fortyPortButton);
		layout.putConstraint(SpringLayout.NORTH, switchList, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, switchList, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, switchList, -400, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, copyButton, 0, SpringLayout.WEST, textScrollPane);
		layout.putConstraint(SpringLayout.NORTH, textScrollPane, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, textScrollPane, 50, SpringLayout.EAST, switchList);
		layout.putConstraint(SpringLayout.SOUTH, textScrollPane, 0, SpringLayout.SOUTH, userDomain);
		layout.putConstraint(SpringLayout.WEST, resetButton, 10, SpringLayout.EAST, copyButton);
		layout.putConstraint(SpringLayout.NORTH, userDomain, 50, SpringLayout.SOUTH, switchNumbers);
		layout.putConstraint(SpringLayout.WEST, userDomain, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.EAST, userDomain, 0, SpringLayout.EAST, switchList);
		layout.putConstraint(SpringLayout.WEST, switchNumbers, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.EAST, switchNumbers, 0, SpringLayout.EAST, switchList);
		layout.putConstraint(SpringLayout.NORTH, fortyPortButton, 50, SpringLayout.SOUTH, switchList);
		layout.putConstraint(SpringLayout.WEST, fortyPortButton, 0, SpringLayout.WEST, switchList);
		layout.putConstraint(SpringLayout.NORTH, switchLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, switchLabel, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, portLabel, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, portLabel, -10, SpringLayout.NORTH, fortyPortButton);
		layout.putConstraint(SpringLayout.WEST, stackLabel, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, stackLabel, -10, SpringLayout.NORTH, switchNumbers);
		layout.putConstraint(SpringLayout.WEST, domainLabel, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, domainLabel, -10, SpringLayout.NORTH, userDomain);
	}
}
