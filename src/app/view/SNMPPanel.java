package app.view;

//import statements
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class that acts as a JPanel for when the 'SNMP Setup' button is pressed from the main menu
 * works to take in data inputs from the user, organize/compile it effectively, and give options for exportation of data
 */
public class SNMPPanel extends JPanel{

    //All of the class's instance variables
    //variables for file IO
    private final String[] fileDest = {""};
    private String fileName = "";

    //ArrayList for organizing all the JTextFields
    private ArrayList<JTextField> fields = new ArrayList<>();

    //panels for organizing the JComponents
    private JPanel colLayout;
    private JPanel ipLayout;
    private JPanel ipRoute;
    private JPanel ipPanels;

    private JPanel minPanel;
    private JPanel morePanel;

    private JPanel moreSubPanel1;
    private JPanel moreSubPanel2;
    private JPanel moreSubPanel3;

    private JPanel westPanels;
    private JPanel eastPanels;

    private JPanel codeLayout;
    private JPanel buttonsLayout;

    //container to help mobilize components and their panels from their individual layout to a SpringLayout
    private Container springPanels;

    //font used in text for the buttons and labels
    private Font font;

    //menu panel for if the user decides to click the home button
    private AppPanel panel;

    //major components of program to help organize JComponents
    private JTextArea codePane;
    private JScrollPane eastScrollPane;
    private JScrollPane westScrollPane;

    //JButtons with action listeners added later on
    private JButton submitButton;
    private JButton copyButton;
    private JButton homeButton;
    private JButton resetButton;
    private JButton exportInfo;

    //JTextFields for user input
    private JTextField agentPhoneNum;
    private JTextField agentLoc;
    private JTextField sysName;
    private JTextField vlanInterface;
    private JTextField ipAddress1;
    private JTextField ipAddress2;
    private JTextField ipRoute1;
    private JTextField ipRoute2;
    private JTextField ipRoute3;
    private JTextField sourceInterface;
    private JTextField unicastServer;
    private JTextField snmpPassword;
    private JTextField localPassword;
    private JTextField localUsername;
    private JTextField encKey;
    private JTextField domainField;

    //JLabels for better user understanding of functionality
    private JLabel phoneNumLabel;
    private JLabel agentLocLabel;
    private JLabel sysNameLabel;
    private JLabel vlanInterfaceLabel;
    private JLabel ipAddressLabel;
    private JLabel ipSubnetLabel;
    private JLabel ipRouteStaticLabel;
    private JLabel sourceInterfaceLabel;
    private JLabel unicastServerLabel;
    private JLabel snmpPasswordLabel;
    private JLabel localPasswordLabel;
    private JLabel localUsernameLabel;
    private JLabel encKeyLabel;
    private JLabel domainLabel;

    private JLabel configInput;

