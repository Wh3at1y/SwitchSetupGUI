package app.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class LinkAggPanel extends JPanel
{
	private SpringLayout layout;

	private AppPanel panel;

	private JTextField bridgeGroupField;
	//text field for user input
	private JTextField vlanPortField;

	private JTextArea codeArea;
	private JScrollPane scrollPane;

	private JComboBox<Integer> irfNumbers;

	//drop down menu items for switch menu
	private JComboBox<Integer> switchNumber1;
	private JComboBox<Integer> switchNumber2;
	private JComboBox<Integer> switchNumber3;
	private JComboBox<Integer> switchNumber4;
	private JComboBox<Integer> switchNumber5;
	private JComboBox<Integer> switchNumber6;
	private JComboBox<Integer> switchNumber7;
	private JComboBox<Integer> switchNumber8;
	//for the module menu
	private JComboBox<Integer> moduleNumber1;
	private JComboBox<Integer> moduleNumber2;
	private JComboBox<Integer> moduleNumber3;
	private JComboBox<Integer> moduleNumber4;
	private JComboBox<Integer> moduleNumber5;
	private JComboBox<Integer> moduleNumber6;
	private JComboBox<Integer> moduleNumber7;
	private JComboBox<Integer> moduleNumber8;
	//for the port menu
	private JComboBox<Integer> portNumber1;
	private JComboBox<Integer> portNumber2;
	private JComboBox<Integer> portNumber3;
	private JComboBox<Integer> portNumber4;
	private JComboBox<Integer> portNumber5;
	private JComboBox<Integer> portNumber6;
	private JComboBox<Integer> portNumber7;
	private JComboBox<Integer> portNumber8;
	//button objects that get filled in when pressed
	private ButtonGroup radioButtons;
	private JRadioButton tengigButton;
	private JRadioButton onegigButton;
	//action buttons that do something when pressed
	private JButton copyButton;
	private JButton submitButton;
	private JButton homeButton;
	//the actual drop down menus
	private ArrayList<JComboBox> switchList;
	private ArrayList<JComboBox> moduleList;
	private ArrayList<JComboBox> portNumberList;
	private ArrayList<JLabel> labelList;
	//all the text labels for components
	private JLabel bridgeLabel;
	private JLabel vlanLabel;
	private JLabel amountLabel;
	private JLabel portSpeedLabel;
	private JLabel port1Label;
	private JLabel port2Label;
	private JLabel port3Label;
	private JLabel port4Label;
	private JLabel port5Label;
	private JLabel port6Label;
	private JLabel port7Label;
	private JLabel port8Label;
	private JLabel homeIcon;

	//font used for the text in the program
	private Font font;

	/**
	 * constructor that sets up all the components and calls the helper methods
	 * @param panel
	 */
	public LinkAggPanel(AppPanel panel)
	{

		this.setVisible(false);		//setting the panel to be visible
		layout = new SpringLayout();		//setting up gui layout
		font = new Font("Neue", Font.BOLD, 14);		//setting up font


		//implementing panel from parameters
		this.panel = panel;

		bridgeGroupField = new JTextField();
		vlanPortField = new JTextField("1");

		/**
		 * Format: Switch / Module / Port
		 */
		// Switch ComboBox List
		switchList = new ArrayList<JComboBox>();
		switchList.add(switchNumber1 = new JComboBox<Integer>());
		switchList.add(switchNumber2 = new JComboBox<Integer>());
		switchList.add(switchNumber3 = new JComboBox<Integer>());
		switchList.add(switchNumber4 = new JComboBox<Integer>());
		switchList.add(switchNumber5 = new JComboBox<Integer>());
		switchList.add(switchNumber6 = new JComboBox<Integer>());
		switchList.add(switchNumber7 = new JComboBox<Integer>());
		switchList.add(switchNumber8 = new JComboBox<Integer>());

		// Module List
		moduleList = new ArrayList<JComboBox>();
		moduleList.add(moduleNumber1 = new JComboBox<Integer>());
		moduleList.add(moduleNumber2 = new JComboBox<Integer>());
		moduleList.add(moduleNumber3 = new JComboBox<Integer>());
		moduleList.add(moduleNumber4 = new JComboBox<Integer>());
		moduleList.add(moduleNumber5 = new JComboBox<Integer>());
		moduleList.add(moduleNumber6 = new JComboBox<Integer>());
		moduleList.add(moduleNumber7 = new JComboBox<Integer>());
		moduleList.add(moduleNumber8 = new JComboBox<Integer>());

		// Port List
		portNumberList = new ArrayList<JComboBox>();
		portNumberList.add(portNumber1 = new JComboBox<Integer>());
		portNumberList.add(portNumber2 = new JComboBox<Integer>());
		portNumberList.add(portNumber3 = new JComboBox<Integer>());
		portNumberList.add(portNumber4 = new JComboBox<Integer>());
		portNumberList.add(portNumber5 = new JComboBox<Integer>());
		portNumberList.add(portNumber6 = new JComboBox<Integer>());
		portNumberList.add(portNumber7 = new JComboBox<Integer>());
		portNumberList.add(portNumber8 = new JComboBox<Integer>());

		// Label List
		labelList = new ArrayList<JLabel>();
		labelList.add(port1Label = new JLabel("Switch / Module / Port"));
		labelList.add(port2Label = new JLabel("Port 2:"));
		labelList.add(port3Label = new JLabel("Port 3:"));
		labelList.add(port4Label = new JLabel("Port 4:"));
		labelList.add(port5Label = new JLabel("Port 5:"));
		labelList.add(port6Label = new JLabel("Port 6:"));
		labelList.add(port7Label = new JLabel("Port 7:"));
		labelList.add(port8Label = new JLabel("Port 8:"));
		port1Label.setFont(font);
		port1Label.setForeground(Color.WHITE);

		//sets up the labels for each of their corresponding components
		labelList.add(bridgeLabel = new JLabel("Enter Bridge :"));
		labelList.add(vlanLabel = new JLabel("Enter Vlan Port : "));
		labelList.add(portSpeedLabel = new JLabel("Enter Port Speed :"));
		labelList.add(amountLabel = new JLabel("Ports in bridge group :"));

		//sets up the home button visually
		homeIcon = new JLabel();
		homeIcon.setIcon(new ImageIcon(LinkAggPanel.class.getResource("/resources/home.png")));

		//sets the drop down menus to be visible
		for (int spot = 1; spot <= 7; spot++)
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

		//sets up the radio buttons
		radioButtons = new ButtonGroup();
		radioButtons.add(tengigButton = new JRadioButton("10 gig", true));
		radioButtons.add(onegigButton = new JRadioButton("1 gig"));
		tengigButton.setForeground(Color.WHITE);
		tengigButton.setFont(font);
		onegigButton.setForeground(Color.WHITE);
		onegigButton.setFont(font);

		//sets up the action buttons
		String buttonLoc = "/resources/netButtonMenu.png";
		copyButton = new JButton("Copy");
		submitButton = new JButton("Submit");
		homeButton = new JButton("Home");
		setupButton(copyButton, buttonLoc);
		setupButton(submitButton, buttonLoc);
		setupButton(homeButton, buttonLoc);

		//calling helper methods
		setupComboBoxes();
		setupChatPane();
		setupPanel();
		setupPlacements();
		setupListeners();
	}

	/**
	 * Helper method that sets up the JButtons in the class
	 * @param button to be given attributes
	 * @param pictureLoc to be used as a graphical icon for the button
	 */
	private void setupButton(JButton button, String pictureLoc)
	{
		//sets up the image for the button
		ImageIcon backgroundImage = new ImageIcon(LinkAggPanel.class.getResource(pictureLoc));
		Image image = backgroundImage.getImage();
		image = image.getScaledInstance(120, 35, java.awt.Image.SCALE_FAST);
		backgroundImage = new ImageIcon(image);
		button.setIcon(backgroundImage);

		//sets up the actual button
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.CENTER);
		button.setFont(font);
		button.setForeground(Color.DARK_GRAY);
	}

	/**
	 * sets up the drop down menus for the port, module, and switch options
	 */
	private void setupComboBoxes()
	{
		//adds the spots for the number groups to go
		irfNumbers = new JComboBox<Integer>();
		for (int spot = 1; spot <= 8; spot++)
			irfNumbers.addItem(new Integer(spot));

		//actually adds each number to the drop down menu
		for (int spot = 0; spot <= 7; spot++)
		{
			for (int number = 1; number <= 9; number++)
				switchList.get(spot).addItem(number);
			for (int number = 0; number <= 2; number++)
				moduleList.get(spot).addItem(number);
			for (int number = 1; number <= 54; number++)
				portNumberList.get(spot).addItem(number);

			switchList.get(spot).setVisible(false);
			moduleList.get(spot).setVisible(false);
			portNumberList.get(spot).setVisible(false);
		}
		switchList.get(0).setVisible(true);
		moduleList.get(0).setVisible(true);
		portNumberList.get(0).setVisible(true);

	}

	/**
	 * method that sets up the area the code will be generated in
	 * able to be edited and scroll up and down if the code extends that far
	 */
	private void setupChatPane()
	{
		codeArea = new JTextArea();
		codeArea.setLineWrap(true);
		codeArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(codeArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * method that acts as the copy command in the operating system
	 */
	private void copyText() 
	{
		StringSelection selection = new StringSelection(codeArea.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}

	/**
	 * sets the vlan port to the user specified value
	 * defaults to '1'
	 * @return the vlanPort
	 */
	private String setVlanPort()
	{
		//gets the text in the field
		String vlanPort = this.vlanPortField.getText();
		//defaults to 1 if the field is left empty
		if (vlanPort.isEmpty()){
			vlanPort = "1";
		}
		//returns the string
		return vlanPort;
	}

	/**
	 * formats the text to be used in setting up the switches to be copied and ran
	 * @param portsEntered
	 * @param speed
	 */
	private void sendText(int portsEntered, String speed)
	{
		//variables for mutating in the code area
		String bridgeGroup = this.bridgeGroupField.getText();
		String vlanPort = setVlanPort();
		//formatting code area
		this.codeArea.setText("sys"
				+"\ninterface bridge-aggregation " + bridgeGroup
				+"\ndefault"
				+"\nyes"
				+"\nlink-aggregation mode dynamic"
				+"\nquit"
				+"\n");
		
				for(int spot=0; spot <= portsEntered-1; spot++)
				{
				codeArea.append(
				"\ninterface " + speed + " " + switchList.get(spot).getSelectedItem() + "/" + moduleList.get(spot).getSelectedItem() + "/" + portNumberList.get(spot).getSelectedItem()
				+"\ndefault"
				+"\nyes"
				+"\nshut"
				+"\nport link-aggregation group " + bridgeGroup
				);
				}
				
				codeArea.append(
				"\n"
				+"\nquit"
				
				+"\ninterface bridge-aggregation " + bridgeGroup
				+"\nport link-type trunk"
				+"\nport trunk permit vlan " + vlanPort
				);

				for(int spot = 0; spot <= portsEntered-1; spot++)
				{
						codeArea.append(
					"\ninterface " + speed + " " + switchList.get(spot).getSelectedItem() + "/" + moduleList.get(spot).getSelectedItem() + "/" + portNumberList.get(spot).getSelectedItem()
					+"\nundo shut"
					);
				}
					
				codeArea.append("\n"
							+"quit"
							+ "\nsave force");
				}

	/**
	 * helper method to set the layout of the gui objects
 	 */
	private void setupPanel()
	{
		setLayout(layout);
		this.setOpaque(false);

		add(this.homeIcon);
		add(this.bridgeLabel);
		add(this.vlanLabel);
		add(this.amountLabel);
		add(this.bridgeGroupField);
		add(this.vlanPortField);
		add(this.portSpeedLabel);
		add(this.irfNumbers);
		add(this.tengigButton);
		add(this.onegigButton);
		add(this.copyButton);
		add(this.submitButton);
		add(this.homeButton);
		add(this.scrollPane);

		for (int spot = 0; spot <= 10; spot++)
			add(labelList.get(spot));
		for (int spot = 0; spot <= 7; spot++)
		{
			add(switchList.get(spot));
			add(moduleList.get(spot));
			add(portNumberList.get(spot));
		}
	}

	/**
	 * sets up the listeners for each button in the gui interface
	 * properly lays them out on the panels for later addition to the JFrame object
	 */
	private void setupListeners()
	{
		irfNumbers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				int irfSelectedNum = Integer.parseInt(irfNumbers.getSelectedItem().toString());

				if (irfSelectedNum <= 1) {
					layout.putConstraint(SpringLayout.WEST, copyButton, 175, SpringLayout.EAST, submitButton);
					layout.putConstraint(SpringLayout.EAST, submitButton, 130, SpringLayout.WEST, portSpeedLabel);


				}else {
					layout.putConstraint(SpringLayout.WEST, copyButton, 70, SpringLayout.EAST, submitButton);
					layout.putConstraint(SpringLayout.EAST, submitButton, 375, SpringLayout.WEST, portSpeedLabel);


				}
				irfSelectedNum--;

				for (int spot = 0; spot <= 7; spot++)
				{
					switchList.get(spot).setVisible(false);
					moduleList.get(spot).setVisible(false);
					portNumberList.get(spot).setVisible(false);
					labelList.get(spot).setVisible(false);
				}

				for (int spot = 0; spot <= irfSelectedNum; spot++)
				{
					switchList.get(spot).setVisible(true);
					moduleList.get(spot).setVisible(true);
					portNumberList.get(spot).setVisible(true);
					labelList.get(spot).setVisible(true);
				}
			}
		});

		homeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				panel.setupBackground("/resources/menuBackground.png");
				panel.getLinkPanel().setVisible(false);
				panel.getMenuPanel().setVisible(true);
			}
		});
		
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				String speed = "";
				if(tengigButton.isSelected())
					speed = "ten";
					else
						speed = "gig";
				sendText(Integer.parseInt(irfNumbers.getSelectedItem().toString()), speed);
			}
		});
		
		copyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				copyText();
			}
		});
	}

	/**
	 * messy organization of the layout for the gui
	 * (thanks previous intern)
	 */
	private void setupPlacements()
	{
		layout.putConstraint(SpringLayout.SOUTH, homeIcon, -12, SpringLayout.SOUTH, submitButton);
		layout.putConstraint(SpringLayout.WEST, copyButton, 175, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.SOUTH, submitButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, submitButton, 130, SpringLayout.WEST, portSpeedLabel);
		layout.putConstraint(SpringLayout.WEST, homeIcon, 15, SpringLayout.WEST, homeButton);
		layout.putConstraint(SpringLayout.SOUTH, homeButton, 0, SpringLayout.SOUTH, submitButton);
		layout.putConstraint(SpringLayout.EAST, homeButton, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, copyButton);
		layout.putConstraint(SpringLayout.EAST, scrollPane, -25, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, -100, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber2, 0, SpringLayout.NORTH, switchNumber2);
		layout.putConstraint(SpringLayout.WEST, moduleNumber2, 0, SpringLayout.EAST, switchNumber2);
		layout.putConstraint(SpringLayout.WEST, moduleNumber3, 0, SpringLayout.EAST, switchNumber3);
		layout.putConstraint(SpringLayout.WEST, moduleNumber4, 0, SpringLayout.EAST, switchNumber4);
		layout.putConstraint(SpringLayout.WEST, moduleNumber5, 0, SpringLayout.EAST, switchNumber5);
		layout.putConstraint(SpringLayout.WEST, moduleNumber6, 0, SpringLayout.EAST, switchNumber6);
		layout.putConstraint(SpringLayout.WEST, moduleNumber7, 0, SpringLayout.EAST, switchNumber7);
		layout.putConstraint(SpringLayout.WEST, moduleNumber8, 0, SpringLayout.EAST, switchNumber8);
		layout.putConstraint(SpringLayout.SOUTH, moduleNumber8, 0, SpringLayout.SOUTH, switchNumber8);
		layout.putConstraint(SpringLayout.NORTH, portNumber2, 0, SpringLayout.NORTH, moduleNumber2);
		layout.putConstraint(SpringLayout.WEST, portNumber2, 0, SpringLayout.EAST, moduleNumber2);
		layout.putConstraint(SpringLayout.WEST, portNumber3, 0, SpringLayout.EAST, moduleNumber3);
		layout.putConstraint(SpringLayout.WEST, portNumber4, 0, SpringLayout.EAST, moduleNumber4);
		layout.putConstraint(SpringLayout.WEST, portNumber5, 0, SpringLayout.EAST, moduleNumber5);
		layout.putConstraint(SpringLayout.WEST, portNumber6, 0, SpringLayout.EAST, moduleNumber6);
		layout.putConstraint(SpringLayout.WEST, portNumber7, 0, SpringLayout.EAST, moduleNumber7);
		layout.putConstraint(SpringLayout.WEST, portNumber8, 0, SpringLayout.EAST, moduleNumber8);
		layout.putConstraint(SpringLayout.SOUTH, portNumber8, 0, SpringLayout.SOUTH, moduleNumber8);
		layout.putConstraint(SpringLayout.NORTH, switchNumber2, 0, SpringLayout.SOUTH, port2Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber2, 0, SpringLayout.WEST, port2Label);
		layout.putConstraint(SpringLayout.NORTH, port2Label, 50, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.WEST, port2Label, 0, SpringLayout.WEST, port1Label);
		layout.putConstraint(SpringLayout.NORTH, portNumber3, 0, SpringLayout.SOUTH, port3Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber3, 0, SpringLayout.SOUTH, port3Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber3, 0, SpringLayout.SOUTH, port3Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber3, 0, SpringLayout.WEST, port3Label);
		layout.putConstraint(SpringLayout.NORTH, portNumber4, 0, SpringLayout.SOUTH, port4Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber4, 0, SpringLayout.SOUTH, port4Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber4, 0, SpringLayout.SOUTH, port4Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber4, 0, SpringLayout.WEST, port4Label);
		layout.putConstraint(SpringLayout.NORTH, port4Label, 50, SpringLayout.SOUTH, port3Label);
		layout.putConstraint(SpringLayout.WEST, port4Label, 0, SpringLayout.WEST, port3Label);
		layout.putConstraint(SpringLayout.WEST, port5Label, 0, SpringLayout.WEST, port3Label);
		layout.putConstraint(SpringLayout.NORTH, portNumber5, 0, SpringLayout.SOUTH, port5Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber5, 0, SpringLayout.SOUTH, port5Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber5, 0, SpringLayout.SOUTH, port5Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber5, 0, SpringLayout.WEST, port5Label);
		layout.putConstraint(SpringLayout.NORTH, port5Label, 50, SpringLayout.SOUTH, port4Label);
		layout.putConstraint(SpringLayout.NORTH, portNumber6, 0, SpringLayout.SOUTH, port6Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber6, 0, SpringLayout.SOUTH, port6Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber6, 0, SpringLayout.SOUTH, port6Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber6, 0, SpringLayout.WEST, port6Label);
		layout.putConstraint(SpringLayout.NORTH, port6Label, 50, SpringLayout.SOUTH, port5Label);
		layout.putConstraint(SpringLayout.WEST, port6Label, 0, SpringLayout.WEST, port3Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber7, 0, SpringLayout.SOUTH, port7Label);
		layout.putConstraint(SpringLayout.NORTH, portNumber7, 0, SpringLayout.SOUTH, port7Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber7, 0, SpringLayout.SOUTH, port7Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber7, 0, SpringLayout.WEST, port7Label);
		layout.putConstraint(SpringLayout.NORTH, port7Label, 50, SpringLayout.SOUTH, port6Label);
		layout.putConstraint(SpringLayout.WEST, port7Label, 0, SpringLayout.WEST, port6Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber8, 0, SpringLayout.SOUTH, port8Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber8, 0, SpringLayout.WEST, port8Label);
		layout.putConstraint(SpringLayout.NORTH, port8Label, 50, SpringLayout.SOUTH, port7Label);
		layout.putConstraint(SpringLayout.WEST, port8Label, 0, SpringLayout.WEST, port7Label);
		layout.putConstraint(SpringLayout.NORTH, port3Label, 0, SpringLayout.NORTH, bridgeLabel);
		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 0, SpringLayout.NORTH, vlanLabel);

		layout.putConstraint(SpringLayout.WEST, port3Label, 125, SpringLayout.EAST, bridgeLabel);
		layout.putConstraint(SpringLayout.WEST, port3Label, 125, SpringLayout.EAST, vlanLabel);

		layout.putConstraint(SpringLayout.WEST, bridgeGroupField, 0, SpringLayout.WEST, bridgeLabel);
		layout.putConstraint(SpringLayout.WEST, vlanPortField, 0, SpringLayout.WEST, vlanLabel);

		layout.putConstraint(SpringLayout.EAST, bridgeGroupField, 0, SpringLayout.EAST, bridgeLabel);
		layout.putConstraint(SpringLayout.EAST, vlanPortField, 0, SpringLayout.EAST, vlanLabel);

		layout.putConstraint(SpringLayout.NORTH, portNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.NORTH, moduleNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.NORTH, switchNumber1, 0, SpringLayout.SOUTH, port1Label);
		layout.putConstraint(SpringLayout.WEST, switchNumber1, 0, SpringLayout.WEST, port1Label);
		layout.putConstraint(SpringLayout.WEST, portNumber1, 0, SpringLayout.EAST, moduleNumber1);
		layout.putConstraint(SpringLayout.WEST, moduleNumber1, 0, SpringLayout.EAST, switchNumber1);

		layout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, bridgeLabel);
		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 0, SpringLayout.NORTH, vlanLabel);

		layout.putConstraint(SpringLayout.SOUTH, copyButton, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, irfNumbers, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, irfNumbers, 167, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, tengigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, onegigButton, 10, SpringLayout.SOUTH, tengigButton);
		layout.putConstraint(SpringLayout.WEST, onegigButton, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 10, SpringLayout.SOUTH, bridgeLabel);

		layout.putConstraint(SpringLayout.NORTH, vlanPortField, 10, SpringLayout.SOUTH, vlanLabel);

		layout.putConstraint(SpringLayout.NORTH, bridgeLabel, 50, SpringLayout.NORTH, vlanPortField);

		layout.putConstraint(SpringLayout.NORTH, vlanLabel, 25, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, bridgeLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, vlanLabel, 25, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.NORTH, portSpeedLabel, 50, SpringLayout.SOUTH, bridgeGroupField);
		layout.putConstraint(SpringLayout.NORTH, bridgeGroupField, 50, SpringLayout.SOUTH, vlanPortField);

		layout.putConstraint(SpringLayout.NORTH, tengigButton, 10, SpringLayout.SOUTH, portSpeedLabel);
		layout.putConstraint(SpringLayout.WEST, portSpeedLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, irfNumbers, 10, SpringLayout.SOUTH, amountLabel);
		layout.putConstraint(SpringLayout.NORTH, amountLabel, 50, SpringLayout.SOUTH, onegigButton);
		layout.putConstraint(SpringLayout.WEST, amountLabel, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, port1Label, 50, SpringLayout.SOUTH, irfNumbers);
		layout.putConstraint(SpringLayout.WEST, port1Label, 25, SpringLayout.WEST, this);
	}
}
