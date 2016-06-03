/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import model.Contact;
import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ProgressDialog extends JDialog implements ActionListener{
    JTextArea txtArea = new JTextArea(5,10);
    JButton btnOk = new JButton(Utility.getString("progress.button.ok"));
    private JScrollPane jScrollPane;
    public ProgressDialog(JFrame owner){
        super(owner,Utility.getString("progressdialog.import.title"),true);
        this.setSize(380,300);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //this.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        //this.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        JPanel panel = new JPanel();
        jScrollPane = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportView(txtArea);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(jScrollPane);
        btnOk.addActionListener(this);
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnOk);
        this.setContentPane(panel);
    }
    public void contactAdded(Contact c){
        String[] params = new String[]{c.getLastName() + ", " + c.getFirstName()};
        txtArea.append(Utility.getString("import.contact.added",params ) + "\n");
    }
    public void importFinished(int num){
        //is called when import action finished
        //this.setModal(true);
        JOptionPane.showMessageDialog(this, Utility.getString("import.finished", new String[]{String.valueOf(num)}));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnOk))
        {
            this.dispose();
        }
    }
    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }

public void contactExported(Contact contact) {
    String[] params = new String[]{contact.getLastName() + ", " + contact.getFirstName()};      
    txtArea.append( Utility.getString("export.contact.saved",params) + "\n");
    }
public void exportFinished(int num){
        JOptionPane.showMessageDialog(this, Utility.getString("export.finished", new String[]{String.valueOf(num)}));
    
}
}
