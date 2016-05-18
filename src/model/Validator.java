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
public interface Validator {
    /**
     * gets field as the field to be validated and contact to have access to other fields. Because some validations
     * are mutually dependent. For example to validate zip code it should know about the country field. 
    */
    public void validate(String field,Contact contact) throws InvalidFieldException;
}
