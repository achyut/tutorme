package uta.edu.tutorme.repositorytest;

import org.junit.Before;
import org.junit.Test;

import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.repositories.UserRepository;


import static org.junit.Assert.*;

/**
 * Created by ananda on 2/18/16.
 */
public class UserRepositoryTest {

    UserRepository repository;

    @Before
    public void setUp(){
        this.repository = new UserRepository();
    }

    @Test
    public void testLogin(){
        String email1 = "abc@gmail.com";
        String password = "testpass";

        boolean result = repository.login(email1,password);
        assertTrue(result);
        String email12 = "def@gmail.com";
        String password2 = "testpass";

        result = repository.login(email1,password);
        assertFalse(result);


    }
}
