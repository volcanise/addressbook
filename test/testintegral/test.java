/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testintegral;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Properties;
import model.ContactsCollection;
import model.Settings;
import persistence.FileContactsHandler;
import view.AddressBookFrame;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class test implements WindowListener, Runnable{
    Locale initialLocale = Settings.LOCALE;
    boolean redraw = false;
    private void createWindow(){
                    try{
           FileInputStream fin = new FileInputStream(new File("settings.properties"));
           Properties props = new Properties();
           props.load(fin);
           String value = (String)props.get("DEFAULT_COUNTRY");
           if (value != null)
               if (value == "CA") 
                 Settings.DEFAULT_COUNTRY = Settings.CA;
               else if (value == "US") 
                 Settings.DEFAULT_COUNTRY = Settings.US;
                 else
                   Settings.DEFAULT_COUNTRY = Settings.OTHER;
           value = (String)props.get("DATA_FILE");
           if (value != null)
               Settings.DATA_FILE = new File(value);
           else 
               Settings.DATA_FILE = new File("database.dat");
           value = (String)props.get("TEL_REQUIRED");
           if (value != null)
               if (value.toLowerCase() == "true")
                   Settings.TEL_REQUIRED = true;
               else if (value.toLowerCase() == "false")
                   Settings.TEL_REQUIRED = false;
           else
                   Settings.TEL_REQUIRED = true;
           value = (String)props.get("ZIP_REQUIRED");
           if (value != null)
               if (value.toLowerCase() == "true")
                   Settings.ZIP_REQUIRED = true;
               else if (value.toLowerCase() == "false")
                   Settings.ZIP_REQUIRED = false;
           else
                   Settings.ZIP_REQUIRED = true;
           value = (String)props.get("VALIDATE_ZIP");
           if (value != null)
               if (value.toLowerCase() == "true")
                   Settings.VALIDATE_ZIP = true;
               else if (value.toLowerCase() == "false")
                   Settings.VALIDATE_ZIP = false;
           else
                   Settings.VALIDATE_ZIP = true;

           value = (String)props.get("DELIMITER");
           if (value != null)
               Settings.DELIMITER = value;
           ContactsCollection registry = new ContactsCollection();
           Settings.registry = registry;
           FileContactsHandler loader = new FileContactsHandler();
           Collection collection = loader.loadContacts();
           registry.addAll(collection);
           AddressBookFrame frame = new AddressBookFrame();
           frame.addWindowListener(this);
           frame.setContacts(registry);
           frame.setTitle("Listes des contacts");
           frame.setSize(790,330);
           frame.setResizable(false);
           frame.setVisible(true);
                       }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public static void main (String[] args) throws Throwable{
        
        test tst = new test();
        Object obj = new Object();
        do{
        tst.redraw = false;    
        Thread th = new Thread(tst);
        th.start();
        synchronized(tst){
        try{
            tst.wait();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        }
                }
        while (tst.redraw);
            
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    
    @Override
    public void windowClosed(WindowEvent e) {
        if (Settings.LOCALE != initialLocale)
            redraw = true;
        synchronized(this){
        this.notifyAll();}
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void run() {
    createWindow();
    }
}
