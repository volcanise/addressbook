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
public class ZipValidator implements Validator {
    public final String CA_ZIP_PATTERN = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
    public final String US_ZIP_PATTERN = "^[0-9]{5}(?:-[0-9]{4})?$";
    
    private int country;
    public ZipValidator() {
        this.country = Settings.DEFAULT_COUNTRY;
    }
    // To validate the Zip format, the country attribut needs to be get prior
    public void validate(String field,Contact contact) throws InvalidFieldException {
        
        if (utils.Utility.isEmpty(field) && Settings.ZIP_REQUIRED)
            throw new InvalidFieldException(Utility.getString("invalidfieldexception.zipvalidator"));
        String countryStr = contact.getCountry();
        if (!utils.Utility.isEmpty(countryStr))
        if (countryStr.equals("CA")||countryStr.equals("ca")||countryStr.equals("canada")||countryStr.equals("CANADA"))
            country = Settings.CA;
        else if (countryStr.equals("US")||countryStr.equals("us"))
            country = Settings.US;
        else 
            country = Settings.OTHER;
        switch (country){
            case(Settings.CA):
                if (!field.matches(CA_ZIP_PATTERN))
                    throw new InvalidFieldException("'"+field +"' "+Utility.getString("invalidfieldexception.canadianzipvalidator"));
                break;
            case (Settings.US):
                if (!field.matches(US_ZIP_PATTERN))
                    throw new InvalidFieldException("'"+field +"' "+ Utility.getString("invalidfieldexception.uszipvalidator"));
                break;
            default:
                return;
        }
        
            
    
    }
    
}
