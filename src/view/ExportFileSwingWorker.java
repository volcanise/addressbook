/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import model.Contact;
import model.ContactRepository;
import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ExportFileSwingWorker extends SwingWorker {

    private ProgressDialog progress;
    private File dataFile;
    private JFrame parent;
    public ExportFileSwingWorker(File dataFile, AddressBookFrame addFrame, ProgressDialog progress){
        this.progress = progress;
        this.dataFile = dataFile;
        this.parent = addFrame;
    }
    @Override
    protected Object doInBackground() throws Exception {
        int counter = 0;
        try{
        PrintWriter pw = new PrintWriter(new FileOutputStream(dataFile));
        Iterator<Contact> itr = ContactRepository.getInstance().getContactsList();
        while (itr.hasNext()){
                counter++;
                Contact contact = itr.next();
                pw.println(Utility.prepareString(contact));
                progress.contactExported(contact);
            }
        pw.flush();
        pw.close();
        progress.exportFinished(counter);
        }catch(Exception e){
            progress.showError(e);
        }
        return null;

    }

    @Override
    protected void process(List chunks) {
        
    }
    
    
}
