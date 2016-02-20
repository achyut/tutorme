package uta.edu.tutorme.servicetest;

import org.junit.Before;
import org.junit.Test;

import uta.edu.tutorme.models.User;
import uta.edu.tutorme.repositories.UserRepository;
import uta.edu.tutorme.services.UserService;


import static org.junit.Assert.*;
/**
 * Created by ananda on 2/18/16.
 */
public class UserServiceTest {

    UserService service;

    @Before
    public void setUp(){
        UserRepository repo = new UserRepository();
        this.service = new UserService(repo);
    }


    @Test
    public void testEncryptPassword(){
        assertEquals("encryptedpassword",service.encryptPassword("password"));
    }


}
