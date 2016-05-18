/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import model.Contact;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class AddressBookFrame extends JFrame{
    JList lstContacts;
    JScrollPane scrPane;
    JTextField txtName;
    JTextField txtTel;
    private JTextField txtLastName;

    public AddressBookFrame() throws HeadlessException {
        run();
    }
    
    
    public void run(){
    initComponents();    
    }
    void initComponents(){
        
        scrPane = new JScrollPane();
        scrPane.setPreferredSize(new Dimension(170, 50));
        lstContacts = new JList<Contact>();
        scrPane.setViewportView(lstContacts);
        DefaultListModel defModel = new DefaultListModel();
        defModel.addElement("Behrooz,shahin");
        lstContacts.setModel(defModel);
        JPanel rightPanel = new JPanel();
        prepareRightPanel(rightPanel);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(scrPane,BorderLayout.CENTER);
        
        JPanel bottomLine = new JPanel(new GridLayout(1,5));
        bottomLine.add(new JButton("1"));
        bottomLine.add(new JButton("2"));
        bottomLine.add(new JButton("3"));
        bottomLine.add(new JButton("4"));
        bottomLine.add(new JButton("5"));
        
        //middle panel contains right panel and list
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(scrPane,BorderLayout.WEST);
        middlePanel.add(rightPanel,BorderLayout.CENTER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(middlePanel,BorderLayout.CENTER);
        mainPanel.add(bottomLine,BorderLayout.SOUTH);
        this.setContentPane(mainPanel);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    private void prepareRightPanel(JPanel rightPanel) {
    JPanel panel1 = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.NONE;
    //margin set to 
    c.insets = new Insets(7,7,7,7);
    prepareConstraint(c, 1, 1, 0, 0, GridBagConstraints.EAST);
    panel1.add(new JLabel("Last Name"),c);
    // last name textfield
    txtLastName = new JTextField(12);
    //prepareConstraint(c,gridwidth,gridheight,gridx,gridy,anchor);
    prepareConstraint(c, 1, 1, 1, 0, GridBagConstraints.WEST);
    panel1.add(txtLastName,c);
    //label for first name same row (gridy=0), next column(gridx=2)
    JLabel lblFirstName = new JLabel("First Name");
    prepareConstraint(c, 1, 1, 2, 0, GridBagConstraints.EAST);
    panel1.add(lblFirstName,c);
    //first name text field
    JTextField txtFirstName = new JTextField(7);
    // same row (gridy=0) next column (gridx=3)
    prepareConstraint(c, 1, 1, 3, 0, GridBagConstraints.WEST);
    panel1.add(txtFirstName,c);
    //label for middle name
    JLabel lblMiddleName = new JLabel("Middle Name");
    //same row gridy=0 next column gridx=4
    prepareConstraint(c, 1, 1, 4, 0, GridBagConstraints.EAST);
    panel1.add(lblMiddleName,c);
    //text field for middle name
    JTextField txtMiddleName = new JTextField(7);
    //same row gridy=0 and next column gridx=5
    prepareConstraint(c, 1, 1, 5, 0, GridBagConstraints.WEST);
    panel1.add(txtMiddleName,c);
    
    //second row gridx=0 gridy=1
    JLabel lblPhone = new JLabel("Phone");
    prepareConstraint(c, 1, 1, 0, 1, GridBagConstraints.EAST);
    panel1.add(lblPhone,c);
    //text field gridx=1 gridy=1 fill horozon
    c.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtPhone = new JTextField();
    prepareConstraint(c, 1, 1, 1, 1, GridBagConstraints.WEST);
    panel1.add(txtPhone,c);
    c.fill = GridBagConstraints.NONE;
    //email label gridx=2 gridy=1
    JLabel lblEmail = new JLabel("Email");
    prepareConstraint(c, 1, 1, 2, 1, GridBagConstraints.EAST);
    panel1.add(lblEmail,c);
    //email text field gridx=3 gridy=1 gridwidth=2
    c.fill = GridBagConstraints.HORIZONTAL;
    JTextField txtEmail = new JTextField();
    prepareConstraint(c, 2, 1, 3, 1, GridBagConstraints.WEST);
    panel1.add(txtEmail,c);
    c.fill = GridBagConstraints.NONE;
    //next row address 1 label. gridx=0 gridy=2
    JLabel lblAddress1 = new JLabel("Address 1");
    prepareConstraint(c, 1, 1, 0, 2, GridBagConstraints.EAST);
    panel1.add(lblAddress1,c);
    // address1 text field. gridx=1 gridy=2 gridwidth=2
    JTextField txtAddress1 = new JTextField();
    prepareConstraint(c, 2, 1, 1, 2, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtAddress1,c);
    c.fill = GridBagConstraints.NONE;
    //next row address 2 label. gridx=0 gridy=3
    JLabel lblAddress2 = new JLabel("Address 2");
    prepareConstraint(c, 1, 1, 0, 3, GridBagConstraints.EAST);
    panel1.add(lblAddress2,c);
    // address1 text field. gridx=1 gridy=3 gridwidth=2
    JTextField txtAddress2 = new JTextField();
    prepareConstraint(c, 2, 1, 1, 3, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtAddress2,c);
    c.fill = GridBagConstraints.NONE;
    //next row city label. gridx=0 gridy=4
    JLabel lblCity = new JLabel("City");
    prepareConstraint(c, 1, 1, 0, 4, GridBagConstraints.EAST);
    panel1.add(lblCity,c);
    // city text field. gridx=1 gridy=4 gridwidth=2
    JTextField txtCity = new JTextField();
    prepareConstraint(c, 2, 1, 1, 4, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtCity,c);
    //next row state label. gridx=0 gridy=5
    c.fill = GridBagConstraints.NONE;
    JLabel lblState = new JLabel("State");
    prepareConstraint(c, 1, 1, 0, 5, GridBagConstraints.EAST);
    panel1.add(lblState,c);

    // city text field. gridx=1 gridy=5 gridwidth=2
    JTextField txtState = new JTextField();
    prepareConstraint(c, 2, 1, 1, 5, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtState,c);
    c.fill = GridBagConstraints.NONE;
//gridx = 3 widthx=1
    JLabel lblZip = new JLabel("Zip");
    prepareConstraint(c, 1, 1, 3, 5, GridBagConstraints.EAST);
    panel1.add(lblZip,c);

    // city text field. gridx=1 gridy=5 gridwidth=2
    JTextField txtZip = new JTextField();
    prepareConstraint(c, 1, 1, 4, 5, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtZip,c);
    c.fill = GridBagConstraints.NONE;
    //next row country label. gridx=0 gridy=6
    JLabel lblCountry = new JLabel("Country");
    prepareConstraint(c, 1, 1, 0, 6, GridBagConstraints.EAST);
    panel1.add(lblCountry,c);
    // address1 text field. gridx=1 gridy=6 gridwidth=2
    JTextField txtCountry = new JTextField();
    prepareConstraint(c, 2, 1, 1, 6, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtCountry,c);


    rightPanel.add(panel1);
    }
    private void prepareConstraint(GridBagConstraints c,int gridwidth,int gridheight,int gridx,int gridy,int anchor){
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = anchor;
    }
    
}
