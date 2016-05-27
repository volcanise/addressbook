/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Contact;
import utils.Utility;

/**
 *
 * @author Mohamed
 */
public class GuiDeleteConfirm extends JFrame{
    Contact contact;
    private JLabel lblConfirm;
    private JButton btnYes;
    private JButton btnNo;
    public static boolean state;
    

    public GuiDeleteConfirm() {
        
        
        initComposants();
        initPanels();
        listeners();
        
    }
    
    public  void listeners(){
        
        btnYes.addActionListener(new ActionListener() {
             
            @Override
            
            public void actionPerformed(ActionEvent ae) {
                state = true;
                exit(0);
            }
        });
        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                state = false;
                exit(0);
                
             }
        });
        
    }
    // Init Components
    private void initComposants(){
        
       btnYes = new JButton(Utility.getString("addressbook.button.deleteconfirm"));
       btnYes = new JButton(Utility.getString("addressbook.button.deletedeny")); 
       lblConfirm = new JLabel(Utility.getString("addressbook.button.deletedeny")); 
        
    }
    
    private void initPanels(){
        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        this.setContentPane(mainPanel);

        // Text Panel
        JPanel layout1 = new JPanel(new FlowLayout());
        layout1.add(lblConfirm);
        mainPanel.add(layout1, BorderLayout.CENTER);
        // Buttons Panel
        JPanel button = new JPanel(new FlowLayout());
        button.add(btnYes);
        button.add(btnNo);
        mainPanel.add(button, BorderLayout.SOUTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
