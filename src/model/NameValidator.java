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
 * 
 */
class NameValidator implements Validator {
    public NameValidator() {
    }
    public void validate(String field,Contact contact) throws InvalidFieldException{
        if (ContactRepository.getInstance().contains(contact))
            throw new InvalidFieldException(Utility.getString("invalidFieldException.nameValidator"));//todo change message
    }
}
