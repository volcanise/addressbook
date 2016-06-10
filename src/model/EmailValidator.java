/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Utility;

/**
 *
 * 
 */
public class EmailValidator implements Validator{
    //Validation Format
    private String reg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public void validate(String field,Contact c) throws InvalidFieldException{
        if (field != null)
            if (!field.matches(reg))
                throw new InvalidFieldException(Utility.getString("invalidFieldException.emailValidator"));
    }
}
