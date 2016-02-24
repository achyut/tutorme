package uta.edu.tutorme.repositories;

import uta.edu.tutorme.models.User;

/**
 * Created by heary on 2/22/16.
 */
public class RegisterRepository extends MapRepositoryImpl<Integer,User> {
    public boolean checkUserExist(String email)  {
        return true;
    }
    public void saveUser(User user){

    }


}


