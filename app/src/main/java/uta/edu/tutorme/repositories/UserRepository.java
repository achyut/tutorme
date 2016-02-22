package uta.edu.tutorme.repositories;

import uta.edu.tutorme.models.User;

/**
 * Created by ananda on 2/18/16.
 */
public class UserRepository extends MapRepositoryImpl<Integer,User> {


    public boolean login(String email, String password) {
        for(User obj:table.values()){
            if(obj.getEmail().equals(email) && obj.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}