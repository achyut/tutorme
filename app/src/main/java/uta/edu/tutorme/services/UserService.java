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

    public User login(String email, String password) {
        password = encryptPassword(password);

        return repository.login(email,password);
    }

    public boolean checkIfUserAlreadyExist(User user) {
        return repository.checkUserExists(user);
    }
}
