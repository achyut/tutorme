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
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }

    public static boolean validateName(String name){
        if(name.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean validatePhoneNumber(String phone){
        if(phone.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean validateAddress(String address){
        if(address.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String password){
        if(password.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean validateUsertype(String usertype){
        if(usertype.isEmpty()){
            return false;
        }
        return true;
    }

}
