/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Validates email entries
 * @author shahin.behrooz@gmail.com
 */
public class EmailValidator implements Validator{
    private String reg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public void validate(String field,Contact c) throws InvalidFieldException{
        if (field != null)
            if (!field.matches(reg))
                throw new InvalidFieldException(field + " is not a valid Email Addresse");//todo change message
    }
}
