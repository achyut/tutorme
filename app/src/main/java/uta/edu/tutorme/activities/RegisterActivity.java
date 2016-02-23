package uta.edu.tutorme.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.User;

import uta.edu.tutorme.repositories.RegisterRepository;
import uta.edu.tutorme.repositories.UserRepository;
import uta.edu.tutorme.services.UserService;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Validator;

public class RegisterActivity extends AppCompatActivity {
    UserService service;

    EditText name;
    EditText email;
    EditText phone;
    EditText address;
    EditText password;
    EditText confirmpass;
    EditText usertype;

    private void initialize(){
         name = (EditText)findViewById(R.id.edit_Name);
         email = (EditText)findViewById(R.id.edit_email);
         phone = (EditText)findViewById(R.id.edit_phone);
         address = (EditText)findViewById(R.id.edit_address);
         password = (EditText)findViewById(R.id.edit_password);
         confirmpass = (EditText)findViewById(R.id.edit_confirm_password);
         usertype = (EditText)findViewById(R.id.edit_address);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserRepository repository = new UserRepository();
        service = new UserService(repository);
        initialize();
    }

    private boolean validateRegister(){
        String namestr = name.getText().toString();
        String emailstr = email.getText().toString();
        String phonestr = phone.getText().toString();
        String addressstr = address.getText().toString();
        String passwordstr = password.getText().toString();
        String confirmstr = confirmpass.getText().toString();
        String usertypestr = usertype.getText().toString();

        boolean result = true;
        if(!passwordstr.equals(confirmstr)){
            confirmpass.setError("Password and confirm password should match");
            result = false;
        }
        if(!Validator.validateName(namestr)){
            name.setError("Name cannot be empty");
            result = false;
        }
        if(emailstr.isEmpty()){
            email.setError("Email cannnot be empty");
            result = false;
        }
        else{
            if(!Validator.validateEmail(emailstr)){
                email.setError("Invalid email address");
                result = false;
            }
        }

        if(!Validator.validatePhoneNumber(phonestr)){
            phone.setError("Phone number cannot be empty");
            result = false;
        }

        if(!Validator.validateAddress(addressstr)){
            address.setError("Address cannot be empty");
            result = false;
        }

        if(!Validator.validatePassword(passwordstr)){
            password.setError("Invalid password");
            result = false;
        }
        return result;
    }

    private User getUser(){
        String namestr = name.getText().toString();
        String emailstr = email.getText().toString();
        String phonestr = phone.getText().toString();
        String addressstr = address.getText().toString();
        String passwordstr = password.getText().toString();
        String confirmstr = confirmpass.getText().toString();
        String usertypestr = usertype.getText().toString();
        User user = new User(1,namestr,emailstr,phonestr,addressstr,passwordstr,usertypestr);
        return user;
    }

   public void doRegister(View view){
        if(validateRegister()){
            User user = getUser();
            if(!service.checkIfUserAlreadyExist(user)){
                service.save(1, user);
                DisplayMessage.displayToast(getApplicationContext(),"You have been registered!!!");
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),"User with given email address already exists!");
            }
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(),"Please enter correct values");
        }
   }



}
