/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Contact;
import model.ContactsCollection;
import model.Settings;
import persistence.FileContactsHandler;
import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class AddressBookFrame extends JFrame implements ActionListener, ListSelectionListener{
    JList lstContacts;
    JScrollPane scrPane;
    JTextField txtName;
    
    JTextField txtCountry;
    JButton btnDelete;
    DefaultListModel defModel;
    private JTextField txtLastName;
    ContactsCollection contacts;
    private JTextField txtFirstName;
    private JTextField txtMiddleName;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress1;
    private JTextField txtAddress2;
    private JTextField txtCity;
    private JTextField txtZip;
    private JTextField txtState;
    private JButton btnSave;
    public AddressBookFrame() throws HeadlessException {
        run();
    }

    public void setContacts(ContactsCollection contacts) {
        this.contacts = contacts;
        Iterator<Contact> itr = contacts.getContactsList();
        while(itr.hasNext())
        ((DefaultListModel)lstContacts.getModel()).addElement(itr.next());
    }
    
    
    public void run(){
    initComponents();    
    }
    void initComponents(){
        
        scrPane = new JScrollPane();
        scrPane.setPreferredSize(new Dimension(170, 50));
        lstContacts = new JList();//todo to be changed to make enable just one selection
        scrPane.setViewportView(lstContacts);
        defModel = new DefaultListModel();
        lstContacts.setModel(defModel);
        lstContacts.addListSelectionListener(this);
        JPanel rightPanel = new JPanel();
        prepareRightPanel(rightPanel);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(scrPane,BorderLayout.CENTER);
        
        JPanel bottomLine = new JPanel(new GridLayout(1,5));
        btnDelete = new JButton(Utility.getString("addressbook.button.delete"));
        btnDelete.addActionListener(this);
        bottomLine.add(btnDelete);//todo label to be customized
        // todo all the following buttons must be declared as the class member as like as Delete button
        // and their actionlistener must be set to this (AddressBookFrame) and the actionPerformed methode of AddressBookFrame
        // must be changed to handle other buttons
        btnSave = new JButton("Save");//todo make label customized
        btnSave.addActionListener(this);
        bottomLine.add(btnSave);
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
    txtFirstName = new JTextField(7);//todo must be declared as class member
    // same row (gridy=0) next column (gridx=3)
    prepareConstraint(c, 1, 1, 3, 0, GridBagConstraints.WEST);
    panel1.add(txtFirstName,c);
    //label for middle name
    JLabel lblMiddleName = new JLabel("Middle Name");
    //same row gridy=0 next column gridx=4
    prepareConstraint(c, 1, 1, 4, 0, GridBagConstraints.EAST);
    panel1.add(lblMiddleName,c);
    //text field for middle name
    txtMiddleName = new JTextField(7);
    //same row gridy=0 and next column gridx=5
    prepareConstraint(c, 1, 1, 5, 0, GridBagConstraints.WEST);
    panel1.add(txtMiddleName,c);
    
    //second row gridx=0 gridy=1
    JLabel lblPhone = new JLabel("Phone");
    prepareConstraint(c, 1, 1, 0, 1, GridBagConstraints.EAST);
    panel1.add(lblPhone,c);
    //text field gridx=1 gridy=1 fill horozon
    c.fill = GridBagConstraints.HORIZONTAL;
    txtPhone = new JTextField();
    prepareConstraint(c, 1, 1, 1, 1, GridBagConstraints.WEST);
    panel1.add(txtPhone,c);
    c.fill = GridBagConstraints.NONE;
    //email label gridx=2 gridy=1
    JLabel lblEmail = new JLabel("Email");
    prepareConstraint(c, 1, 1, 2, 1, GridBagConstraints.EAST);
    panel1.add(lblEmail,c);
    //email text field gridx=3 gridy=1 gridwidth=2
    c.fill = GridBagConstraints.HORIZONTAL;
    txtEmail = new JTextField();
    prepareConstraint(c, 2, 1, 3, 1, GridBagConstraints.WEST);
    panel1.add(txtEmail,c);
    c.fill = GridBagConstraints.NONE;
    //next row address 1 label. gridx=0 gridy=2
    JLabel lblAddress1 = new JLabel("Address 1");
    prepareConstraint(c, 1, 1, 0, 2, GridBagConstraints.EAST);
    panel1.add(lblAddress1,c);
    // address1 text field. gridx=1 gridy=2 gridwidth=2
    txtAddress1 = new JTextField();
    prepareConstraint(c, 2, 1, 1, 2, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtAddress1,c);
    c.fill = GridBagConstraints.NONE;
    //next row address 2 label. gridx=0 gridy=3
    JLabel lblAddress2 = new JLabel("Address 2");
    prepareConstraint(c, 1, 1, 0, 3, GridBagConstraints.EAST);
    panel1.add(lblAddress2,c);
    // address1 text field. gridx=1 gridy=3 gridwidth=2
    txtAddress2 = new JTextField();
    prepareConstraint(c, 2, 1, 1, 3, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtAddress2,c);
    c.fill = GridBagConstraints.NONE;
    //next row city label. gridx=0 gridy=4
    JLabel lblCity = new JLabel("City");
    prepareConstraint(c, 1, 1, 0, 4, GridBagConstraints.EAST);
    panel1.add(lblCity,c);
    // city text field. gridx=1 gridy=4 gridwidth=2
    txtCity = new JTextField();
    prepareConstraint(c, 2, 1, 1, 4, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtCity,c);
    //next row state label. gridx=0 gridy=5
    c.fill = GridBagConstraints.NONE;
    JLabel lblState = new JLabel("State");
    prepareConstraint(c, 1, 1, 0, 5, GridBagConstraints.EAST);
    panel1.add(lblState,c);

    // city text field. gridx=1 gridy=5 gridwidth=2
    txtState = new JTextField();
    prepareConstraint(c, 2, 1, 1, 5, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtState,c);
    c.fill = GridBagConstraints.NONE;
//gridx = 3 widthx=1
    JLabel lblZip = new JLabel("Zip");
    prepareConstraint(c, 1, 1, 3, 5, GridBagConstraints.EAST);
    panel1.add(lblZip,c);

    // city text field. gridx=1 gridy=5 gridwidth=2
    txtZip = new JTextField();
    prepareConstraint(c, 1, 1, 4, 5, GridBagConstraints.WEST);
    c.fill = GridBagConstraints.HORIZONTAL;
    panel1.add(txtZip,c);
    c.fill = GridBagConstraints.NONE;
    //next row country label. gridx=0 gridy=6
    JLabel lblCountry = new JLabel("Country");
    prepareConstraint(c, 1, 1, 0, 6, GridBagConstraints.EAST);
    panel1.add(lblCountry,c);
    // address1 text field. gridx=1 gridy=6 gridwidth=2
    txtCountry = new JTextField();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        //delete
        if (obj.equals(btnDelete))
        {
            //todo by mohamed: Before proceeding to delete must obtain user's confirmation
            // needs to create a dialog
            // create a method named getConfirmation that returns boolean
            // if returned value is true proceed to delete otherwise do nothing
   // if (getConfirmation()){         
            Object contact = lstContacts.getSelectedValue();
            if (contact != null)
            {
                defModel.removeElement(contact);//delete from JList
                contacts.deleteContact((Contact)contact);//delete from repository
            }
        }
        //}
        //else return; do nothing
        //end of delete
        //save
        if (obj.equals(btnSave))
        {
            FileContactsHandler fhandler = new FileContactsHandler();
            try{
            fhandler.save(Settings.registry.getContactsList());
            }catch(Exception ex){
                ex.printStackTrace(System.out);
            }
        }
        //end of save command handling

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Contact ct = (Contact)lstContacts.getSelectedValue();
        if (ct == null)
        {
        resetTextFields();
        return;
        }
        txtCountry.setText(ct.getCountry());
        txtLastName.setText(ct.getLastName());
        txtFirstName.setText(ct.getFirstName());
        txtMiddleName.setText(ct.getMiddleName());
        txtPhone.setText(ct.getTel());
        txtAddress1.setText(ct.getAddress1());
        txtAddress2.setText(ct.getAddress2());
        txtCity.setText(ct.getCity());
        txtState.setText(ct.getState());
        txtEmail.setText(ct.getEmail());
        txtZip.setText(ct.getZip());
    }
    //this method make the text fields empty. to be called when no contact is selected in jlist 
    private void resetTextFields(){
        txtCountry.setText("");//todo by mohamed other text fields must be set to ""
    }
    /*
    private boolean getConfirmation(){
    // show a modal dialog to get confirmation
    }
    */
}
