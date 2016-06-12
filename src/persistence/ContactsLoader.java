/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.File;
import java.util.Collection;
import model.Contact;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public interface ContactsLoader {
    //loads contacts regardless of real location of data, maybe on file or on database etc.
    public Collection<Contact> loadContacts() throws Exception;
    
}
