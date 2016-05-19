/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ContactsCollection {
    HashSet contactsSet;
    public ContactsCollection(){
        contactsSet = new HashSet<Contact>();
    }
    public void addContact(Contact c){
        contactsSet.add(c);
    }
    /*public void load(Hashtable<String, Contact> source){
        hashTable.putAll(source);
    }*/
    public void deleteContact(Contact c){
        contactsSet.remove(c);
    }
    public Iterator<Contact> getContactsList(){
        return contactsSet.iterator();
    }
}
