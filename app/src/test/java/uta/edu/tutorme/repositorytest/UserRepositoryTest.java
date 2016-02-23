package uta.edu.tutorme.repositorytest;

import org.junit.Before;
import org.junit.Test;

import uta.edu.tutorme.models.User;
import uta.edu.tutorme.repositories.UserRepository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        String password = "abcd";

        boolean result = repository.login(email1,password);
        assertTrue(result);
        String email12 = "def@gmail.com";
        String password2 = "defg";

        result = repository.login(email1,password);
        assertFalse(result);

    }

    @Test
    public void testCreateUser(){
        User usr = new User();
        usr.setName("Achyut");
        usr.setEmail("achyut.pdl@gmail.com");
        usr.save();
    }
}