    /**
     * Constructor to initialize all the components and variables
     * @param panel from main menu
     */
    public SNMPPanel(AppPanel panel)
    {
        //sets the entire panel visible
        this.setVisible(false);
        this.panel = panel;

        //initializes the font
        font = new Font("Neue", Font.BOLD, 13);

        //sets up the action buttons
        String buttonLoc = "/resources/netButtonSmall.png";
        copyButton = new JButton("Copy");
        submitButton = new JButton("Submit");
        homeButton = new JButton("HOME");
        resetButton = new JButton("Reset");
        exportInfo = new JButton("<html>Export To<br>Desktop<html>");
        setupButton(copyButton, buttonLoc);
        setupButton(submitButton, buttonLoc);
        setupButton(homeButton, buttonLoc);
        setupButton(resetButton, buttonLoc);
        setupButton(exportInfo, buttonLoc);

        //sets up the labels
        phoneNumLabel = setupLabels(phoneNumLabel, "Phone Number");
        agentLocLabel = setupLabels(agentLocLabel, "Agent Location");
        sysNameLabel = setupLabels(sysNameLabel, "Switch Name");
        vlanInterfaceLabel = setupLabels(vlanInterfaceLabel, "Vlan-interface");
        ipAddressLabel = setupLabels(ipAddressLabel, "IP Address     ");
        ipSubnetLabel = setupLabels(ipSubnetLabel, "Subnet Mask");
        ipRouteStaticLabel = setupLabels(ipRouteStaticLabel, "Default Gateway");
        sourceInterfaceLabel = setupLabels(sourceInterfaceLabel, "NTP Source Interface");
        unicastServerLabel = setupLabels(unicastServerLabel, "NTP Server IP");
        snmpPasswordLabel = setupLabels(snmpPasswordLabel, "SNMP Password");
        localPasswordLabel = setupLabels(localPasswordLabel, "Local User Password");
        localUsernameLabel = setupLabels(localUsernameLabel, "Local User Name");
        encKeyLabel = setupLabels(encKeyLabel, "Encryption Key");
        domainLabel = setupLabels(domainLabel,"SNMP Server IP");
        configInput = setupLabels(configInput,"Drag config file here");



        //set up the components
        agentPhoneNum = new JTextField();
        agentLoc = new JTextField();
        sysName = new JTextField();
        vlanInterface = new JTextField("1");
        ipAddress1 = new JTextField();
        ipAddress2 = new JTextField();
        ipRoute1 = new JTextField();
        ipRoute2 = new JTextField();
        ipRoute3 = new JTextField();
        ipRoute1 = greyedOut(ipRoute1,"Source (0.0.0.0)");
        ipRoute2 = greyedOut(ipRoute2,"Destination (0)");
        ipRoute3 = greyedOut(ipRoute3, "Next Hop (10.0.0.0)");

        sourceInterface = new JTextField();
        unicastServer = new JTextField();
        snmpPassword = new JTextField();
        localPassword = new JTextField();
        localUsername = new JTextField();
        encKey = new JTextField();
        domainField = new JTextField();

        //populates the ArrayList
        fields.add(localUsername);
        fields.add(localPassword);
        fields.add(domainField);
        fields.add(snmpPassword);
        fields.add(encKey);
        fields.add(sysName);
        fields.add(vlanInterface);
        fields.add(ipAddress1);
        fields.add(ipAddress2);
        fields.add(ipRoute1);
        fields.add(ipRoute2);
        fields.add(ipRoute3);
        fields.add(sourceInterface);
        fields.add(unicastServer);
        fields.add(agentPhoneNum);
        fields.add(agentLoc);



        //sets up the panels
        colLayout = new JPanel();
        ipLayout = new JPanel();
        ipRoute = new JPanel();
        ipPanels = new JPanel();
        westPanels = new JPanel();
        eastPanels = new JPanel();
        codeLayout = new JPanel();
        buttonsLayout = new JPanel();
        minPanel = new JPanel();
        morePanel = new JPanel();
        moreSubPanel1 = new JPanel();
        moreSubPanel2 = new JPanel();
        moreSubPanel3 = new JPanel();

        //sets up the main Container
        springPanels = new Container();

        //calls helper methods
        setupChatPane();
        buildPanels();
        buildListeners();

    }

    /**
     * Helper method to dry out code
     * sets up JLabel components
     * @param label to be set up
     * @param text to be displayed on the label
     * @return the newly factored JLabel
     */
    private JLabel setupLabels(JLabel label, String text)
    {
        label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.WHITE);

