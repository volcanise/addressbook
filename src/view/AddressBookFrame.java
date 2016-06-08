/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Contact;
import model.ContactRepository;
import model.InvalidFieldException;
import model.Language;
import model.Settings;
import static model.Settings.LOCALE;
import persistence.FileContactsHandler;
import utils.Utility;

/**
 *
 * 
 */
public class AddressBookFrame extends JFrame implements ActionListener, ListSelectionListener {

    JList lstContacts;
    JScrollPane scrPane;
    JTextField txtName;

    JTextField txtCountry;

    DefaultListModel defModel;
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtMiddleName;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtAddress1;
    private JTextField txtAddress2;
    private JTextField txtCity;
    private JTextField txtZip;
    private JTextField txtState;
    private JComboBox comboLanguage;
    // All Button set
    private JButton btnApply;// In the Settings screen
    private JButton btnNew;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnSave;
    private JButton btnSearch;
    private JButton btnCancel;
    private Contact contactForEdit;
    private JButton btnFileSelect;
    private JTextField txtFileSelect;
    private JLabel lblDataFile;
    private JButton btnCancelSettings;
    private JCheckBox chkEmail;
    private JCheckBox chkZip;
    private JCheckBox chkTel;
    private JCheckBox chkZipValidation;
    private JCheckBox chkEmailValidation;
    private JTextField txtFileImport;
    private JRadioButton rdiImport;
    private JRadioButton rdiExport;
    private JLabel lblFileImport;
    private JButton btnFileImport;
    private JButton btnFileImportProceed;

    public AddressBookFrame() throws HeadlessException {
        run();
    }

    public void loadContacts() {
        Iterator<Contact> itr = ContactRepository.getInstance().getContactsList();
        while (itr.hasNext()) {
            ((DefaultListModel) lstContacts.getModel()).addElement(itr.next());//Returns:the ListModel that provides the displayed list of items
        }
    }

    public void run() {
        initComponents();
    }

