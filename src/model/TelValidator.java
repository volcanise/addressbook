/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author shahin.behrooz@gmail.comsssss
 */
public class TelValidator implements Validator {

    public TelValidator() {
    }
    public void validate(String field,Contact contact) throws InvalidFieldException{
        //todo exception message must be customized
        if ((Settings.TEL_REQUIRED) && ((field == null) || (field.trim().length() == 0)))
                throw new InvalidFieldException("Phone number cannot be empty.");
     return;
    }

    
}
