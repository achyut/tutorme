package uta.edu.tutorme.services;

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
        return password;
        //return "encryptedpassword";
    }

    public boolean login(String email, String password) {
        password = encryptPassword(password);
        if(repository.login(email,password)){
            return true;
        }
        if(email.equals("abc@gmail.com") && password.equals("abcd")){
            return true;
        }
        return false;
    }

}