    private void initComponents() {
        // Create the ListModel
        defModel = new DefaultListModel();
        //Create the list and put it in a scroll pane.
        lstContacts = new JList();
        //Enable just one selection
        lstContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstContacts.setSelectedIndex(0);
        lstContacts.setModel(defModel);
        lstContacts.addListSelectionListener(this);// Add the ListSelectioListener to the list
        scrPane = new JScrollPane();
        scrPane.setPreferredSize(new Dimension(170, 50));
        scrPane.setViewportView(lstContacts);
        // Differents Right components to be set in this right panel
        JPanel rightPanel = new JPanel();
        prepareRightPanel(rightPanel);
        // The Left Panel contains the scroll panel
        JPanel leftPanel = new JPanel();
//        leftPanel.setLayout(new BorderLayout());
//        leftPanel.add(scrPane,BorderLayout.CENTER);
        leftPanel.setLayout(new FlowLayout());// Is better to put it as a FlowLayout, not to mix with the main BorLay
        leftPanel.add(scrPane);
        // Buttons settings
        JPanel bottomLine = new JPanel(new GridLayout(1, 5));
        btnNew = new JButton(Utility.getString("addressbook.button.new"));//make label customized
        btnNew.addActionListener(this);
        bottomLine.add(btnNew);
        
        btnDelete = new JButton(Utility.getString("addressbook.button.delete"));
        btnDelete.addActionListener(this);
        bottomLine.add(btnDelete);
        btnEdit = new JButton(Utility.getString("addressbook.button.edit"));//make label customized
        btnEdit.addActionListener(this);
        bottomLine.add(btnEdit);
        btnSave = new JButton(Utility.getString("addressbook.button.save"));
        btnSave.addActionListener(this);
        bottomLine.add(btnSave);
        btnSearch = new JButton(Utility.getString("addressbook.button.find"));
        btnSearch.addActionListener(this);
        bottomLine.add(btnSearch);
        btnCancel = new JButton(Utility.getString("addressbook.button.cancel"));
        btnCancel.addActionListener(this);
        bottomLine.add(btnCancel);

        //middle panel contains right panel and list
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.add(scrPane, BorderLayout.WEST);
        middlePanel.add(rightPanel, BorderLayout.CENTER);
        //Main panel contains middle panel and bottomline
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(middlePanel, BorderLayout.NORTH);
        mainPanel.add(new JSeparator(), BorderLayout.CENTER);// Adding a Jsperator in the Main Panel
        mainPanel.add(bottomLine, BorderLayout.SOUTH);
        // The Settings and Import/Export GUI
        JPanel settingsPanel = prepareSettingsPanel();
        //JPanel importPanel = prepareImportPanel();
        // Settings of the JTabbedPane
        JPanel importPanel = prepareImportPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(Utility.getString("firsttab.title"), mainPanel);// must be localized
        tabbedPane.add(Utility.getString("secondtab.title"), settingsPanel);
        tabbedPane.add(Utility.getString("thirdtab.title"),importPanel);
        this.setContentPane(tabbedPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        loadContacts();//it supposes that ContactRepository already loaded by file content
    }
    // Prepare the right panel which includes the GridBagConstraints settings 
    private void prepareRightPanel(JPanel rightPanel) {
        JPanel panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        //margin set to 
        c.insets = new Insets(7, 7, 7, 7);
        prepareConstraint(c, 1, 1, 0, 0, GridBagConstraints.EAST);
        panel1.add(new JLabel(Utility.getString("addressbook.label.lastName")), c);
        // last name textfield
        txtLastName = new JTextField(12);
        
        //prepareConstraint(c,gridwidth,gridheight,gridx,gridy,anchor);
        prepareConstraint(c, 1, 1, 1, 0, GridBagConstraints.WEST);
        panel1.add(txtLastName, c);
        //label for first name same row (gridy=0), next column(gridx=2)
        JLabel lblFirstName = new JLabel(Utility.getString("addressbook.label.firstName"));
        prepareConstraint(c, 1, 1, 2, 0, GridBagConstraints.EAST);
        panel1.add(lblFirstName, c);
        //first name text field
        txtFirstName = new JTextField(7);
        // same row (gridy=0) next column (gridx=3)
        prepareConstraint(c, 1, 1, 3, 0, GridBagConstraints.WEST);
        panel1.add(txtFirstName, c);
        //label for middle name
        JLabel lblMiddleName = new JLabel(Utility.getString("addressbook.label.middleName"));
        //same row gridy=0 next column gridx=4
        prepareConstraint(c, 1, 1, 4, 0, GridBagConstraints.EAST);
        panel1.add(lblMiddleName, c);
        //text field for middle name
        txtMiddleName = new JTextField(7);
        
        //same row gridy=0 and next column gridx=5
        prepareConstraint(c, 1, 1, 5, 0, GridBagConstraints.WEST);
        panel1.add(txtMiddleName, c);

        //second row gridx=0 gridy=1
        JLabel lblPhone = new JLabel(Utility.getString("addressbook.label.phone"));
        prepareConstraint(c, 1, 1, 0, 1, GridBagConstraints.EAST);
        panel1.add(lblPhone, c);
        //text field gridx=1 gridy=1 fill horozon
        c.fill = GridBagConstraints.HORIZONTAL;
        txtPhone = new JTextField();
        prepareConstraint(c, 1, 1, 1, 1, GridBagConstraints.WEST);
        panel1.add(txtPhone, c);
        c.fill = GridBagConstraints.NONE;
        //email label gridx=2 gridy=1
        JLabel lblEmail = new JLabel(Utility.getString("addressbook.label.email"));
        prepareConstraint(c, 1, 1, 2, 1, GridBagConstraints.EAST);
        panel1.add(lblEmail, c);
        //email text field gridx=3 gridy=1 gridwidth=2
        c.fill = GridBagConstraints.HORIZONTAL;
        txtEmail = new JTextField();
        prepareConstraint(c, 2, 1, 3, 1, GridBagConstraints.WEST);
        panel1.add(txtEmail, c);
        c.fill = GridBagConstraints.NONE;
        //next row address 1 label. gridx=0 gridy=2
        JLabel lblAddress1 = new JLabel(Utility.getString("addressbook.label.addresse1"));
        prepareConstraint(c, 1, 1, 0, 2, GridBagConstraints.EAST);
        panel1.add(lblAddress1, c);
        // address1 text field. gridx=1 gridy=2 gridwidth=2
        txtAddress1 = new JTextField();
        prepareConstraint(c, 2, 1, 1, 2, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtAddress1, c);
        c.fill = GridBagConstraints.NONE;
        //next row address 2 label. gridx=0 gridy=3
        JLabel lblAddress2 = new JLabel(Utility.getString("addressbook.label.addresse2"));
        prepareConstraint(c, 1, 1, 0, 3, GridBagConstraints.EAST);
        panel1.add(lblAddress2, c);
        // address1 text field. gridx=1 gridy=3 gridwidth=2
        txtAddress2 = new JTextField();
        prepareConstraint(c, 2, 1, 1, 3, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtAddress2, c);
        c.fill = GridBagConstraints.NONE;
        //next row city label. gridx=0 gridy=4
        JLabel lblCity = new JLabel(Utility.getString("addressbook.label.city"));
        prepareConstraint(c, 1, 1, 0, 4, GridBagConstraints.EAST);
        panel1.add(lblCity, c);
        // city text field. gridx=1 gridy=4 gridwidth=2
        txtCity = new JTextField();
        prepareConstraint(c, 2, 1, 1, 4, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtCity, c);
        //next row state label. gridx=0 gridy=5
        c.fill = GridBagConstraints.NONE;
        JLabel lblState = new JLabel(Utility.getString("addressbook.label.state"));
        prepareConstraint(c, 1, 1, 0, 5, GridBagConstraints.EAST);
        panel1.add(lblState, c);

        // city text field. gridx=1 gridy=5 gridwidth=2
        txtState = new JTextField();
        prepareConstraint(c, 2, 1, 1, 5, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtState, c);
        c.fill = GridBagConstraints.NONE;
        //gridx = 3 widthx=1
        JLabel lblZip = new JLabel(Utility.getString("addressbook.label.zip"));
        prepareConstraint(c, 1, 1, 3, 5, GridBagConstraints.EAST);
        panel1.add(lblZip, c);

        // city text field. gridx=1 gridy=5 gridwidth=2
        txtZip = new JTextField();
        prepareConstraint(c, 1, 1, 4, 5, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtZip, c);
        c.fill = GridBagConstraints.NONE;
        //next row country label. gridx=0 gridy=6
        JLabel lblCountry = new JLabel(Utility.getString("addressbook.label.country"));
        prepareConstraint(c, 1, 1, 0, 6, GridBagConstraints.EAST);
        panel1.add(lblCountry, c);
        // address1 text field. gridx=1 gridy=6 gridwidth=2
        txtCountry = new JTextField();
        prepareConstraint(c, 2, 1, 1, 6, GridBagConstraints.WEST);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(txtCountry, c);
        setTextFieldsEditability(false);
        rightPanel.add(panel1);
    }
    private void prepareConstraint(GridBagConstraints c, int gridwidth, int gridheight, int gridx, int gridy, int anchor) {
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
        if (obj.equals(btnDelete)) {
            handleDeleteCommand();
        } //else return; do nothing
        //end of delete
        //start of save button handling
        else if (obj.equals(btnSave)) {
            handleSaveCommand();
        } //end of save command handling
        // start of handling apply setting
        else if (obj.equals(btnApply)) {
            handleApplyCommand();
        }
        
        else if (obj.equals(btnEdit)) {
            handleEditCommand();
        }
        else if (obj.equals(btnCancel)) {
            handleCancelCommand();
        }
        else if (obj.equals(btnNew)) {
            handleNewCommand();
        }
        else if (obj.equals(btnFileSelect)) {
            handleFileSelectCommand();
        }
        else if (obj.equals(btnCancelSettings)) {
            handleCancelSettingsCommand();
        }
        else if (obj.equals(btnFileImport)) {
            handleFileImportSelectCommand();
        }
        else if (obj.equals(btnFileImportProceed)) {
            handleFileImportProceedCommand();
        }
 
    }

    @Override
    // is called when list selection changes and the new values must be shown in text fields
    public void valueChanged(ListSelectionEvent e) {
        Contact ct = (Contact) lstContacts.getSelectedValue();
        if (ct==null) {
            resetTextFields();
            return;
        }
        if (txtLastName.isEditable())
            setTextFieldsEditability(false);
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
    private void resetTextFields() {
        txtCountry.setText("");
        txtCountry.setText("");
        txtLastName.setText("");
        txtFirstName.setText("");
        txtMiddleName.setText("");
        txtPhone.setText("");
        txtAddress1.setText("");
        txtAddress2.setText("");
        txtCity.setText("");
        txtState.setText("");
        txtEmail.setText("");
        txtZip.setText("");
    }
    // Dialog confirmation whether to proceed with contact delete 
    private boolean getConfirmation(String message,String title) {
        UIManager.put("OptionPane.yesButtonText", Utility.getString("deletedialogue.yesbutton"));
        UIManager.put("OptionPane.noButtonText", Utility.getString("deletedialogue.nobutton"));
        UIManager.put("OptionPane.cancelButtonText", Utility.getString("deletedialogue.cancelbutton"));
        UIManager.put("OptionPane.titleText", Utility.getString("deletedialogue.title"));

        int dialogResult = JOptionPane.showConfirmDialog(this, message,title,JOptionPane.YES_NO_OPTION );
        if (dialogResult == 0) {
            return true;
        }
        return false;

    }

    private void setTextFieldsEditability(boolean b) {
        txtLastName.setEditable(b);
        txtFirstName.setEditable(b);
        txtMiddleName.setEditable(b);
        txtPhone.setEditable(b);
        txtEmail.setEditable(b);
        txtAddress1.setEditable(b);
        txtAddress2.setEditable(b);
        txtCity.setEditable(b);
        txtState.setEditable(b);
        txtZip.setEditable(b);
        txtCountry.setEditable(b);

    }

    private void handleDeleteCommand() {
        Object contact = lstContacts.getSelectedValue();
        if ((contact !=null) && (getConfirmation(Utility.getString("deletedialogue.message"),Utility.getString("deletedialogue.title")))) {
                defModel.removeElement(contact);//delete from JList
                ContactRepository.getInstance().deleteContact((Contact) contact);//delete from repository
        }

    }

    private void handleEditCommand() {
            contactForEdit = (Contact)lstContacts.getSelectedValue();
            if (contactForEdit != null){
            setTextFieldsEditability(true);//makes fields editable and lets it until next save or cancel operations
            }
    }
    private void handleApplyCommand() {//apply settings
            Language lang = (Language) comboLanguage.getSelectedItem();
            Locale locale = new Locale(lang.getCode());
            Settings.LOCALE = locale;
            Settings.resources = ResourceBundle.getBundle("resources", LOCALE);
            Settings.EMAIL_REQUIRED = chkEmail.isSelected();
            Settings.VALIDATE_EMAIL = chkEmailValidation.isSelected();
            Settings.ZIP_REQUIRED = chkZip.isSelected();
            Settings.VALIDATE_ZIP = chkZipValidation.isSelected();
            Settings.TEL_REQUIRED = chkTel.isSelected();
            Settings.saveToFile();
            this.setVisible(false);
            this.dispose();
    }

    private void handleSaveCommand() {
            if (txtLastName.isEditable())//edit shows we are in the process of editing
            {
                boolean success = applyChanges(); // applies changes 
                if (success)
                    setTextFieldsEditability(false);
                else
                    return;
            }
            FileContactsHandler fhandler = new FileContactsHandler();
            try {
                fhandler.save(ContactRepository.getInstance().getContactsList(),Settings.DATA_FILE);
                defModel.removeAllElements();// removes all elements of jlist
                loadContacts();
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

    }

    private void handleCancelCommand() {
        setTextFieldsEditability(false);
        lstContacts.clearSelection();
        contactForEdit = null;//this is the contact to be edited. if the user canceled his edit decision this will be set to null
        // the null value show an edit is not under treatment
    }

    private boolean applyChanges() {//boolean edit shows whether we want to edit a contact or create a new one 
        boolean edit = (contactForEdit == null)?false:true;
        Contact cont = new Contact();
        cont.setAddress1(txtAddress1.getText().trim());
        cont.setAddress2(txtAddress2.getText().trim());
        cont.setCity(txtCity.getText().trim());
        cont.setCountry(txtCountry.getText().trim());
        try{
        cont.setEmail(txtEmail.getText().trim());
        }catch(InvalidFieldException e){
            showError(e);
            return false;
        }
        cont.setFirstName(txtFirstName.getText().trim());
        try{
        cont.setLastName(txtLastName.getText().trim());
        }catch(InvalidFieldException e){
        if (!edit)//just when in the process of create new contact the last name must be unique
        {
            showError(e);
            return false;
        }
        }
        cont.setMiddleName(txtMiddleName.getText().trim());
        cont.setState(txtState.getText().trim());
        cont.setTel(txtPhone.getText().trim());
        try{
        cont.setZip(txtZip.getText().trim());
        }catch(InvalidFieldException e){
            showError(e);
            return false;
        }
        if (!cont.equals(contactForEdit))
        {
            if (edit)//just in edit handling it requires to delete previous
            ContactRepository.getInstance().deleteContact(contactForEdit);
            ContactRepository.getInstance().addContact(cont);
        }
        return true;
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    private void showError(String s) {
        JOptionPane.showMessageDialog(this,s);
    }

    private void handleNewCommand() {
        lstContacts.clearSelection();
        setTextFieldsEditability(true);
    }
   // The setting panel in the JTabedPane
    private JPanel prepareSettingsPanel() {
        JPanel settingsPanel = new JPanel();  
        BoxLayout bl = new BoxLayout(settingsPanel, BoxLayout.Y_AXIS);
        settingsPanel.setLayout(bl);
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel(Utility.getString("settingspanel.label.email.required")));
        chkEmail = new JCheckBox();
        topPanel.add(chkEmail);
        topPanel.add(new JLabel(Utility.getString("settingspanel.label.zip.required")));
        chkZip = new JCheckBox();
        topPanel.add(chkZip);
        topPanel.add(new JLabel(Utility.getString("settingspanel.label.tel.required")));
        chkTel = new JCheckBox();
        topPanel.add(chkTel);
        settingsPanel.add(topPanel);
        
        JPanel meanPanel = new JPanel();
        chkZipValidation = new JCheckBox();
        meanPanel.add(new JLabel(Utility.getString("settingspanel.label.zip.validation")));
        meanPanel.add(chkZipValidation);
        chkEmailValidation = new JCheckBox();
        meanPanel.add(new JLabel(Utility.getString("settingspanel.label.email.validation")));
        meanPanel.add(chkEmailValidation);
        settingsPanel.add(meanPanel);
        
        JPanel dataFilePanel = new JPanel();
        lblDataFile = new JLabel(Utility.getString("settingspanel.label.fileselect"));
        dataFilePanel.add(lblDataFile);
        btnFileSelect = new JButton(Utility.getString("settingspanel.button.fileselect"));
        btnFileSelect.addActionListener(this);
        txtFileSelect = new JTextField();
        txtFileSelect.setColumns(8);
        txtFileSelect.setText(Settings.DATA_FILE.getName());
        dataFilePanel.add(txtFileSelect);
        dataFilePanel.add(btnFileSelect);
        settingsPanel.add(dataFilePanel);
        JPanel internalPanel = new JPanel();
        internalPanel.add(new JLabel(Utility.getString("settingspanel.label.language")));
        Vector items = new Vector();// equivalent to an ArrayList
        for (String lang : Settings.SUPPURTED_LANGS) {
            Locale locale = new Locale(lang);
            Language item = new Language(lang, locale.getDisplayLanguage(locale));
            items.add(item);
        }
        // Settings of the JComboBox
        comboLanguage = new JComboBox(items);
        btnApply = new JButton(Utility.getString("settingspanel.button.apply"));
        btnApply.addActionListener(this);
        btnCancelSettings = new JButton(Utility.getString("settingspanel.button.cancel"));
        btnCancelSettings.addActionListener(this);
        internalPanel.add(comboLanguage);
        internalPanel.add(btnApply);
        internalPanel.add(btnCancelSettings);
        settingsPanel.add(internalPanel);
        reflectSettings();//reflect settings on components
        return settingsPanel;
    }
    // The ImportPanel panel in the JTabbedPane
    private JPanel prepareImportPanel() {
    JPanel panel = new JPanel();
    ButtonGroup bg = new ButtonGroup();
    rdiImport = new JRadioButton(Utility.getString("importpanel.radiobutton.import"));
    rdiExport = new JRadioButton(Utility.getString("importpanel.radiobutton.export"));
    rdiExport.setSelected(true);
    bg.add(rdiExport);
    bg.add(rdiImport);
    txtFileImport = new JTextField(15);
    lblFileImport = new JLabel(Utility.getString("importpanel.label.fileimport"));
    btnFileImport = new JButton(Utility.getString("importpanel.button.fileimport"));
    btnFileImport.addActionListener(this);
    btnFileImportProceed = new JButton(Utility.getString("importpanel.button.proceed"));
    btnFileImportProceed.addActionListener(this);
    GridBagLayout gbag = new GridBagLayout();
    panel.setLayout(gbag);
    GridBagConstraints cons = new GridBagConstraints();
    //margin set to 
    cons.fill = GridBagConstraints.NONE;
    cons.insets = new Insets(7, 7, 7, 7);
    prepareConstraint(cons, 1, 1, 0, 1, GridBagConstraints.WEST);
    panel.add(rdiExport, cons);
    prepareConstraint(cons, 1, 1, 0, 2, GridBagConstraints.WEST);
    panel.add(rdiImport, cons);
    prepareConstraint(cons, 1, 1, 0, 3, GridBagConstraints.WEST);
    panel.add(lblFileImport,cons);
    prepareConstraint(cons, 1, 1, 1, 3, GridBagConstraints.WEST);
    panel.add(txtFileImport, cons);
    prepareConstraint(cons, 1, 1, 2, 3, GridBagConstraints.EAST);
    panel.add(btnFileImport, cons);
    prepareConstraint(cons, 1, 1, 3, 3, GridBagConstraints.EAST);
    panel.add(btnFileImportProceed, cons);
     
    return panel;
    }

    private void handleFileSelectCommand() {
        JFileChooser chooser = new JFileChooser(Settings.DATA_FILE.getPath());
        chooser.setVisible(true);
        File dataFile = null;
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           dataFile = chooser.getSelectedFile();
        if  (dataFile.exists())
        {
            if(
            getConfirmation (Utility.getString("settingspanel.file.override",new String[]{dataFile.getName()}),Utility.getString("file.override.warning.title"))
            )
            {
                Settings.DATA_FILE = dataFile;
                txtFileSelect.setText(Settings.DATA_FILE.getName());
            }
        }
        else//does not exist
        if (getConfirmation(Utility.getString("settingspanel.file.create",new String[]{dataFile.getName()}),Utility.getString("file.create.warning.title")))
            {
                try{
                dataFile.createNewFile();
                Settings.DATA_FILE = dataFile;
                txtFileSelect.setText(Settings.DATA_FILE.getName());
                }catch(IOException e){
                    e.printStackTrace();//todo must be changed
            }
        }
        
    }

}

    private void handleCancelSettingsCommand() {
       reflectSettings();//reflects Settings on check box values
    }

    private void reflectSettings() {
       chkEmail.setSelected(Settings.EMAIL_REQUIRED);
       chkEmailValidation.setSelected(Settings.VALIDATE_EMAIL);
       chkTel.setSelected(Settings.TEL_REQUIRED);
       chkZip.setSelected(Settings.ZIP_REQUIRED);
       chkZipValidation.setSelected(Settings.VALIDATE_ZIP);
       txtFileSelect.setText(Settings.DATA_FILE.getName());
       Language lang = new Language(Settings.LOCALE.getLanguage(),"");
       comboLanguage.setSelectedItem(lang);
    }

    private void handleFileImportSelectCommand() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setVisible(true);
        File dataFile = null;
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            {
            dataFile = chooser.getSelectedFile();
            txtFileImport.setText(dataFile.getName());
            }
    }
    }
    private void exportToFile(File dataFile) {
        ProgressDialog progress = new ProgressDialog(this);
        ExportFileSwingWorker worker = new ExportFileSwingWorker(dataFile, this,progress);
        progress.setLocationRelativeTo(this);
        worker.execute();
        progress.setVisible(true);
    }

