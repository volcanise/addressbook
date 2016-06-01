/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import model.Contact;
import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ProgressFrame extends JDialog{
    JTextArea txtArea = new JTextArea();
    public ProgressFrame(){
        this.setSize(150,110);
        txtArea.setSize(50,70);
        this.add(txtArea);
    }
    public void contactAdded(Contact c){
        txtArea.append(c.getLastName() + ", " + c.getFirstName() + " " + Utility.getString("import.contact.added" + "\n"));
    }
    public void finished(){
        //is called when import action finished
        JOptionPane.showConfirmDialog(this, )
    }
}
