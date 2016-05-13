/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Hashtable;

/**111122222
 *
 * @author shahin.behrooz@gmail.com
 */
public class ContactsCollection {
    private Hashtable<String, Contact> hashTable;
    public ContactsCollection(){
        hashTable = new Hashtable<String, Contact>();
    }
    public void addContact(Contact c){
        hashTable.put(c.getId(), c);
    }
    public void load(Hashtable<String, Contact> source){
        hashTable.putAll(source);
    }
    public void deleteContact(String id){
        hashTable.remove(id);
    }
}
