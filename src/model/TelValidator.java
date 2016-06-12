/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Utility;

/**
 *
 * @author shahin.behrooz@gmail.comsssss
 */
public class TelValidator implements Validator {

    public TelValidator() {
    }
    public void validate(String field,Contact contact) throws InvalidFieldException{
        
        if ((Settings.TEL_REQUIRED) && Utility.isEmpty(field))
                throw new InvalidFieldException(Utility.getString("invalidfieldexception.telvalidator"));
     return;
    }

    
}
