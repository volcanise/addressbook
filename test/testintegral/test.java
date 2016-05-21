/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testintegral;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;
import model.Contact;
import model.ContactsCollection;
import model.Settings;
import persistence.FileContactsLoader;
import view.AddressBookFrame;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class test {
    public static void main(String[] args) {
                try{
           /* Contact contact1 = new Contact();
            contact1.setCountry("US");
            contact1.setZip("66085");
            contact1.setLastName("shahin");
            
            Contact contact2 = new Contact();
            contact2.setCountry("FR");
            contact2.setZip("66085");
            contact2.setLastName("ahmad");
            Contact contact3 = new Contact();
            contact3.setCountry("US");
            contact3.setZip("66085");
            contact3.setLastName("salem");
            ContactsCollection cc = new ContactsCollection();
            cc.addContact(contact3);
            cc.addContact(contact2);
            cc.addContact(contact1);
            Settings.registry = cc;
             AddressBookFrame frame = new AddressBookFrame();
            frame.setContacts(cc);
             frame.setTitle("Listes des contacts");
           frame.setSize(790,320);
            frame.setResizable(false);
            frame.setVisible(true);
*/
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
           FileContactsLoader loader = new FileContactsLoader();
           Collection collection = loader.loadContacts();
           registry.addAll(collection);
           AddressBookFrame frame = new AddressBookFrame();
           frame.setContacts(registry);
           frame.setTitle("Listes des contacts");
           frame.setSize(790,320);
            frame.setResizable(false);
            frame.setVisible(true);
                }catch(Exception e){
            e.printStackTrace();
        }

    }
}
