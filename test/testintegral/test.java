/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testintegral;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Locale;
import model.ContactRepository;
import model.Settings;
import persistence.FileContactsHandler;
import utils.Utility;
import view.AddressBookFrame;

/**
 *
 *
 */
public class test implements WindowListener, Runnable {

    Locale initialLocale = Settings.LOCALE;
    boolean redraw = false;

    private void createWindow() {
        try {

            Settings.loadFromFile();
            FileContactsHandler loader = new FileContactsHandler();
            Collection collection = loader.loadContacts(Settings.DATA_FILE);
            ContactRepository.getInstance().addAll(collection);
            AddressBookFrame frame = new AddressBookFrame();
            frame.addWindowListener(this);
            frame.setTitle(Utility.getString("addressbook.title"));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();//comments
        }

    }

    public static void main(String[] args) throws Throwable {

        test tst = new test();
        Object obj = new Object();
        do {
            tst.redraw = false;
            Thread th = new Thread(tst);
            th.start();
            synchronized (tst) {
                try {
                    tst.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (tst.redraw);

    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    
    @Override
    public void windowClosed(WindowEvent e) {
        if (Settings.LOCALE != initialLocale) {
            redraw = true;
        }
        synchronized (this) {
            this.notifyAll();
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
