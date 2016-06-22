/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author victorspringer
 */
public class YearValidator {
    
    private static YearValidator YearValidatorInstance;
    
    /**
     * 
     * @return YearValidator instance
     */
    public static synchronized YearValidator getInstance() {
        if (YearValidatorInstance == null) {
            YearValidatorInstance = new YearValidator();
        }
        
        return YearValidatorInstance;
    }
    
    /**
     * 
     * @param yearToValidate
     * @return True or false
     */
    public boolean validateYear(String yearToValidate) {

        if(yearToValidate != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            dateFormat.setLenient(false);

            try {
                //If not valid, it will throw ParseException
                Date date = dateFormat.parse(yearToValidate);

            } catch(ParseException pe) {
                return false;
            }
            
            return true;
        }

        return false;
    }
}
