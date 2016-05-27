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
public class Language {
    private String code;
    private String display;

    public Language(String code, String display) {
        this.code = code;
        this.display = display;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return display;
    }
    
}
