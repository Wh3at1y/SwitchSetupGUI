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
	private ArrayList<JLabel> labelList;
	private ArrayList<JCheckBox> yesList;

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
		font = new Font("Candara", Font.BOLD, 13);

		bridgeGroupField = new JTextField();

		fieldList = new ArrayList<Component>();
		fieldList.add(portNum1 = new JTextField());
		fieldList.add(portNum2 = new JTextField());
		fieldList.add(portNum3 = new JTextField());
		fieldList.add(portNum4 = new JTextField());
		fieldList.add(portNum5 = new JTextField());
		fieldList.add(portNum6 = new JTextField());
		fieldList.add(portNum7 = new JTextField());
		fieldList.add(portNum8 = new JTextField());

		yesList = new ArrayList<JCheckBox>();
		yesList.add(yes1 = new JCheckBox("Yes"));
		yesList.add(yes2 = new JCheckBox("Yes"));
		yesList.add(yes3 = new JCheckBox("Yes"));
		yesList.add(yes4 = new JCheckBox("Yes"));
		yesList.add(yes5 = new JCheckBox("Yes"));
		yesList.add(yes6 = new JCheckBox("Yes"));
		yesList.add(yes7 = new JCheckBox("Yes"));
		yesList.add(yes8 = new JCheckBox("Yes"));

		labelList = new ArrayList<JLabel>();
		labelList.add(port1Label = new JLabel("Port 1 Info (1-54) :"));
		labelList.add(port2Label = new JLabel("Port 2 Info :"));
		labelList.add(port3Label = new JLabel("Port 3 Info :"));
		labelList.add(port4Label = new JLabel("Port 4 Info :"));
		labelList.add(port5Label = new JLabel("Port 5 Info :"));
		labelList.add(port6Label = new JLabel("Port 6 Info :"));
		labelList.add(port7Label = new JLabel("Port 7 Info :"));
		labelList.add(port8Label = new JLabel("Port 8 Info :"));
		port1Label.setFont(font);

		labelList.add(bridgeLabel = new JLabel("Enter Bridge :"));
		labelList.add(portSpeedLabel = new JLabel("Enter Port Speed :"));
		labelList.add(amountLabel = new JLabel("Ports in bridge group :"));
		labelList.add(moduleLabel = new JLabel("Module? :"));

		for (int spot = 1; spot <= 7; spot++)
		{
			labelList.get(spot).setVisible(false);
			labelList.get(spot).setFont(font);

			yesList.get(spot).setVisible(false);
			yesList.get(spot).setFont(font);

			fieldList.get(spot).setVisible(false);
			fieldList.get(spot).setFont(font);

			for (int place = 8; place <= 11; place++)
				labelList.get(place).setFont(font);
		}

		irfNumbers = new JComboBox<Integer>();
		for (int spot = 1; spot <= 8; spot++)
			irfNumbers.addItem(new Integer(spot));

		radioButtons = new ButtonGroup();
		radioButtons.add(tengigButton = new JRadioButton("10 gig", true));
		radioButtons.add(onegigButton = new JRadioButton("1 gig"));

		submitButton = new JButton("Submit");

		setupPanel();
		setupPlacements();
		setupListeners();
	}

	private void setupPanel()
	{
		setLayout(layout);
		this.setOpaque(false);
		add(this.bridgeLabel);
		add(this.amountLabel);
		add(this.moduleLabel);
		add(this.bridgeGroupField);
		add(this.portSpeedLabel);
		add(this.irfNumbers);
		add(this.tengigButton);
		add(this.onegigButton);
		add(this.submitButton);

		for (int spot = 0; spot <= 7; spot++)
			add(fieldList.get(spot));
		for (int spot = 0; spot <= 7; spot++)
			add(yesList.get(spot));
		for (int spot = 0; spot <= 11; spot++)
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
					fieldList.get(spot).setVisible(false);
					yesList.get(spot).setVisible(false);
					labelList.get(spot).setVisible(false);
				}

				for (int spot = 0; spot <= irfSelectedNum; spot++)
				{
					fieldList.get(spot).setVisible(true);
					yesList.get(spot).setVisible(true);
					labelList.get(spot).setVisible(true);
				}
			}
		});
	}

	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.NORTH, yes1, 7, SpringLayout.SOUTH, portNum1);
		layout.putConstraint(SpringLayout.NORTH, yes3, 3, SpringLayout.SOUTH, portNum3);
		layout.putConstraint(SpringLayout.EAST, yes3, 0, SpringLayout.EAST, portNum3);
		layout.putConstraint(SpringLayout.NORTH, yes4, 3, SpringLayout.SOUTH, portNum4);
		layout.putConstraint(SpringLayout.NORTH, yes5, 3, SpringLayout.SOUTH, portNum5);
		layout.putConstraint(SpringLayout.EAST, yes5, 0, SpringLayout.EAST, portNum5);
		layout.putConstraint(SpringLayout.NORTH, yes6, 3, SpringLayout.SOUTH, portNum6);
		layout.putConstraint(SpringLayout.EAST, yes6, 0, SpringLayout.EAST, portNum6);
		layout.putConstraint(SpringLayout.NORTH, yes7, 3, SpringLayout.SOUTH, portNum7);
		layout.putConstraint(SpringLayout.NORTH, yes8, 3, SpringLayout.SOUTH, portNum8);
		layout.putConstraint(SpringLayout.EAST, yes8, 0, SpringLayout.EAST, portNum8);
		layout.putConstraint(SpringLayout.EAST, portNum1, 0, SpringLayout.EAST, portSpeedLabel);
		layout.putConstraint(SpringLayout.NORTH, moduleLabel, 10, SpringLayout.SOUTH, portNum1);
		layout.putConstraint(SpringLayout.NORTH, port4Label, 10, SpringLayout.SOUTH, yes3);
		layout.putConstraint(SpringLayout.EAST, yes4, 0, SpringLayout.EAST, port4Label);
		layout.putConstraint(SpringLayout.NORTH, portNum4, 7, SpringLayout.SOUTH, port4Label);
		layout.putConstraint(SpringLayout.WEST, portNum4, 0, SpringLayout.WEST, port4Label);
		layout.putConstraint(SpringLayout.EAST, port4Label, 0, SpringLayout.EAST, portNum3);
		layout.putConstraint(SpringLayout.NORTH, portNum5, 7, SpringLayout.SOUTH, port5Label);
		layout.putConstraint(SpringLayout.WEST, portNum5, 0, SpringLayout.WEST, port5Label);
		layout.putConstraint(SpringLayout.NORTH, port5Label, 10, SpringLayout.SOUTH, yes4);
		layout.putConstraint(SpringLayout.WEST, port5Label, 0, SpringLayout.WEST, portNum4);
		layout.putConstraint(SpringLayout.NORTH, portNum6, 7, SpringLayout.SOUTH, port6Label);
		layout.putConstraint(SpringLayout.WEST, portNum6, 0, SpringLayout.WEST, port6Label);
		layout.putConstraint(SpringLayout.EAST, portNum6, 0, SpringLayout.EAST, port6Label);
		layout.putConstraint(SpringLayout.NORTH, port6Label, 10, SpringLayout.SOUTH, yes5);
		layout.putConstraint(SpringLayout.WEST, port6Label, 0, SpringLayout.WEST, port5Label);
		layout.putConstraint(SpringLayout.EAST, yes7, 0, SpringLayout.EAST, port7Label);
		layout.putConstraint(SpringLayout.NORTH, portNum7, 7, SpringLayout.SOUTH, port7Label);
		layout.putConstraint(SpringLayout.WEST, portNum7, 0, SpringLayout.WEST, port7Label);
		layout.putConstraint(SpringLayout.EAST, portNum7, 0, SpringLayout.EAST, port7Label);
		layout.putConstraint(SpringLayout.NORTH, port7Label, 10, SpringLayout.SOUTH, yes6);
		layout.putConstraint(SpringLayout.WEST, port7Label, 0, SpringLayout.WEST, port6Label);
		layout.putConstraint(SpringLayout.NORTH, portNum8, 7, SpringLayout.SOUTH, port8Label);
		layout.putConstraint(SpringLayout.WEST, portNum8, 0, SpringLayout.WEST, port8Label);
		layout.putConstraint(SpringLayout.EAST, portNum8, 0, SpringLayout.EAST, port8Label);
		layout.putConstraint(SpringLayout.NORTH, port8Label, 10, SpringLayout.SOUTH, yes7);
		layout.putConstraint(SpringLayout.WEST, port8Label, 0, SpringLayout.WEST, port7Label);
		layout.putConstraint(SpringLayout.EAST, portNum5, 0, SpringLayout.EAST, port3Label);
		layout.putConstraint(SpringLayout.EAST, portNum4, 0, SpringLayout.EAST, port3Label);
		layout.putConstraint(SpringLayout.NORTH, portNum3, 7, SpringLayout.SOUTH, port3Label);
		layout.putConstraint(SpringLayout.WEST, portNum3, 0, SpringLayout.WEST, port3Label);
		layout.putConstraint(SpringLayout.EAST, portNum3, 0, SpringLayout.EAST, port3Label);
		layout.putConstraint(SpringLayout.NORTH, port3Label, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, port3Label, 50, SpringLayout.EAST, bridgeGroupField);
		layout.putConstraint(SpringLayout.WEST, moduleLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, bridgeGroupField, 24, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, bridgeGroupField, -484, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, portNum1, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, portNum2, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, submitButton, 188, SpringLayout.EAST, yes2);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submitButton, -197, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, irfNumbers, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfNumbers, 167, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, tengigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, onegigButton, 10, SpringLayout.SOUTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, onegigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 10, SpringLayout.SOUTH, bridgeLabel);
		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, bridgeLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, portNum2, 0, SpringLayout.EAST, portSpeedLabel);
		layout.putConstraint(SpringLayout.NORTH, portSpeedLabel, 50, SpringLayout.SOUTH, bridgeGroupField);
		layout.putConstraint(SpringLayout.NORTH, tengigButton, 10, SpringLayout.SOUTH, portSpeedLabel);
		layout.putConstraint(SpringLayout.WEST, portSpeedLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, irfNumbers, 10, SpringLayout.SOUTH, amountLabel);
		layout.putConstraint(SpringLayout.NORTH, amountLabel, 50, SpringLayout.SOUTH, onegigButton);
		layout.putConstraint(SpringLayout.WEST, amountLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, portNum1, 10, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.NORTH, port1Label, 50, SpringLayout.SOUTH, irfNumbers);
		layout.putConstraint(SpringLayout.WEST, port1Label, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, yes1, 0, SpringLayout.EAST, moduleLabel);
		layout.putConstraint(SpringLayout.NORTH, port2Label, 30, SpringLayout.SOUTH, moduleLabel);
		layout.putConstraint(SpringLayout.NORTH, portNum2, 10, SpringLayout.SOUTH, port2Label);
		layout.putConstraint(SpringLayout.WEST, port2Label, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, yes2, 3, SpringLayout.SOUTH, portNum2);
		layout.putConstraint(SpringLayout.WEST, yes2, 0, SpringLayout.WEST, yes1);
	}
}
