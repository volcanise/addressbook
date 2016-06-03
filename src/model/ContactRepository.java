/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class ContactRepository {
    private TreeMap contactsSet;
    private static ContactRepository registry = new ContactRepository();//just one registry across the application
    private ContactRepository(){
        contactsSet = new TreeMap<String,Contact>();
    }
    public static ContactRepository getInstance(){
        return registry;
    }
    public void addContact(Contact c){
        contactsSet.put(c.getId(),c);
        
    }
    public void addAll(Collection<Contact> cn){
        for(Contact c:cn)
            contactsSet.put(c.getId(), c);
    }
    public void deleteContact(Contact c){
        contactsSet.remove(c.getId());
    }
    public Iterator<Contact> getContactsList(){
        return contactsSet.values().iterator();
    }
    public boolean contains(Object obj){
        return contactsSet.containsKey(((Contact)obj).getId());
    }
 
}
