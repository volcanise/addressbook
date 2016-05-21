/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.Collection;
import model.Contact;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public interface ContactsSaver {
    public void save(Collection<Contact> c);
    
}
