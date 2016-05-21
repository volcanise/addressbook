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
public class Contact implements Comparable<Contact>{
    //les attribut d<un contact
    private String lastName;
    private String tel;
    private String firstName;
    private String middleName = "";
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
    private Validator emailValidator = new EmailValidator();
    
    public void setLastName(String name) throws InvalidFieldException{
        this.lastName = name;
        nameValidator.validate(name,this);
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
        this.zip = zip;//first set then check. this is for situation that we ignored validation and saved to file and 
        //we want to reload file. it should not block loading already saved records
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
        return  getLastName() + ", " + getMiddleName() + "," + getFirstName() ;
    }

    @Override
    public int compareTo(Contact c) {
        return (this.getLastName().compareTo(c.getLastName()));
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) throws InvalidFieldException{
        this.email = email;//first set it
        emailValidator.validate(email, this);
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }
    
}
