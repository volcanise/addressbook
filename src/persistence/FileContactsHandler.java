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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import model.Contact;
import model.ContactsCollection;
import model.InvalidFieldException;
import model.Settings;
import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class FileContactsHandler implements ContactsLoader, ContactsSaver{

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
        fin.close();
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
    public void save(Iterator<Contact> itr) throws Exception{
       FileOutputStream fout = new FileOutputStream(Settings.DATA_FILE);
        PrintWriter printer = new PrintWriter(fout);
        while(itr.hasNext()){
            Contact cnt = itr.next();
            printer.println(prepareString(cnt));
        }
        printer.flush();
        //printer.close();
        fout.close();
    }

    private String prepareString(Contact contact) {
        String record = Utility.nvl(contact.getLastName(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getMiddleName(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getFirstName(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getTel(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getEmail(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getCountry(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getZip(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getState(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getAddress1(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getAddress2(),"") + Settings.DELIMITER +
                        Utility.nvl(contact.getCity(),"");
                return record;
       }

    
    
}
