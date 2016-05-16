/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paradis;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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

    public AddressBookFrame() throws HeadlessException {
        run();
    }
    
    
    public void run(){
    initComponents();    
    }
    void initComponents(){
        txtName = new JTextField();
        txtTel = new JTextField();
        txtName.setColumns(15);
        txtTel.setColumns(15);
        
        scrPane = new JScrollPane();
        lstContacts = new JList<Contact>();
        scrPane.setViewportView(lstContacts);
        
        JPanel rightPanel = new JPanel();
        rightPanel.add(txtName);
        rightPanel.add(txtTel);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(scrPane,BorderLayout.CENTER);
        
        JPanel bottomLine = new JPanel(new GridLayout(1,5));
        bottomLine.add(new JButton("1"));
        bottomLine.add(new JButton("2"));
        bottomLine.add(new JButton("3"));
        bottomLine.add(new JButton("4"));
        bottomLine.add(new JButton("5"));
        
        //JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrPane, rightPanel);
        
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
    
}
