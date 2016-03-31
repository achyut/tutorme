package uta.edu.tutorme.activities;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.utils.Validator;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "LOGIN_ACTIVITY";

    EditText username;
    EditText password;

    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    @Override
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

    public void initialize(){
        this.username = (EditText)findViewById(R.id.email_edit_txt);
        this.password = (EditText)findViewById(R.id.pwd_edit_txt);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;

        if(response!=null && response.statusCode == 401){
            DisplayMessage.displayToast(getApplicationContext(), "Invalid login credentials");
        }
        else if(response!=null && response.statusCode == 404){
            DisplayMessage.displayToast(getApplicationContext(), "Please check your Internet Connection!!");
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }

    }

    private void setUserInSession(JSONObject response) throws JSONException {
        int id = response.getInt("id");
        String name = response.getString("name");
        String email = response.getString("email");
        String usertype = response.getString("usertype");
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setUsertype(usertype);
        SharedPrefUtils.storeUserInsharedPref(getApplicationContext(), user);
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            if(!response.getBoolean("error")){
                setUserInSession(response);
                DisplayMessage.displayToast(getApplicationContext(), "Logging in");
                Intent i = new Intent(getApplicationContext(),HomepageActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getLoginRequestObject(String email,String pass){
        Map<String,String> obj = new HashMap<>();
        obj.put("email",email);
        obj.put("password", pass);
        return new JSONObject(obj);
    }


    public void doLogin(View view){
        String email = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(validateLogin(email,pass)){

            MyJsonObjectRequest loginRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.LOGIN_URL,
                    getLoginRequestObject(email,pass), this, this);

            loginRequest.setTag(REQUEST_TAG);
            mQueue.add(loginRequest);
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

    public void doForgotPassword(View view){
        Intent intent = new Intent(getApplicationContext(),ForgetPasswordActivity.class);
        startActivity(intent);
    }

}
