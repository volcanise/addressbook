/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import model.Contact;
import model.InvalidFieldException;
import model.Settings;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class FileContactsLoader implements ContactsLoader, ContactsSaver{

    @Override
    public Collection<Contact> loadContacts() throws Exception{
        ArrayList<Contact> lst = new ArrayList<Contact>();
    try{
        FileReader fin = new FileReader(Settings.DATA_FILE);
        BufferedReader br = new BufferedReader(fin);
        String line = null;
        while((line = br.readLine()) != null){
        Contact c = createContact(line);
        if (c != null)
            lst.add(c);
            };
        return lst;
    }catch(Exception e){
        throw e;
    }
    }
    private Contact createContact(String str){
        String[] s = str.split("[" + Settings.DELIMITER + "]");
        if (s == null || s.length != 11)// the number of fields is 11
        {System.out.println("Warning: the line has invalid format. Line contect is: " + str);//just a warning and going to the next line
        return null;
        }
        Contact contact = new Contact();
        try{
        contact.setLastName(s[0]);
        }catch(InvalidFieldException e){
            //this was already saved so take it easy and simply ignore
            //just a message
            System.out.println("Warning: " + s[0] + " is not a valid last name.");//todo customize message
        }
        contact.setMiddleName(s[1]);
        contact.setFirstName(s[2]);
        contact.setTel(s[3]);
        try{
        contact.setEmail(s[4]);
        }catch(InvalidFieldException e){
            // this was already saved so take it easy and simply ignore
            System.out.println("Warning: " + s[4] + " is not a valid email.");//todo customize message
        }
        contact.setCountry(s[5]);//setCountry must come before setZip because zip checking depends on it
        try{
        contact.setZip(s[6]);
        }catch(InvalidFieldException e){
            //// this was already saved so take it easy and simply ignore
            System.out.println("Warning: " + s[6] + " is not a valid zip.");//todo customize message
        }
        contact.setState(s[7]);
        contact.setAddress1(s[8]);
        contact.setAddress2(s[9]);
        contact.setCity(s[10]);
        return contact;
    }
    @Override
    public void save(Collection<Contact> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
