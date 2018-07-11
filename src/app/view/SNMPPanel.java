package app.view;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class SNMPPanel extends JPanel implements MouseListener{

    private JFrame fileIO = new JFrame();
    private final String[] fileDest = {""};
    private String fileName = "";
    private ArrayList<JTextField> fields = new ArrayList<>();

    private JPanel colLayout;
    private JPanel ipLayout;
    private JPanel ipRoute;
    private JPanel ipPanels;

    private JPanel minPanel;
    private JPanel morePanel;

    private JPanel moreSubPanel1;
    private JPanel moreSubPanel2;

    private JPanel westPanels;
    private JPanel eastPanels;

    private JPanel codeLayout;
    private JPanel buttonsLayout;

    private Container springPanels;

    private Font font;
    private AppPanel panel;

    private JTextArea codePane;
    private JScrollPane textScrollPane;
    private JScrollPane westScrollPane;


    private JButton submitButton;
    private JButton copyButton;
    private JButton homeButton;
    private JButton resetButton;
    private JButton importConf;
    private JButton exportConf;

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


    public SNMPPanel(AppPanel panel)
    {
        this.setVisible(false);
        font = new Font("Neue", Font.BOLD, 13);
        this.panel = panel;

        //sets up the action buttons
        String buttonLoc = "/resources/netButtonMenu.png";
        copyButton = new JButton("Copy");
        submitButton = new JButton("Submit");
        homeButton = new JButton("HOME");
        resetButton = new JButton("Reset");
        importConf = new JButton("<html>Import<br>Config<html>");
        exportConf = new JButton("<html>Export To<br>Desktop<html>");
        setupButton(copyButton, buttonLoc);
        setupButton(submitButton, buttonLoc);
        setupButton(homeButton, buttonLoc);
        setupButton(resetButton, buttonLoc);
        setupButton(importConf, buttonLoc);
        setupButton(exportConf, buttonLoc);

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

        springPanels = new Container();

        setupChatPane();
        buildPanels();
        buildListeners();

    }

    private JTextField greyedOut(JTextField field, String text)
    {
        field = new JTextField();
        field.setText(text);
        field.setForeground(Color.GRAY);
        JTextField finalField = field;
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (finalField.getText().equals(text)){
                    finalField.setText("");
                }
                finalField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (finalField.getText().equals("")){
                    finalField.setText(text);
                    finalField.setForeground(Color.GRAY);
                }
            }


        });
        return finalField;

    }

    private void setupButton(JButton button, String pictureLoc)
    {
        ImageIcon backgroundImage = new ImageIcon(IRFPanel.class.getResource(pictureLoc));
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

    private JLabel setupLabels(JLabel label, String text)
    {
        label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Color.WHITE);

        return label;
    }

    private void setupChatPane()
    {
        codePane = new JTextArea(30,36);
        codePane.setLineWrap(false);
        codePane.setWrapStyleWord(true);
        textScrollPane = new JScrollPane(codePane);

        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    }

    private void updateTextCode()
    {
        /*
        #
sysname name (Switch Name)
#
local-user cavemin (local user name)
 password simple password (Local user password)
 authorization-attribute level 3
 service-type ssh terminal
#
interface Vlan-interface1
 ip address 10.27.0.1 255.255.0.0 (IP address & Subnet mask or Subnet Prefix)
#
public-key local create rsa
2048
y
y
#
ssh server enable
#
user-interface vty 0 15
#
user-interface vty 0 15
 authentication-mode scheme
 protocol inbound ssh
#  
ip route-static 0.0.0.0 0 XXX.XXX.XXX.XXX (Default Gateway)
#
 snmp-agent group v3 cavemin authentication write-view ViewDefault
 snmp-agent target-host trap address udp-domain 10.25.11.13 (SNMP SERVER) params securityname cavemin v3 privacy
 snmp-agent usm-user v3 cavemin cavemin cipher authentication-mode sha (SNMP Password) privacy-mode aes128 (Encryption Key)
#
ntp-service source-interface  ()
ntp-service unicast-server ()
#
snmp-agent sys-info contact ()
snmp-agent sys-into location ()
#

         */
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
                + "\n#"
                + "\nuser-interface vty 0 15"
                + "\nauthentication-mode scheme"
                + "\nprotocol inbound ssh"
                + "\n#"
                + ipRouteCheck()
                + "\n#"
                + "\nsnmp-agent group v3 " + localUsername.getText() + " authentication write-view ViewDefault"
                + "\nsnmp-agent target-host trap address udp-domain " + domainField.getText() + " params securityname " +
                        localUsername.getText() + " v3 privacy"
                + "snmp-agent usm-user v3 " + localUsername.getText() + " " + localUsername.getText() +
                        " cipher authentication-mode sha " + snmpPassword.getText() + " privacy-mode aes128 " + encKey.getText()
                + "\n#"
                + ntpText()
                + snmpAgentText()

        );
    }

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

    private void copyText()
    {
        StringSelection selection = new StringSelection(codePane.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

//    private String getFile(){
//
//        final String[] fileDest = {""};
//
//        new FileDrop( codePane, /*dragBorder,*/ new FileDrop.Listener()
//        {
//            public void filesDropped( java.io.File[] files )
//            {   for( int i = 0; i < files.length; i++ )
//                {   try
//                    {   fileDest[0] += files[i].getCanonicalPath();
//                    }   // end try
//                    catch( java.io.IOException e ) {}
//                }   // end for: through each dropped file
//            }   // end filesDropped
//        }); // end FileDrop.Listener
//
//        return fileDest[0];
//        Object[] options = {"Submit","Cancel"};
//        int n = JOptionPane.showOptionDialog(fileIO,configInput,"",
//                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
//                null, options, options[1]);
//        if (n==JOptionPane.OK_OPTION){
//            return fileDest[0];
//        }else if (n == JOptionPane.NO_OPTION){
//            return null;
//        }else if (n == JOptionPane.CLOSED_OPTION){
//            return null;
//        }
//        return null;
//    }

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

    private String snmpAgentText(){
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
    //"\nip route-static " + ipRoute1.getText() + " " + ipRoute2.getText() + " " + ipRoute3.getText()

    private String ipRouteCheck()
    {
        String ipRouteLine = "";

        if ((!ipRoute1.getText().equals("Source (0.0.0.0)"))){
            ipRouteLine += "\nip route-static " + ipRoute1.getText() + " ";
        }
        else{
            ipRouteLine += "\nip route-static 0.0.0.0 ";
        }

        if (!ipRoute2.getText().equals("Destination (0)")){
            ipRouteLine += ipRoute2.getText() + " ";
        }
        else{
            ipRouteLine += "0 ";
        }

        if (!ipRoute3.getText().equals("Next Hop (10.0.0.0)")){
            ipRouteLine += ipRoute3.getText() + " ";
        }
        else{
            ipRouteLine += "10.0.0.0";
        }

        return ipRouteLine;
    }

    private void buildPanels()
    {
        this.setOpaque(false);
        colLayout.setLayout(new GridLayout(0,1));
        colLayout.setBorder(BorderFactory.createLoweredBevelBorder());

        colLayout.add(localUsernameLabel);
        colLayout.add(localUsername);
        colLayout.add(localPasswordLabel);
        colLayout.add(localPassword);
        colLayout.add(domainLabel);
        colLayout.add(domainField);
        colLayout.add(snmpPasswordLabel);
        colLayout.add(snmpPassword);
        colLayout.add(encKeyLabel);
        colLayout.add(encKey);

        colLayout.add(sysNameLabel);
        colLayout.add(sysName);
        colLayout.add(vlanInterfaceLabel);
        colLayout.add(vlanInterface);
        colLayout.setOpaque(false);

        ipLayout.setLayout(new GridLayout(0, 1,20,0));
        ipLayout.setBorder(BorderFactory.createLoweredBevelBorder());
//        ipLayout.add(fillerLabel());
        ipLayout.add(ipAddressLabel);
//        ipLayout.add(fillerLabel());
//        ipLayout.add(numLabel(1));
        ipLayout.add(ipAddress1);
//        ipLayout.add(numLabel(1));
//        ipLayout.add(numLabel(2));
        ipLayout.add(ipSubnetLabel);
        ipLayout.add(ipAddress2);
//        ipLayout.add(numLabel(2));
        ipLayout.add(ipRoute2);
//        ipLayout.add(fillerLabel());
//        ipLayout.add(numLabel(3));
        ipLayout.setOpaque(false);

        ipRoute.setLayout(new GridLayout(0, 1,20,0));
        ipRoute.setBorder(BorderFactory.createLoweredBevelBorder());

        ipRoute.add(ipRouteStaticLabel);
        ipRoute.add(ipRoute1);
        ipRoute.add(ipRoute2);
        ipRoute.add(ipRoute3);
        ipRoute.setOpaque(false);

        ipPanels.setLayout(new BoxLayout(ipPanels, BoxLayout.LINE_AXIS));
        ipPanels.add(ipLayout);
        ipPanels.add(ipRoute);
        ipPanels.setOpaque(false);

        minPanel.setLayout(new BoxLayout(minPanel, BoxLayout.PAGE_AXIS));
        minPanel.setBorder(BorderFactory.createTitledBorder("Minimum requirements for setup"));
        minPanel.add(colLayout);
        minPanel.add(ipPanels);
        minPanel.setOpaque(false);

        morePanel.setLayout(new BoxLayout(morePanel, BoxLayout.PAGE_AXIS));
        morePanel.setBorder(BorderFactory.createTitledBorder("Optional fields"));
//        morePanel.add(serverLayout);
        moreSubPanel1.setLayout(new BoxLayout(moreSubPanel1, BoxLayout.PAGE_AXIS));
        moreSubPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
        moreSubPanel1.add(sourceInterfaceLabel);
        moreSubPanel1.add(sourceInterface);
        moreSubPanel1.add(unicastServerLabel);
        moreSubPanel1.add(unicastServer);

        moreSubPanel2.setLayout(new BoxLayout(moreSubPanel2, BoxLayout.PAGE_AXIS));
        moreSubPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
        moreSubPanel2.add(phoneNumLabel);
        moreSubPanel2.add(agentPhoneNum);
        moreSubPanel2.add(agentLocLabel);
        moreSubPanel2.add(agentLoc);

        morePanel.add(moreSubPanel1);
        morePanel.add(moreSubPanel2);

        moreSubPanel1.setOpaque(false);
        moreSubPanel2.setOpaque(false);
        morePanel.setOpaque(false);

        westPanels.setLayout(new BoxLayout(westPanels, BoxLayout.PAGE_AXIS));
        westPanels.add(minPanel);
        westPanels.add(morePanel);
        westPanels.setOpaque(false);

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

        codeLayout.setLayout(new BoxLayout(codeLayout, BoxLayout.PAGE_AXIS));
        codeLayout.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()),"Code Output"));
        codeLayout.add(textScrollPane);
        codeLayout.setOpaque(false);

//        buttonsLayout.setLayout(new BoxLayout(buttonsLayout, BoxLayout.LINE_AXIS));
        buttonsLayout.setLayout(new FlowLayout());
        buttonsLayout.add(submitButton);
        buttonsLayout.add(copyButton);
        buttonsLayout.add(exportConf);
        importConf.setEnabled(false);
        buttonsLayout.add(importConf);
        buttonsLayout.add(resetButton);
        buttonsLayout.add(homeButton);
        buttonsLayout.setOpaque(false);

        eastPanels.setLayout(new BoxLayout(eastPanels, BoxLayout.PAGE_AXIS));
        eastPanels.add(codeLayout);
        eastPanels.add(buttonsLayout);
        eastPanels.setOpaque(false);

        springPanels.setLayout(new BoxLayout(springPanels, BoxLayout.LINE_AXIS));
        springPanels.add(westScrollPane);
        springPanels.add(eastPanels);
        springPanels.setFont(font);

        this.setLayout(new SpringLayout());
        this.add(springPanels);

    }

    private JLabel fillerLabel()
    {
        return new JLabel(" ");
    }

