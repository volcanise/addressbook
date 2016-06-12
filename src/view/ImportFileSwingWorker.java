/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import model.Contact;
import model.ContactRepository;
import persistence.FileContactsHandler;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ImportFileSwingWorker extends SwingWorker {

    private ProgressDialog progress;
    private File dataFile;
    private JFrame parent;
    public ImportFileSwingWorker(File dataFile, AddressBookFrame addFrame, ProgressDialog progress){
        this.progress = progress;
        this.dataFile = dataFile;
        this.parent = addFrame;
    }
    @Override
    protected Object doInBackground() throws Exception {
        FileContactsHandler fHandler = new FileContactsHandler();
        int counter = 0;
        try{
        Collection<Contact> c = fHandler.loadContacts(dataFile);
        Iterator<Contact> itr = c.iterator();
        while(itr.hasNext()){
                Contact contact = itr.next();
                counter++;
                ContactRepository.getInstance().addContact(contact);
                progress.contactAdded(contact);
            }
        progress.importFinished(counter);
        }catch(Exception e){
            progress.showError(e);
        }
        return null;

    }

    @Override
    protected void process(List chunks) {
        
    }
    
    
}
