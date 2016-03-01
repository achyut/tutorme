package uta.edu.tutorme.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uta.edu.tutorme.R;
import uta.edu.tutorme.generators.CategoryGenerator;
import uta.edu.tutorme.generators.InitializeTables;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.repositories.SubCategoryRepository;
import uta.edu.tutorme.repositories.UserRepository;
import uta.edu.tutorme.services.UserService;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Validator;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    UserService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        UserRepository repository = new UserRepository();
        service = new UserService(repository);

        CategoryRepository catRepo = new CategoryRepository();
        SubCategoryRepository subCatRepo = new SubCategoryRepository();

        InitializeTables tables = new InitializeTables();
        tables.generateTables();

        CategoryGenerator generator = new CategoryGenerator(catRepo, subCatRepo);
        generator.generateCategory();
        generator.generateSubCategory();


    }

    public void initialize(){
        this.username = (EditText)findViewById(R.id.email_edit_txt);
        this.password = (EditText)findViewById(R.id.pwd_edit_txt);
    }

    public void doLogin(View view){
        String email = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(validateLogin(email,pass)){
            // login logic
            User user = service.login(email,pass);
            if(user!=null){
                // show another activity
                SharedPrefUtils.storeUserInsharedPref(getApplicationContext(),user);
                DisplayMessage.displayToast(getApplicationContext(), "Logging in");
                SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(), "Invalid login credentials");
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


    public void openRegister(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }
}
