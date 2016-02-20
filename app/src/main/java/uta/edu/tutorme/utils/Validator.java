package uta.edu.tutorme.utils;

/**
 * Created by ananda on 2/18/16.
 */
public class Validator {

    public static boolean validateEmail(String email){
        return true;
    }

    public static boolean validatePassword(String password){

        if(password.isEmpty()){
            return false;
        }
        return true;
    }


}
