package uta.edu.tutorme;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import uta.edu.tutorme.models.User;

import uta.edu.tutorme.repositories.RegisterRepository;
import uta.edu.tutorme.repositories.UserRepository;
import uta.edu.tutorme.services.UserService;

public class Register extends AppCompatActivity {
UserService service;
    RegisterRepository regisrepository=new RegisterRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserRepository repository = new UserRepository();
        service = new UserService(repository);

    }

   public void doRegister(View view){
       EditText editname=(EditText)findViewById(R.id.edit_Name);
       String name=editname.getText().toString();
       EditText editemail=(EditText)findViewById(R.id.edit_email);
       String email=editemail.getText().toString();
       EditText editphone=(EditText)findViewById(R.id.edit_phone);
       String phone=editphone.getText().toString();
       EditText editaddress=(EditText)findViewById(R.id.edit_address);
       String address=editaddress.getText().toString();
       if(regisrepository.checkUserExist(email)==true){

       }
       else{
           User user=new User();
           user.setName(name);
           user.setEmail(email);
           user.setAddress(address);
           user.setPhone(phone);

           regisrepository.saveUser(user);
       }
   }



}
