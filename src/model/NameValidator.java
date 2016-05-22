/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
class NameValidator implements Validator {
    public NameValidator() {
    }
    public void validate(String field,Contact contact) throws InvalidFieldException{
        if (Settings.registry.contains(contact))
            throw new InvalidFieldException("This contact already exists.");//todo change message
    }
}
