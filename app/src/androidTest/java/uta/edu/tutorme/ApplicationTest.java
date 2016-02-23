package uta.edu.tutorme;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.orm.SugarContext;

import uta.edu.tutorme.models.User;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@SmallTest
public class ApplicationTest extends ApplicationTestCase<Application> {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // SugarORM need some time to start up, else tests will fail
        Thread.sleep(3000);
    }

    public ApplicationTest() {
        super(Application.class);
        SugarContext.init(getContext());
    }


    public void testCreateUser(){
        User usr = new User();
        usr.setName("Achyut");
        usr.setEmail("achyut.pdl@gmail.com");
        usr.save();
    }
}