    private void importFromFile(File dataFile) {
        ProgressDialog progress = new ProgressDialog(this);
        ImportFileSwingWorker worker = new ImportFileSwingWorker(dataFile, this,progress);
        progress.setLocationRelativeTo(this);
        worker.execute();
        progress.setVisible(true);

        
    }

    private void handleFileImportProceedCommand() {
        File dataFile = new File(txtFileImport.getText().trim());  
        if  (dataFile.exists()){ 
          if (rdiExport.isSelected())//file exists and we want to write on it
            {
            if(
            getConfirmation (Utility.getString("importpanel.file.override",new String[]{dataFile.getName()}),Utility.getString("file.override.warning.title"))
            )
                exportToFile(dataFile);
            else//user dont accept
                ;
          }
            else if (rdiImport.isSelected())//file exist for import
                importFromFile(dataFile);
        }else 
        {//file does not exist
            if (rdiExport.isSelected()){
            if(
            getConfirmation (Utility.getString("importpanel.file.create",new String[]{dataFile.getName()}),Utility.getString("file.create.warning.title"))
            )
            try{    
            dataFile.createNewFile();
            
            }catch(Exception e){
                showError(e);
            }
            }
            else
                showError(Utility.getString("importpanel.file.inexistant",new String[]{dataFile.getName()}));
        }
        }
}