package app.view;

import javax.swing.*;

public class LinkAggPanel extends JPanel
{
	private SpringLayout layout;
	
	private JTextField bridgeGroupField;
	
	private JComboBox<Integer> irfNumbers;
	
	private ButtonGroup radioButtons;
	private JRadioButton tengigButton;
	private JRadioButton onegigButton;
	
	private JButton submitButton;
	
	public LinkAggPanel(AppPanel panel)
	{
		this.setVisible(false);
		layout = new SpringLayout();
		
		bridgeGroupField = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, bridgeGroupField, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, bridgeGroupField, -543, SpringLayout.EAST, this);
		submitButton = new JButton("Submit");
		layout.putConstraint(SpringLayout.WEST, submitButton, 0, SpringLayout.WEST, bridgeGroupField);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submitButton, 115, SpringLayout.WEST, bridgeGroupField);
		
		irfNumbers = new JComboBox<Integer>();
		layout.putConstraint(SpringLayout.WEST, irfNumbers, 0, SpringLayout.WEST, bridgeGroupField);
		layout.putConstraint(SpringLayout.EAST, irfNumbers, 0, SpringLayout.EAST, bridgeGroupField);
		for (int spot = 1; spot <= 9; spot++)
		{
			irfNumbers.addItem(new Integer(spot));
		}
		
		radioButtons = new ButtonGroup();
		radioButtons.add(tengigButton = new JRadioButton("10 gig", true));
		layout.putConstraint(SpringLayout.NORTH, tengigButton, 40, SpringLayout.SOUTH, bridgeGroupField);
		layout.putConstraint(SpringLayout.WEST, tengigButton, 0, SpringLayout.WEST, bridgeGroupField);
		radioButtons.add(onegigButton = new JRadioButton("1 gig"));
		layout.putConstraint(SpringLayout.NORTH, irfNumbers, 47, SpringLayout.SOUTH, onegigButton);
		layout.putConstraint(SpringLayout.NORTH, onegigButton, 6, SpringLayout.SOUTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, onegigButton, 0, SpringLayout.WEST, bridgeGroupField);
		
		setupPanel();
		setupPlacements();
		setupListeners();
	}
	
	private void setupPanel()
	{
		setLayout(layout);
		this.setOpaque(false);
		add(this.bridgeGroupField);
		add(this.irfNumbers);
		add(this.tengigButton);
		add(this.onegigButton);
		add(this.submitButton);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupPlacements()
	{
		
	}
}
