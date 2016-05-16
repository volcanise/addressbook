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
public class Contact {
    //les attribut d<un contact
    private String name;
    private String tel;
    // validator pour chaque attribut
    private Validator nameValidator = new NameValidator();
    private Validator telValidator = new TelValidator();
    
    public void setName(String name) throws InvalidFieldException{
        nameValidator.validate(name);
        this.name = name;
    }
    public void setNameValidator(Validator v){
        nameValidator = v;
    }

    String getId() {
//todo ajouter id
    }
    /** modified for test
     * todo: a ajouter les autres setters selon exemple
     * */
    
}
