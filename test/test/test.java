/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import model.Contact;
import model.ZipValidator;
/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class test {
    public static void main(String[] args) {
        ZipValidator zv = new ZipValidator();
        try{
            Contact contact = new Contact();
            contact.setCountry("US");
            contact.setZip("66085");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
