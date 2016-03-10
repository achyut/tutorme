package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.utils.Validator;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class RegisterActivity extends AppCompatActivity  implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "POST_ADDNEW";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;

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
        initialize();
        progressDialog = new ProgressDialog(this);
    }

    protected void onStart() {
        super.onStart();
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
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

    private JSONObject getRegisterJsonObject(){
        String namestr = name.getText().toString().trim();
        String emailstr = email.getText().toString().trim();
        String phonestr = phone.getText().toString().trim();
        String addressstr = address.getText().toString().trim();
        String passwordstr = password.getText().toString().trim();
        String confirmstr = confirmpass.getText().toString().trim();
        String usertypestr = usertype.getText().toString().trim();
        Map<String, String> reqmap = new HashMap<String, String>();
        reqmap.put("name", namestr);
        reqmap.put("email",emailstr);
        reqmap.put("address",addressstr);
        reqmap.put("contact",phonestr);
        reqmap.put("password",passwordstr);
        reqmap.put("usertype",usertypestr);
        JSONObject obj = new JSONObject(reqmap);
        return obj;
    }


   public void doRegister(View view){
        if(validateRegister()){
            progressDialog.setMessage("Registering!!!");
            progressDialog.show();
            MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.USERS,
                    getRegisterJsonObject(), this, this);


            postRequest.setTag(REQUEST_TAG);
            mQueue.add(postRequest);

        }
        else{
            DisplayMessage.displayToast(getApplicationContext(),"Please enter correct values");
        }
   }


    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        NetworkResponse response = error.networkResponse;

        if(response!=null && response.statusCode == 404){
            DisplayMessage.displayToast(getApplicationContext(), "Please check your Internet Connection!!");
        }
        else if (response != null && response.statusCode == 400) {
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }
    }

    @Override
    public void onResponse(JSONObject response) {
            progressDialog.hide();
        try {
            if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(),"You have been registered!!!");
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
