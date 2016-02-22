package uta.edu.tutorme.repositories;

import uta.edu.tutorme.models.User;

/**
 * Created by ananda on 2/18/16.
 */
public class UserRepository extends MapRepositoryImpl<Integer,User> {


    public boolean login(String email, String password) {

        return false;
    }


}