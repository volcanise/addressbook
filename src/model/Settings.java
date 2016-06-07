/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * 
 */
public class Settings {
    public static final int CA = 25;
    public static final int US = 30;
    public static final int OTHER = -1;
    
    //initialises at start up
    public static int DEFAULT_COUNTRY = CA;
    public static boolean VALIDATE_ZIP = true;
    public static boolean VALIDATE_EMAIL = true;
    public static boolean TEL_REQUIRED = true;
    public static boolean ZIP_REQUIRED = true;
    public static boolean EMAIL_REQUIRED = true;
    
    public static File DATA_FILE;
    public static String DELIMITER;// = ",";
    public static Locale LOCALE = new Locale("en");
    public static ResourceBundle resources = ResourceBundle.getBundle("resources",LOCALE);
    public static String[] SUPPURTED_LANGS = {"en","fr"};

    public static void saveToFile() {
           try{
           FileInputStream fin = new FileInputStream(Settings.class.getResource("/settings.properties").getFile());
           Properties props = new Properties();
           props.load(fin);
           props.put("VALIDATE_ZIP",String.valueOf(VALIDATE_ZIP).toUpperCase() );
           props.put("VALIDATE_EMAIL",String.valueOf(VALIDATE_EMAIL).toUpperCase() );
           props.put("ZIP_REQUIRED",String.valueOf(ZIP_REQUIRED).toUpperCase() );
           props.put("TEL_REQUIRED",String.valueOf(TEL_REQUIRED).toUpperCase() );
           props.put("EMAIL_REQUIRED",String.valueOf(EMAIL_REQUIRED).toUpperCase() );
           fin.close();
           FileOutputStream fout = new FileOutputStream(Settings.class.getResource("/settings.properties").getFile());
           props.store(fout, "");
           fout.flush();
           fout.close();
           }catch(Exception e){
               e.printStackTrace();// todo change
           }

    }
public static void loadFromFile() throws Exception{
           FileInputStream fin = new FileInputStream(Settings.class.getResource("/settings.properties").getFile());
           Properties props = new Properties();
           props.load(fin);
           String value = (String)props.get("DEFAULT_COUNTRY");
           if (value != null)
               if (value.equals("CA")) 
                 Settings.DEFAULT_COUNTRY = Settings.CA;
               else if (value.equals("US")) 
                 Settings.DEFAULT_COUNTRY = Settings.US;
                 else
                   Settings.DEFAULT_COUNTRY = Settings.OTHER;
           value = (String)props.get("DATA_FILE");
           if (value != null)
               Settings.DATA_FILE = new File(value);
           else 
               Settings.DATA_FILE = new File("database.dat");
           value = (String)props.get("TEL_REQUIRED");
           if (value != null)
               if (value.toLowerCase().equals("true"))
                   Settings.TEL_REQUIRED = true;
               else if (value.toLowerCase().equals("false"))
                   Settings.TEL_REQUIRED = false;
           else
                   Settings.TEL_REQUIRED = true;
           value = (String)props.get("EMAIL_REQUIRED");
           if (value != null)
               if (value.toLowerCase().equals("true"))
                   Settings.EMAIL_REQUIRED = true;
               else if (value.toLowerCase().equals("false"))
                   Settings.EMAIL_REQUIRED = false;
           else
                   Settings.EMAIL_REQUIRED = true;


           value = (String)props.get("ZIP_REQUIRED");
           if (value != null)
               if (value.toLowerCase().equals("true"))
                   Settings.ZIP_REQUIRED = true;
               else if (value.toLowerCase().equals("false"))
                   Settings.ZIP_REQUIRED = false;
           else
                   Settings.ZIP_REQUIRED = true;
           value = (String)props.get("VALIDATE_ZIP");
           if (value != null)
               if (value.toLowerCase().equals("true"))
                   Settings.VALIDATE_ZIP = true;
               else if (value.toLowerCase().equals("false"))
                   Settings.VALIDATE_ZIP = false;
           else
                   Settings.VALIDATE_ZIP = true;
           value = (String)props.get("VALIDATE_EMAIL");
           if (value != null)
               if (value.toLowerCase().equals("true"))
                   Settings.VALIDATE_EMAIL = true;
               else if (value.toLowerCase().equals("false"))
                   Settings.VALIDATE_EMAIL = false;
           else
                   Settings.VALIDATE_EMAIL = true;

           value = (String)props.get("DELIMITER");
           if (value != null)
               Settings.DELIMITER = value;
}
}
