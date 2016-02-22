package uta.edu.tutorme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uta.edu.tutorme.R;
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
        this.username = (EditText)findViewById(R.id.email_edit_txt);
        this.password = (EditText)findViewById(R.id.pwd_edit_txt);
        UserRepository repository = new UserRepository();
        service = new UserService(repository);
    }

    public void doLogin(View view){
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if(validateLogin(email,pass)){
            // login logic
            if(service.login(email,pass)){
                // show another activity
                Toast toastemail = Toast.makeText(getApplicationContext(),"Logging in",Toast.LENGTH_LONG);
                toastemail.show();
            }
            else{
                Toast toastpwd = Toast.makeText(getApplicationContext(),"Invalid login credentials",Toast.LENGTH_LONG);
                toastpwd.show();
            }

        }
    }


    private boolean validateLogin(String email,String pass){
        boolean result = true;
        if(email.isEmpty()){
            username.setError(getString(R.string.email_blank));
            result = false;
        }
        else{
            if(!Validator.validateEmail(email)){
                username.setError(getString(R.string.validate_email));
                result = false;
            }
        }

        if(pass.isEmpty()){
            password.setError(getString(R.string.pwd_blank));
            result = false;
        }
        else{
            if(!Validator.validatePassword(pass)){
                password.setError(getString(R.string.validate_password));
                result = false;
            }
        }
        return result;
    }
}
