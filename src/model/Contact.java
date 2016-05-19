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
    private String lastName;
    private String tel;
    private String firstName;
    private String middleName;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    // validator pour chaque attribut
    private Validator nameValidator = new NameValidator();
    private Validator telValidator = new TelValidator();
    private Validator zipValidator = new ZipValidator();
    
    public void setLastName(String name) throws InvalidFieldException{
        nameValidator.validate(name,this);
        this.lastName = name;
    }
    public void setNameValidator(Validator v){
        nameValidator = v;
    }

    String getId() {
//todo ajouter id
    return "id";//to be changed to add an id real
    }
    /** modified for test
     * todo: a ajouter les autres setters selon exemple
     * */
    public void setZip(String zip)throws InvalidFieldException{
        if (Settings.VALIDATE_ZIP)
            zipValidator.validate(zip,this);
    }
    //setCountry must be called before setZip because validation of zip depends on country value
    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return  lastName ;
    }
    
}
