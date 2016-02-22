package uta.edu.tutorme.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ananda on 2/18/16.
 */
public class Validator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateEmail(String email){
        if(email.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean validatePassword(String password){

        if(password.isEmpty()){
            return false;
        }
        return true;
    }


}
