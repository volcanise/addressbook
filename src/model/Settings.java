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
public class Settings {
    public static final int CA = 25;
    public static final int US = 30;
    public static final int OTHER = -1;
    
    //initialises at start up
    public static int DEFAULT_COUNTRY = CA;
    public static boolean VALIDATE_ZIP = true;
    public static boolean TEL_REQUIRED = true;
    public static boolean ZIP_REQUIRED = true;
}
