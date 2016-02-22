package uta.edu.tutorme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uta.edu.tutorme.R;
import uta.edu.tutorme.generators.CategoryGenerator;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.repositories.UserRepository;
import uta.edu.tutorme.services.UserService;
import uta.edu.tutorme.utils.Validator;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.username = (EditText)findViewById(R.id.txt_login_email);
        this.password = (EditText)findViewById(R.id.txt_login_password);

        UserRepository repository = new UserRepository();
        service = new UserService(repository);

        CategoryRepository catRepo = new CategoryRepository();

        CategoryGenerator generator = new CategoryGenerator(catRepo);
        generator.generateCategory();
    }


    public void doLogin(View view){
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if(validateLogin(email,pass)){
            // login logic
            if(service.login(email,password)){
                // show another activity
                Toast.makeText(getApplicationContext(),"Loging in",Toast.LENGTH_LONG);
            }
            else{
                Toast.makeText(getApplicationContext(),"Invalid login credentials",Toast.LENGTH_LONG);
            }

        }
    }


    private boolean validateLogin(String email,String pass){
        boolean result = true;
        if(!Validator.validateEmail(email)){
            username.setError(getString(R.string.validate_email));
            result = false;
        }
        if(!Validator.validatePassword(pass)){
            password.setError(getString(R.string.validate_password));
            result = false;
        }
        return result;
    }
}