        return label;
    }

    /**
     * helper method that sets up the buttons visually
     * @param button to be set up
     * @param pictureLoc
     */
    private void setupButton(JButton button, String pictureLoc)
    {
        ImageIcon backgroundImage = new ImageIcon(SNMPPanel.class.getResource(pictureLoc));
        Image image = backgroundImage.getImage();
        image = image.getScaledInstance(75, 45, java.awt.Image.SCALE_FAST);
        backgroundImage = new ImageIcon(image);
        button.setIcon(backgroundImage);

        button.setBorderPainted(false);
        button.setBorder(null);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(font);
        button.setForeground(Color.DARK_GRAY);
    }

    /**
     * helper method to make the JTextField have grayed out text
     * that disappears when the field is selected, and reappears when deselected
     * @param field to be grayed out
     * @param text to display in the field
     * @return a modified JTextField
     */
    private JTextField greyedOut(JTextField field, String text)
    {
        //declares the initial grayed out text
//        field = new JTextField();
        field.setText(text);
        field.setForeground(Color.GRAY);
//        JTextField finalField = field;

        //adds the focus listeners
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(text)){
                    field.setText("");
                }
                field.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().equals("")){
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }


        });
        return field;

    }

    /**
     * Sets up the JTextArea that displays the script for setting up the switch
     */
    private void setupChatPane()
    {
        //sets up the JTextArea
        codePane = new JTextArea(30,36);
        codePane.setLineWrap(false);
        codePane.setWrapStyleWord(true);
        eastScrollPane = new JScrollPane(codePane);

        //scrollbar policies
        eastScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        eastScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Prints out text to the JTextArea @app.view.SNMPPanel#codePane
     * updates the corresponding variables based on what is in their JTextFields
     */
    private void updateTextCode()
    {
        this.codePane.setText(
                "system-view"
                + "\n#"
                + "\nsysname " + sysName.getText()
                + "\n#"
                + "\nlocal-user " + localUsername.getText()
                + "\npassword simple " + localPassword.getText()
                + "\nauthorization-attribute level 3"
                + "\nservice-type ssh terminal"
                + "\n#"
                + "\ninterface Vlan-interface " + vlanInterface.getText()
                + "\nip address " + ipAddress1.getText() + " " + ipAddress2.getText()
                + "\n#"
                + "\npublic-key local create rsa"
                + "\n2048"
                + "\ny"
                + "\ny"
                + "\n#"
                + "\nssh server enable"
                + "\n#"
                + "\nuser-interface vty 0 15"
                + "\nauthentication-mode scheme"
                + "\nprotocol inbound ssh"
                + "\n#"
                + ipRouteCheck() //helper method triggered if a field is filled
                + snmpTarget() //helper method triggered if a field is filled
                + ntpText() //helper method triggered if a field is filled
                + snmpContact() //helper method triggered if a field is filled

        );

        ipRoute1.grabFocus();
        ipRoute2.grabFocus();
        ipRoute3.grabFocus();
        localUsername.grabFocus();
    }

    /**
     * if any NTP field is filled out, then build the corresponding code
     * @return optional code in the codePane
     */
    private String ntpText(){
        if (sourceInterface.getText().equals("") && unicastServer.getText().equals("")){
            return "";
        }else{
            return ""
                    + "\nntp-service source-interface  " + sourceInterface.getText()
                    + "\nntp-service unicast-server " + unicastServer.getText()
                    +"\n#"
                    ;
        }

    }

    /**
     * if any SNMP agent field is filled out, then build the corresponding code
     * @return optional code in the codePane
     */
    private String snmpContact(){
        if (agentPhoneNum.getText().equals("") && agentLoc.getText().equals("")){
            return "";
        }else{
            return ""
                    + "\nsnmp-agent sys-info contact " + agentPhoneNum.getText()
                    + "\nsnmp-agent sys-into location " + agentLoc.getText()
                    + "\n#"
                    ;
        }
    }

    private String snmpTarget(){
        if (domainField.getText().equals("") && snmpPassword.getText().equals("") && encKey.getText().equals("")){
            return "";
        }else{
            return "\n#"
                    + "\nsnmp-agent group v3 " + localUsername.getText() + " authentication write-view ViewDefault"
                    + "\nsnmp-agent target-host trap address udp-domain " + domainField.getText() + " params securityname " +
                    localUsername.getText() + " v3 privacy"
                    + "snmp-agent usm-user v3 " + localUsername.getText() + " " + localUsername.getText() +
                    " cipher authentication-mode sha " + snmpPassword.getText() + " privacy-mode aes128 " + encKey.getText()
                    + "\n#";
        }
    }

    /**
     * builds correct IP route code instead of what the grayed out text is
     * @return ipRouteLine
     */
    private String ipRouteCheck()
    {
        //line to be built
        String ipRouteLine = "";

        //1st part
        if ((!ipRoute1.getText().equals("Source (0.0.0.0)"))){
            ipRouteLine += "\nip route-static " + ipRoute1.getText() + " ";
        }
        else{
            ipRouteLine += "\nip route-static 0.0.0.0 ";
        }

        //2nd part
        if (!ipRoute2.getText().equals("Destination (0)")){
            ipRouteLine += ipRoute2.getText() + " ";
        }
        else{
            ipRouteLine += "0 ";
        }

        //3rd part
        if (!ipRoute3.getText().equals("Next Hop (10.0.0.0)")){
            ipRouteLine += ipRoute3.getText() + " ";
        }
        else{
            ipRouteLine += "10.0.0.0";
        }

        //return statement
        return ipRouteLine;
    }

    /**
     * Sets all JTextFields back to their original states at the start of the program
     */
    private void resetPanel()
    {
        //Reset username and passwords
        snmpPassword.setText("");
        localPassword.setText("");
        localUsername.setText("");
        encKey.setText("");

        //Reset agent phone number
        agentPhoneNum.setText("");

        //Reset agent location
        agentLoc.setText("");

        //Reset system name
        sysName.setText("");

        //Reset Vlan interface
        vlanInterface.setText("1");

        //Reset ip addresses
        ipAddress1.setText("");
        ipAddress2.setText("");

        //Reset ip routes
        ipRoute1.setText("");
        ipRoute2.setText("");
        ipRoute3.setText("");
        //requesting focus to trigger the FocusLost event and set gray text
        ipRoute1.requestFocus();
        ipRoute2.requestFocus();
        ipRoute3.requestFocus();
        localUsername.requestFocus();

        //Reset source interface
        sourceInterface.setText("");

        //Reset unicast server
        unicastServer.setText("");

        //Resets domain ip address
        domainField.setText("");

        // Reset TextBox
        codePane.setText("");
    }

    /**
     * acts as a CMD + C for the text in the codePane
     */
    private void copyText()
    {
        StringSelection selection = new StringSelection(codePane.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    /**
     * sets all the components in their corresponding panels/containers
     */
    private void buildPanels()
    {
        //setting parent panel to be visible
        this.setOpaque(false);
        //setting up the leftmost side of components in a GridLayout
        colLayout.setLayout(new GridLayout(0,1));
        colLayout.setBorder(BorderFactory.createLoweredBevelBorder());

        colLayout.add(localUsernameLabel);
        colLayout.add(localUsername);
        colLayout.add(localPasswordLabel);
        colLayout.add(localPassword);
//        colLayout.add(domainLabel);
//        colLayout.add(domainField);
//        colLayout.add(snmpPasswordLabel);
//        colLayout.add(snmpPassword);
//        colLayout.add(encKeyLabel);
//        colLayout.add(encKey);

        colLayout.add(sysNameLabel);
        colLayout.add(sysName);
        colLayout.add(vlanInterfaceLabel);
        colLayout.add(vlanInterface);
        colLayout.setOpaque(false);

        //nested panel to set up the IP layout in a GridLayout
        ipLayout.setLayout(new GridLayout(0, 1,20,0));
        ipLayout.setBorder(BorderFactory.createLoweredBevelBorder());
        ipLayout.add(ipAddressLabel);
        ipLayout.add(ipAddress1);
        ipLayout.add(ipSubnetLabel);
        ipLayout.add(ipAddress2);
        ipLayout.add(ipRoute2);
        ipLayout.setOpaque(false);

        //setting up the IP route in a GridLayout
        ipRoute.setLayout(new GridLayout(0, 1,20,0));
        ipRoute.setBorder(BorderFactory.createLoweredBevelBorder());

        ipRoute.add(ipRouteStaticLabel);
        ipRoute.add(ipRoute1);
        ipRoute.add(ipRoute2);
        ipRoute.add(ipRoute3);
        ipRoute.setOpaque(false);

        //combining both the ip and route layouts
        ipPanels.setLayout(new BoxLayout(ipPanels, BoxLayout.LINE_AXIS));
        ipPanels.add(ipLayout);
        ipPanels.add(ipRoute);
        ipPanels.setOpaque(false);

        //combining the column layout and ip layout in a BoxLayout
        minPanel.setLayout(new BoxLayout(minPanel, BoxLayout.PAGE_AXIS));
        minPanel.setBorder(BorderFactory.createTitledBorder("Minimum requirements for setup"));
        minPanel.add(colLayout);
        minPanel.add(ipPanels);
        minPanel.setOpaque(false);

        //panel to be added below the column layout and ip layout
        morePanel.setLayout(new BoxLayout(morePanel, BoxLayout.PAGE_AXIS));
        morePanel.setBorder(BorderFactory.createTitledBorder("Optional fields"));

        //nested panel to be separated with a border and placed under the morePanel panel
        moreSubPanel1.setLayout(new BoxLayout(moreSubPanel1, BoxLayout.PAGE_AXIS));
        moreSubPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
        moreSubPanel1.add(sourceInterfaceLabel);
        moreSubPanel1.add(sourceInterface);
        moreSubPanel1.add(unicastServerLabel);
        moreSubPanel1.add(unicastServer);

        //nested panel to be separated with a border and placed under the morePanel panel
        moreSubPanel2.setLayout(new BoxLayout(moreSubPanel2, BoxLayout.PAGE_AXIS));
        moreSubPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
        moreSubPanel2.add(phoneNumLabel);
        moreSubPanel2.add(agentPhoneNum);
        moreSubPanel2.add(agentLocLabel);
        moreSubPanel2.add(agentLoc);

        //nested panel to be separated with a border and placed under the morePanel panel
        moreSubPanel3.setLayout(new BoxLayout(moreSubPanel3, BoxLayout.PAGE_AXIS));
        moreSubPanel3.setBorder(BorderFactory.createLoweredBevelBorder());
        moreSubPanel3.add(domainLabel);
        moreSubPanel3.add(domainField);
        moreSubPanel3.add(snmpPasswordLabel);
        moreSubPanel3.add(snmpPassword);
        moreSubPanel3.add(encKeyLabel);
        moreSubPanel3.add(encKey);


        //combining the two panels listed above
        morePanel.add(moreSubPanel1);
        morePanel.add(moreSubPanel2);
        morePanel.add(moreSubPanel3);

        //setting them all to be visible
        moreSubPanel1.setOpaque(false);
        moreSubPanel2.setOpaque(false);
        moreSubPanel3.setOpaque(false);
        morePanel.setOpaque(false);

        //combining ALL west panels into one nicely organized BoxLayout panel in the frame
        westPanels.setLayout(new BoxLayout(westPanels, BoxLayout.PAGE_AXIS));
        westPanels.add(minPanel);
        westPanels.add(morePanel);
        westPanels.setOpaque(false);

        //setting the west panels in a scrollPane to activate scrolling capabilities
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setVisibleAmount(2);
        scrollBar.setOpaque(false);
        westScrollPane = new JScrollPane(scrollBar);
        westScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        westScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        westScrollPane.setPreferredSize(new Dimension(300,200));
        westScrollPane.revalidate();

        JViewport viewport = new JViewport();
        viewport.setView(westPanels);
        westScrollPane.setViewport(viewport);
        westScrollPane.getViewport().setOpaque(false);
        westScrollPane.setOpaque(false);

        //setting the codeLayout on the East side of the frame
        codeLayout.setLayout(new BoxLayout(codeLayout, BoxLayout.PAGE_AXIS));
        codeLayout.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()),"Code Output"));
        codeLayout.add(eastScrollPane);
        codeLayout.setOpaque(false);

        //setting the buttonLayout to go below the codeLayout but be alligned horizontally
        buttonsLayout.setLayout(new FlowLayout());
        buttonsLayout.add(submitButton);
        buttonsLayout.add(copyButton);
        buttonsLayout.add(exportInfo);
        buttonsLayout.add(resetButton);
        buttonsLayout.add(homeButton);
        buttonsLayout.setOpaque(false);

        //putting the codeLayout and buttonLayout into one nicely fitted panel
        eastPanels.setLayout(new BoxLayout(eastPanels, BoxLayout.PAGE_AXIS));
        eastPanels.add(codeLayout);
        eastPanels.add(buttonsLayout);
        eastPanels.setOpaque(false);

        //putting the east and west panels into one panel
        springPanels.setLayout(new BoxLayout(springPanels, BoxLayout.LINE_AXIS));
        springPanels.add(westScrollPane);
        springPanels.add(eastPanels);
        springPanels.setFont(font);

        //setting the parent as a SpringLayout to be compatible with the rest of the program
        this.setLayout(new SpringLayout());
        //adding components
        this.add(springPanels);

    }

    /**
     * Method to help user know that they haven't filled out all the required fields to make a functioning script
     * with the added ability to bypass the check
     * @return true to build code, false to not
     */
    private boolean noEmptyFields()
    {
        //checks if any of the fields are empty
        if (agentLoc.getText().equals("") || agentLoc.getText().equals("") || sysName.getText().equals("")
                || vlanInterface.getText().equals("") || ipAddress1.getText().equals("")
                || ipAddress2.getText().equals("") || ipRoute1.getText().equals("") || ipRoute2.getText().equals("")
                || ipRoute3.getText().equals("") || localUsername.getText().equals("")
                || localPassword.getText().equals("")){

            //builds the option Objects
            Object[] options = {"Continue","Cancel"};
            //builds the pop up window for user input
            int n = JOptionPane.showOptionDialog(new JFrame(),new JLabel(
                    "<html>Not all required fields have been submitted<br>" +
                            "are you sure you want to continue?<html>"),"",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            //'Continue' is selected
            if (n==JOptionPane.OK_OPTION){
                return true;
            //'Cancel' is selected
            }else if (n == JOptionPane.NO_OPTION){
                return false;
            //red X is pressed
            }else if (n == JOptionPane.CLOSED_OPTION) {
                return false;
            //anything else happens
            }else{
                return false;
            }

        //all fields are filled out
        }else{
            return true;
        }
    }

    /**
     * Builds all the other Listeners for buttons and mouse actions
     */
    private void buildListeners()
    {
        // Listener for the Submit Button
        this.submitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent clicked)
            {
                updateTextCode();
            }
        });

        // Listener for Home Button
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

        // Listener for the Export Button
        this.exportInfo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String desktop = System.getProperty("user.home") + "/Desktop";
                JTextField fileNameInput = new JTextField();
                JLabel fileNameLabel = new JLabel("<html>Enter file name here<br><strong>WITH FILE EXTENSION<strong><html>");
                JPanel fileNamePanel = new JPanel();
                fileNamePanel.setLayout(new BoxLayout(fileNamePanel, BoxLayout.PAGE_AXIS));

                fileNamePanel.add(fileNameLabel);
                fileNamePanel.add(fileNameInput);
                fileNamePanel.setOpaque(false);
                fileNamePanel.setVisible(true);

                if (noEmptyFields()){
                    Object[] options = {"Cancel","Submit"};
                    int n = JOptionPane.showOptionDialog(new JFrame(),fileNamePanel,"",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);
                    if (n==JOptionPane.OK_OPTION){

                    }else if (n == JOptionPane.NO_OPTION){
                        fileName = fileNameInput.getText();

                        if (!(fileName.equals("")) && fileName.trim().equals(fileName)){
                            try{

                                //exported info to be put into switch
                                File f = new File(desktop,fileName);
                                BufferedWriter out = new BufferedWriter(new FileWriter(f));
                                out.write(codePane.getText());
                                out.close();

                                //exported info for dropping in program
                                File f2 = new File(desktop,"DRAG_AND_DROP-" + fileName);
                                BufferedWriter out2 = new BufferedWriter(new FileWriter(f2));
                                for (JTextField field :
                                        fields)
                                {
                                    out2.write(field.getText() + "\n");
                                }
                                out2.close();
                            }catch(FileNotFoundException e1)
                            {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "File must have something as a name and have no spaces",
                                    "Invalid File Name",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }else if (n == JOptionPane.CLOSED_OPTION) {

                    }
                }
            }
        });

        //Object that builds a FileDrop listener on the entire SNMPPanel
        new FileDrop( this, true, new FileDrop.Listener()
        {
            public void filesDropped( java.io.File[] files )
            {
                for( int i = 0; i < files.length; i++ )
                {
                    try
                    {
                        fileDest[0] = files[i].getCanonicalPath();
                        File f = new File(fileDest[0]);
                        BufferedReader br = new BufferedReader(new FileReader(f));

                        String line;
                        int counter = 0;

                        while ((line = br.readLine()) != null)
                        {
                            if(line.equals("#"))
                            {
                                //do nothing
                            }
                            else
                            {
                                if(counter >= fields.size())
                                {



                                }
                                else
                                {
                                    fields.get(counter).setText(line);
                                    counter++;
                                }

                            }

                        }

                        updateTextCode();
                        fileDest[0] = null;

                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
    }
}
