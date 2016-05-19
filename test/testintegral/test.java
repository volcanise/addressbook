/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testintegral;

import model.Contact;
import model.ContactsCollection;
import view.AddressBookFrame;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class test {
    public static void main(String[] args) {
                try{
            Contact contact1 = new Contact();
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
            
             AddressBookFrame frame = new AddressBookFrame();
            frame.setContacts(cc);
             frame.setTitle("Listes des contacts");
           frame.setSize(790,320);
            frame.setResizable(false);
            frame.setVisible(true);

            }catch(Exception e){
            e.printStackTrace();
        }

    }
}
