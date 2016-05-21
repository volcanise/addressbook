/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ContactsCollection {
    TreeSet contactsSet;
    public ContactsCollection(){
        contactsSet = new TreeSet<Contact>();
    }
    public void addContact(Contact c){
        contactsSet.add(c);
    }
    public void addAll(Collection<Contact> cn){
        contactsSet.addAll(cn);
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
    public boolean contains(Object obj){
        return contactsSet.contains(obj);
    }
}
