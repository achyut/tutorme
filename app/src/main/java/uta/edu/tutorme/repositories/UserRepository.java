package uta.edu.tutorme.repositories;

import com.orm.query.Condition;
import com.orm.query.Select;

import uta.edu.tutorme.models.User;

/**
 * Created by ananda on 2/18/16.
 */
public class UserRepository extends MapRepositoryImpl<Integer,User> {


    @Override
    public void save(Integer id, User user) {
        user.save();
    }

    public User login(String email, String password) {
        Select loginQuery = Select.from(User.class)
                .where(Condition.prop("email").eq(email))
                .where(Condition.prop("password").eq(password));
        User user = (User)loginQuery.first();
        return user;
    }

    public boolean checkUserExists(User user) {
        String[] params = new String[1];
        params[0] = user.getEmail();
        long noOfUsers = User.count(User.class, "email = ?",params);
        if(noOfUsers>0){
            return true;
        }
        return false;
    }
}
