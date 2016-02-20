package uta.edu.tutorme.services;

import android.widget.EditText;

import uta.edu.tutorme.models.User;
import uta.edu.tutorme.repositories.UserRepository;

/**
 * Created by ananda on 2/18/16.
 */
public class UserService extends GenericSerciveImpl<Integer,User,UserRepository>{

    public UserService(UserRepository repository) {
        super(repository);
    }

    public String encryptPassword(String password) {
        return "encryptedpassword";
    }

    public boolean login(String email, EditText password) {
        if(email.equalsIgnoreCase("abc")){
            return true;
        }
        else{
            return false;
        }
    }

}
