/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.File;
import java.util.Iterator;
import model.Contact;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public interface ContactsSaver{
    public void save(Iterator<Contact> itr) throws Exception;
    
}
