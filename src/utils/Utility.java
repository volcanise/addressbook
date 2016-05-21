/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author shahin.behrooz@gmail.com
 */
public class Utility {
    /**
     * Checks if string is null or lenght zero without spaces.
     * @param str
     * @return 
     */
    public static boolean isEmpty(String str){
        return (str == null) || (str.trim().length() == 0);
    }
    public static String nvl(Object obj,String s){
        return (obj == null)?s:obj.toString();//if obj equals null return value of s otherwise return string value of obj
    }
}