//    private JLabel numLabel(int num){
//        String strNum = String.valueOf(num);
//        JLabel label = new JLabel(strNum, SwingConstants.RIGHT);
//
//        return label;
//    }

    private boolean checkFields(){
        if (agentLoc.getText().equals("") || agentLoc.getText().equals("") || sysName.getText().equals("")
                || vlanInterface.getText().equals("") || ipAddress1.getText().equals("")
                || ipAddress2.getText().equals("") || ipRoute1.getText().equals("") || ipRoute2.getText().equals("")
                || ipRoute3.getText().equals("")){
            Object[] options = {"Continue","Cancel"};
            int n = JOptionPane.showOptionDialog(new JFrame(),new JLabel(
                    "<html>Not all required fields have been submitted<br>" +
                            "are you sure you want to continue?<html>"),"",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);
            if (n==JOptionPane.OK_OPTION){
                return true;
            }else if (n == JOptionPane.NO_OPTION){

                return false;
            }else if (n == JOptionPane.CLOSED_OPTION) {
                return false;
            }else{
                return false;
            }
        }else{
            return true;
        }


    }

    private void updateField(JTextField field, String text)
    {
        field.setText(text);
    }

    private void dropMethod(){
        new FileDrop( this, true, new FileDrop.Listener()
        {
            public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {
                    try
                    {
                        fileDest[0] = files[i].getCanonicalPath();
                        File f = new File(fileDest[0]);
                        BufferedReader br = new BufferedReader(new FileReader(f));

                        String line;
                        int counter = 0;

                        while ((line = br.readLine()) != null) {
                            if(line.equals("#")){

                            }else{
                                if(counter >= fields.size()){

                                }else{
                                    updateField(fields.get(counter),line);
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

        // Listener for the Import Button
//        this.importConf.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent clicked)
//            {
//                String fileDest = getFile();
//                if (fileDest != null){
//                    try{
//
//                        File f = new File(fileDest);
//                        FileOutputStream out = new FileOutputStream(f);
//
//                        codePane.setText(String.valueOf(out));
//                        out.close();
//                    }catch (FileNotFoundException e){e.printStackTrace();
//                    }catch (IOException e){}
//
//                }
//
//
//            }
//        });


        // Listener for the Export Button
        this.exportConf.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
//                fileName = "";
                String desktop = System.getProperty("user.home") + "/Desktop";
                JTextField fileNameInput = new JTextField();
                JLabel fileNameLabel = new JLabel("<html>Enter file name here<br><strong>WITH FILE EXTENSION<strong><html>");
                JPanel fileNamePanel = new JPanel();
                fileNamePanel.setLayout(new BoxLayout(fileNamePanel, BoxLayout.PAGE_AXIS));

                fileNamePanel.add(fileNameLabel);
                fileNamePanel.add(fileNameInput);
                fileNamePanel.setOpaque(false);
                fileNamePanel.setVisible(true);

                if (checkFields()){
                    Object[] options = {"Cancel","Submit"};
                    int n = JOptionPane.showOptionDialog(new JFrame(),fileNamePanel,"",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);
                    if (n==JOptionPane.OK_OPTION){

                    }else if (n == JOptionPane.NO_OPTION){
                        fileName = fileNameInput.getText();

                    }else if (n == JOptionPane.CLOSED_OPTION) {

                    }
                    if (!(fileName.equals("")) && fileName.trim().equals(fileName)){
                        try{

                            //exported info to be put into switch
                            File f = new File(desktop,fileName);
                            BufferedWriter out = new BufferedWriter(new FileWriter(f));
                            out.write(codePane.getText());
                            out.close();

                            //exported info for dropping in program
                            File f2 = new File(desktop,"DRAG_AND_DROP_ME-" + fileName);
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
                }





            }
        });

        this.addMouseListener(this);









    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        dropMethod();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
