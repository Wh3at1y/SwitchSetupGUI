package app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class LinkAggPanel extends JPanel
{
	private SpringLayout layout;

	private JTextField bridgeGroupField;
	
	private JTextArea codeArea;
	private JScrollPane scrollPane;

	private JComboBox<Integer> irfNumbers;
	
	private JComboBox<Integer> switchNumber1;
	private JComboBox<Integer> switchNumber2;
	private JComboBox<Integer> switchNumber3;
	private JComboBox<Integer> switchNumber4;
	private JComboBox<Integer> switchNumber5;
	private JComboBox<Integer> switchNumber6;
	private JComboBox<Integer> switchNumber7;
	private JComboBox<Integer> switchNumber8;
	
	private JComboBox<Integer> moduleNumber1;
	private JComboBox<Integer> moduleNumber2;
	private JComboBox<Integer> moduleNumber3;
	private JComboBox<Integer> moduleNumber4;
	private JComboBox<Integer> moduleNumber5;
	private JComboBox<Integer> moduleNumber6;
	private JComboBox<Integer> moduleNumber7;
	private JComboBox<Integer> moduleNumber8;
	
	private JComboBox<Integer> portNumber1;
	private JComboBox<Integer> portNumber2;
	private JComboBox<Integer> portNumber3;
	private JComboBox<Integer> portNumber4;
	private JComboBox<Integer> portNumber5;
	private JComboBox<Integer> portNumber6;
	private JComboBox<Integer> portNumber7;
	private JComboBox<Integer> portNumber8;

	private ButtonGroup radioButtons;
	private JRadioButton tengigButton;
	private JRadioButton onegigButton;

	private JButton copyButton;
	private JButton clearButton;

	private ArrayList<JComboBox> switchList;
	private ArrayList<JComboBox> moduleList;
	private ArrayList<JComboBox> portNumberList;
	private ArrayList<JLabel> labelList;

	private JLabel bridgeLabel;
	private JLabel amountLabel;
	private JLabel moduleLabel;
	private JLabel portSpeedLabel;
	private JLabel port1Label;
	private JLabel port2Label;
	private JLabel port3Label;
	private JLabel port4Label;
	private JLabel port5Label;
	private JLabel port6Label;
	private JLabel port7Label;
	private JLabel port8Label;

	private Font font;

	public LinkAggPanel(AppPanel panel)
	{
		this.setVisible(false);
		layout = new SpringLayout();
		font = new Font("Candara", Font.BOLD, 14);

		bridgeGroupField = new JTextField();

		switchList = new ArrayList<JComboBox>();	
		switchList.add(switchNumber1 = new JComboBox<Integer>());
		switchList.add(switchNumber2 = new JComboBox<Integer>());
		switchList.add(switchNumber3 = new JComboBox<Integer>());
		switchList.add(switchNumber4 = new JComboBox<Integer>());
		switchList.add(switchNumber5 = new JComboBox<Integer>());
		switchList.add(switchNumber6 = new JComboBox<Integer>());
		switchList.add(switchNumber7 = new JComboBox<Integer>());
		switchList.add(switchNumber8 = new JComboBox<Integer>());
		
		moduleList = new ArrayList<JComboBox>();
		moduleList.add(moduleNumber1 = new JComboBox<Integer>());
		moduleList.add(moduleNumber2 = new JComboBox<Integer>());
		layout.putConstraint(SpringLayout.NORTH, moduleNumber2, 0, SpringLayout.NORTH, switchNumber2);
		layout.putConstraint(SpringLayout.WEST, moduleNumber2, 0, SpringLayout.EAST, switchNumber2);
		moduleList.add(moduleNumber3 = new JComboBox<Integer>());
		moduleList.add(moduleNumber4 = new JComboBox<Integer>());
		moduleList.add(moduleNumber5 = new JComboBox<Integer>());
		moduleList.add(moduleNumber6 = new JComboBox<Integer>());
		moduleList.add(moduleNumber7 = new JComboBox<Integer>());
		moduleList.add(moduleNumber8 = new JComboBox<Integer>());
		
		portNumberList = new ArrayList<JComboBox>();
		portNumberList.add(portNumber1 = new JComboBox<Integer>());
		portNumberList.add(portNumber2 = new JComboBox<Integer>());
		layout.putConstraint(SpringLayout.NORTH, portNumber2, 0, SpringLayout.NORTH, moduleNumber2);
		layout.putConstraint(SpringLayout.WEST, portNumber2, 0, SpringLayout.EAST, moduleNumber2);
		portNumberList.add(portNumber3 = new JComboBox<Integer>());
		portNumberList.add(portNumber4 = new JComboBox<Integer>());
		portNumberList.add(portNumber5 = new JComboBox<Integer>());
		portNumberList.add(portNumber6 = new JComboBox<Integer>());
		portNumberList.add(portNumber7 = new JComboBox<Integer>());
		portNumberList.add(portNumber8 = new JComboBox<Integer>());
		
				for(int spot = 1; spot <= 4; spot ++)
				{
					switchList.get(spot).addItem(new Integer(spot));
					moduleList.get(spot).addItem(new Integer(spot));
				}
				
				//for(int spot = 1; spot <= 54; spot ++)
					//portNumberList.get(spot).addItem(spot);

		labelList = new ArrayList<JLabel>();
		labelList.add(port1Label = new JLabel("Port 1 Info (1-8) :"));
		layout.putConstraint(SpringLayout.NORTH, portNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber1, 0, SpringLayout.WEST, port1Label);
		labelList.add(port2Label = new JLabel("Port 2 Info :"));
		labelList.add(port3Label = new JLabel("Port 3 Info :"));
		labelList.add(port4Label = new JLabel("Port 4 Info :"));
		labelList.add(port5Label = new JLabel("Port 5 Info :"));
		labelList.add(port6Label = new JLabel("Port 6 Info :"));
		labelList.add(port7Label = new JLabel("Port 7 Info :"));
		labelList.add(port8Label = new JLabel("Port 8 Info :"));
		port1Label.setFont(font);
		port1Label.setForeground(Color.WHITE);

		labelList.add(bridgeLabel = new JLabel("Enter Bridge :"));
		layout.putConstraint(SpringLayout.WEST, bridgeGroupField, 0, SpringLayout.WEST, bridgeLabel);
		layout.putConstraint(SpringLayout.EAST, bridgeGroupField, 0, SpringLayout.EAST, bridgeLabel);
		labelList.add(portSpeedLabel = new JLabel("Enter Port Speed :"));
		labelList.add(amountLabel = new JLabel("Ports in bridge group :"));

		for (int spot = 1; spot <= 6; spot++)
		{
			labelList.get(spot).setVisible(false);
			labelList.get(spot).setFont(font);
			labelList.get(spot).setForeground(Color.WHITE);

			for (int place = 7; place <= 10; place++)
			{
				labelList.get(place).setFont(font);
				labelList.get(place).setForeground(Color.WHITE);
			}
		}

		irfNumbers = new JComboBox<Integer>();
		for (int spot = 1; spot <= 8; spot++)
			irfNumbers.addItem(new Integer(spot));

		radioButtons = new ButtonGroup();
		radioButtons.add(tengigButton = new JRadioButton("10 gig", true));
		radioButtons.add(onegigButton = new JRadioButton("1 gig"));
		tengigButton.setForeground(Color.WHITE);
		tengigButton.setFont(font);
		onegigButton.setForeground(Color.WHITE);
		onegigButton.setFont(font);

		copyButton = new JButton("Copy");
		clearButton = new JButton("Clear");

		setupChatPane();
		setupPanel();
		setupPlacements();
		setupListeners();
	}

	private void setupChatPane()
	{
		codeArea = new JTextArea();
		codeArea.setLineWrap(true);
		codeArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(codeArea);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, clearButton);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void setupPanel()
	{
		setLayout(layout);
		this.setOpaque(false);
		add(this.port1Label);
		add(this.port2Label);
		
		add(this.bridgeLabel);
		add(this.amountLabel);
		add(this.bridgeGroupField);
		add(this.portSpeedLabel);
		add(this.irfNumbers);
		add(this.tengigButton);
		add(this.onegigButton);
		add(this.switchNumber2);
		add(this.moduleNumber2);
		add(this.portNumber2);
		add(this.copyButton);
		add(this.scrollPane);
		add(this.clearButton);

		for (int spot = 0; spot <= 10; spot++)
			add(labelList.get(spot));
	}

	private void setupListeners()
	{
		irfNumbers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				int irfSelectedNum = Integer.parseInt(irfNumbers.getSelectedItem().toString());
				irfSelectedNum--;

				for (int spot = 0; spot <= 7; spot++)
				{
					labelList.get(spot).setVisible(false);
				}

				for (int spot = 0; spot <= irfSelectedNum; spot++)
				{
					labelList.get(spot).setVisible(true);
				}
			}
		});
	}

	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.WEST, portNumber1, 0, SpringLayout.EAST, moduleNumber1);
		layout.putConstraint(SpringLayout.WEST, moduleNumber1, 0, SpringLayout.EAST, switchNumber1);
		layout.putConstraint(SpringLayout.WEST, copyButton, 325, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, copyButton, -12, SpringLayout.WEST, clearButton);
		layout.putConstraint(SpringLayout.WEST, clearButton, 484, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, clearButton, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, clearButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, bridgeLabel);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, -50, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, scrollPane, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, copyButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, irfNumbers, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfNumbers, 167, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, tengigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, onegigButton, 10, SpringLayout.SOUTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, onegigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 10, SpringLayout.SOUTH, bridgeLabel);
		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, bridgeLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, portSpeedLabel, 50, SpringLayout.SOUTH, bridgeGroupField);
		layout.putConstraint(SpringLayout.NORTH, tengigButton, 10, SpringLayout.SOUTH, portSpeedLabel);
		layout.putConstraint(SpringLayout.WEST, portSpeedLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, irfNumbers, 10, SpringLayout.SOUTH, amountLabel);
		layout.putConstraint(SpringLayout.NORTH, amountLabel, 50, SpringLayout.SOUTH, onegigButton);
		layout.putConstraint(SpringLayout.WEST, amountLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, port1Label, 50, SpringLayout.SOUTH, irfNumbers);
		layout.putConstraint(SpringLayout.WEST, port1Label, 25, SpringLayout.WEST, this);
	}
}
