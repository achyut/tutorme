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
    public void saveUser(){
        User testuser = new User();
        testuser.setId(1);
        testuser.setName("Achyut");
        repository.save(1,testuser);

        try {
            User saveduser = repository.findById(1);
            assertEquals("Achyut",saveduser.getName());
        } catch (RecordNotFoundException e) {
            fail("Record not found");
        }
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
