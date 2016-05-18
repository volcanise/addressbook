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
class ZipValidator implements Validator {
    public final String CA_ZIP_PATTERN = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";
    public final String US_ZIP_PATTERN = "^[0-9]{5}(?:-[0-9]{4})?$";
    public ZipValidator() {
    }

    @Override
    public void validate(String field) throws InvalidFieldException {
        if (!field.matches(CA_ZIP_PATTERN))
            throw new InvalidFieldException(field + " is not a valid canadian zip code.");
    }
    
}
