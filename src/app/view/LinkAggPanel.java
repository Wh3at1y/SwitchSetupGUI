package app.view;

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
	private JTextField portNum1;
	private JTextField portNum2;
	private JTextField portNum3;
	private JTextField portNum4;
	private JTextField portNum5;
	private JTextField portNum6;
	private JTextField portNum7;
	private JTextField portNum8;
	
	private ButtonGroup portGroup1;
	private ButtonGroup portGroup2;
	private ButtonGroup portGroup3;
	private ButtonGroup portGroup4;
	private ButtonGroup portGroup5;
	private ButtonGroup portGroup6;
	private ButtonGroup portGroup7;
	private ButtonGroup portGroup8;
	
	private JCheckBox yes1;
	private JCheckBox yes2;
	private JCheckBox yes3;
	private JCheckBox yes4;
	private JCheckBox yes5;
	private JCheckBox yes6;
	private JCheckBox yes7;
	private JCheckBox yes8;	
	
	private JComboBox<Integer> irfNumbers;

	private ButtonGroup radioButtons;
	private JRadioButton tengigButton;
	private JRadioButton onegigButton;

	private JButton submitButton;
	
	private ArrayList<Component> fieldList;
	private ArrayList<ButtonGroup> groupList;
	private ArrayList<JCheckBox> yesList;
	
	private JLabel bridgeLabel;
	private JLabel portLabel;
	private JLabel amountLabel;
	private JLabel portNumLabel;
	private JLabel moduleLabel;
	
	private Font font;

	public LinkAggPanel(AppPanel panel)
	{
		this.setVisible(false);
		layout = new SpringLayout();

		font = new Font("Candara", Font.BOLD, 13);
		
		bridgeGroupField = new JTextField();
		layout.putConstraint(SpringLayout.WEST, bridgeGroupField, 24, SpringLayout.WEST, this);
		portNum1 = new JTextField("Num 1");
		layout.putConstraint(SpringLayout.WEST, portNum1, 25, SpringLayout.WEST, this);
		portNum2 = new JTextField("Num 2");
		portNum3 = new JTextField("Num 3");
		layout.putConstraint(SpringLayout.EAST, portNum3, -10, SpringLayout.EAST, portNum2);
		portNum4 = new JTextField("Num 4");
		layout.putConstraint(SpringLayout.NORTH, portNum4, 19, SpringLayout.SOUTH, portNum3);
		layout.putConstraint(SpringLayout.EAST, portNum4, 0, SpringLayout.EAST, portNum2);
		portNum5 = new JTextField("Num 5");
		layout.putConstraint(SpringLayout.NORTH, portNum5, 23, SpringLayout.SOUTH, portNum4);
		layout.putConstraint(SpringLayout.EAST, portNum5, 0, SpringLayout.EAST, portNum2);
		portNum6 = new JTextField("Num 6");
		portNum7 = new JTextField("Num 7");
		portNum8 = new JTextField("Num 8");
		portNum2.setVisible(false);
		portNum3.setVisible(false);
		portNum4.setVisible(false);
		portNum5.setVisible(false);
		portNum6.setVisible(false);
		portNum7.setVisible(false);
		portNum8.setVisible(false);

		fieldList = new ArrayList<Component>();
		fieldList.add(portNum1);
		fieldList.add(portNum2);
		fieldList.add(portNum3);
		fieldList.add(portNum4);
		fieldList.add(portNum5);
		fieldList.add(portNum6);
		fieldList.add(portNum7);
		fieldList.add(portNum8);
		
		portGroup1 = new ButtonGroup();
		portGroup2 = new ButtonGroup();
		portGroup3 = new ButtonGroup();
		portGroup4 = new ButtonGroup();
		portGroup5 = new ButtonGroup();
		portGroup6 = new ButtonGroup();
		portGroup7 = new ButtonGroup();
		portGroup8 = new ButtonGroup();
		
		groupList = new ArrayList<ButtonGroup>();
		groupList.add(portGroup1);
		groupList.add(portGroup2);
		groupList.add(portGroup3);
		groupList.add(portGroup4);
		groupList.add(portGroup5);
		groupList.add(portGroup6);
		groupList.add(portGroup7);
		groupList.add(portGroup8);
		
		yesList = new ArrayList<JCheckBox>();
		yesList.add(yes1 = new JCheckBox("Yes1"));
		layout.putConstraint(SpringLayout.NORTH, yes1, 7, SpringLayout.SOUTH, portNum1);
		yesList.add(yes2 = new JCheckBox("Yes2"));
		yesList.add(yes3 = new JCheckBox("Yes3"));
		yesList.add(yes4 = new JCheckBox("Yes4"));
		yesList.add(yes5 = new JCheckBox("Yes5"));
		yesList.add(yes6 = new JCheckBox("Yes6"));
		yesList.add(yes7 = new JCheckBox("Yes7"));
		yesList.add(yes8 = new JCheckBox("Yes8"));
		
		for(int spot = 0; spot <= groupList.size() - 1; spot++)
		{
			for(int place = 0; place <= yesList.size() - 1; place++)
				groupList.get(spot).add(yesList.get(place));
		}
		
		submitButton = new JButton("Submit");
		layout.putConstraint(SpringLayout.EAST, bridgeGroupField, 26, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.WEST, submitButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, submitButton, -510, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, portNum8, 0, SpringLayout.NORTH, submitButton);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, -10, SpringLayout.SOUTH, this);

		irfNumbers = new JComboBox<Integer>();
		layout.putConstraint(SpringLayout.WEST, irfNumbers, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfNumbers, 167, SpringLayout.WEST, this);
		for (int spot = 1; spot <= 8; spot++)
		{
			irfNumbers.addItem(new Integer(spot));
		}

		radioButtons = new ButtonGroup();
		radioButtons.add(tengigButton = new JRadioButton("10 gig", true));
		layout.putConstraint(SpringLayout.WEST, tengigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, portNum2, -1, SpringLayout.NORTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, portNum2, 107, SpringLayout.EAST, tengigButton);
		radioButtons.add(onegigButton = new JRadioButton("1 gig"));
		layout.putConstraint(SpringLayout.NORTH, onegigButton, 10, SpringLayout.SOUTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, onegigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, portNum3, -1, SpringLayout.NORTH, onegigButton);

		bridgeLabel = new JLabel("Enter Bridge :");
		bridgeLabel.setFont(font);
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 10, SpringLayout.SOUTH, bridgeLabel);
		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, bridgeLabel, 25, SpringLayout.WEST, this);
		portLabel = new JLabel("Enter Port Speed :");
		layout.putConstraint(SpringLayout.NORTH, portLabel, 50, SpringLayout.SOUTH, bridgeGroupField);
		portLabel.setFont(font);
		layout.putConstraint(SpringLayout.NORTH, tengigButton, 10, SpringLayout.SOUTH, portLabel);
		layout.putConstraint(SpringLayout.WEST, portLabel, 25, SpringLayout.WEST, this);
		amountLabel = new JLabel("How many ports are in the bridge group (1-8)");
		layout.putConstraint(SpringLayout.NORTH, irfNumbers, 10, SpringLayout.SOUTH, amountLabel);
		layout.putConstraint(SpringLayout.NORTH, amountLabel, 50, SpringLayout.SOUTH, onegigButton);
		layout.putConstraint(SpringLayout.WEST, amountLabel, 25, SpringLayout.WEST, this);
		amountLabel.setFont(font);
		portNumLabel = new JLabel("Port Number (1-54) :");
		layout.putConstraint(SpringLayout.NORTH, portNum1, 10, SpringLayout.SOUTH, portNumLabel);
		layout.putConstraint(SpringLayout.EAST, portNum1, 0, SpringLayout.EAST, portNumLabel);
		layout.putConstraint(SpringLayout.NORTH, portNumLabel, 50, SpringLayout.SOUTH, irfNumbers);
		layout.putConstraint(SpringLayout.WEST, portNumLabel, 25, SpringLayout.WEST, this);
		portNumLabel.setFont(font);
		moduleLabel = new JLabel("Module? :");
		layout.putConstraint(SpringLayout.NORTH, moduleLabel, 10, SpringLayout.SOUTH, portNum1);
		layout.putConstraint(SpringLayout.WEST, yes1, 0, SpringLayout.EAST, moduleLabel);
		moduleLabel.setFont(font);
		layout.putConstraint(SpringLayout.WEST, moduleLabel, 25, SpringLayout.WEST, this);
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}

	private void setupPanel()
	{
		setLayout(layout);
		this.setOpaque(false);
		add(this.bridgeLabel);
		add(this.portLabel);
		add(this.amountLabel);
		add(this.portNumLabel);
		add(this.moduleLabel);
		add(this.bridgeGroupField);
		add(this.irfNumbers);
		add(this.tengigButton);
		add(this.onegigButton);
		
		add(this.portNum1);
		
		add(this.yes1);
//		for(int spot = 0; spot <=7; spot++)
//			add(fieldList.get(spot));
//		for(int spot = 0; spot <= 7; spot++)
//			add(yesList.get(spot));
		
		add(this.submitButton);
	}

	private void setupListeners()
	{
		irfNumbers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				int irfSelectedNum = Integer.parseInt(irfNumbers.getSelectedItem().toString());
				irfSelectedNum--;

				for(int spot = 0; spot <= fieldList.size() - 1; spot++)
				{
					fieldList.get(spot).setVisible(false);
				}
				
				for(int x = 0; x <= irfSelectedNum; x++)
				{
					fieldList.get(x).setVisible(true);
				}
			}
		});
	}

	private void setupPlacements()
	{

	}
}
