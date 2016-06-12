/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Locale;
import model.ContactRepository;
import model.Settings;
import persistence.ContactsLoader;
import persistence.FileContactsHandler;
import utils.Utility;
import view.AddressBookFrame;

/**
 *
 *
 */
public class AddressBook implements WindowListener, Runnable {

    Locale initialLocale = Settings.LOCALE;
    boolean redraw = false;

    private void createWindow() {
        try {

            Settings.loadFromFile();
            ContactsLoader loader = new FileContactsHandler(Settings.DATA_FILE);
            Collection collection = loader.loadContacts();
            ContactRepository.getInstance().addAll(collection);
            AddressBookFrame frame = new AddressBookFrame();
            frame.addWindowListener(this);
            frame.setTitle(Utility.getString("addressbook.title"));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();//comments
            System.exit(0);
        }

    }

    public static void main(String[] args) throws Throwable {

        AddressBook addressBook = new AddressBook();
        do {
            addressBook.redraw = false;
            Thread th = new Thread(addressBook);
            th.start();// creates a thread which inseides runs GUI
            synchronized (addressBook) {//main thread continues
                try {
                    addressBook.wait();//main thread sleeps here until be waked up by closeWindow method
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (addressBook.redraw);//if this is a redraw request recreates GUI. otherwise terminates

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    
    @Override
    public void windowClosed(WindowEvent e) {
        if (Settings.LOCALE != initialLocale) {//just once the language changes it is required to restart application
            redraw = true;//shows that it needs to redraw not close
        }
        synchronized (this) {
            this.notifyAll();//wakes up the main thread (the main thread that started application)
        }
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
