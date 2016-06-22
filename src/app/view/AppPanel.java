package app.view;

import java.awt.List;

import javax.swing.*;

import app.controller.AppController;

public class AppPanel extends JPanel
{
	private SpringLayout layout;

	private List switchList;

	private JRadioButton fortyPortButton;
	private JRadioButton twentyPortButton;

	private JComboBox<Integer> switchNumbers;

	private JTextField userDomain;

	private ButtonGroup radioButtons;

	private JTextArea codePane;
	private JScrollPane textScrollPane;

	private JButton resetButton;
	private JButton copyButton;

	private JLabel switchLabel;
	private JLabel portLabel;
	private JLabel stackLabel;
	private JLabel domainLabel;

	public AppPanel(AppController controller)
	{
		layout = new SpringLayout();

		switchList = new List();
		layout.putConstraint(SpringLayout.SOUTH, switchList, -375, SpringLayout.SOUTH, this);
		switchList.add("5500 Switch");
		switchList.add("5800 Switch");

		fortyPortButton = new JRadioButton("48 Port");
		twentyPortButton = new JRadioButton("24 Port");

		switchNumbers = new JComboBox<Integer>();
		layout.putConstraint(SpringLayout.NORTH, switchNumbers, 60, SpringLayout.SOUTH, twentyPortButton);

		for (int spot = 1; spot <= 9; spot++)
		{
			switchNumbers.addItem(new Integer(spot));
		}

		userDomain = new JTextField();

		radioButtons = new ButtonGroup();
		radioButtons.add(this.fortyPortButton);
		radioButtons.add(this.twentyPortButton);

		codePane = new JTextArea();

		resetButton = new JButton("Reset Options");
		copyButton = new JButton("Copy the Code");

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
		textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void buildPanel()
	{

		setLayout(this.layout);
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
	}

	private void buildListeners()
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
		layout.putConstraint(SpringLayout.EAST, textScrollPane, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, copyButton, -30, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, resetButton, 10, SpringLayout.EAST, copyButton);
		layout.putConstraint(SpringLayout.SOUTH, resetButton, -30, SpringLayout.SOUTH, this);
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

	private void buildPlacements()
	{
	}
}
