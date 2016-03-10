package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.utils.Validator;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class ForgetPasswordActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    ProgressDialog pDialog;
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "FORGOT_PASSWORD_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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

    private String getValueFromTextView(int editTextId){
        EditText editText = (EditText) findViewById(editTextId);
        return editText.getText().toString().trim();
    }

    private Map<String,String> getForgotPasswordParameters(String email) {
        Map<String, String> props = new HashMap<String, String>();
        props.put("email", email);
        return props;
    }

    public void doForgotPassword(View view){
        String email = getValueFromTextView(R.id.forgotpassword_email).trim();
        if(validForgotPassword(email)){
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Resetting password. Please wait...");
            pDialog.show();
            JSONObject obj = new JSONObject(getForgotPasswordParameters(email));
            MyJsonObjectRequest loginRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.FORGOT_PASSWORD_URL,
                    obj, this, this);
            loginRequest.setTag(REQUEST_TAG);
            mQueue.add(loginRequest);
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pDialog.hide();
        NetworkResponse response = error.networkResponse;
        if(response!=null && response.statusCode == 400){
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(),VollyUtils.getString(response, "message"));
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        pDialog.hide();
        try {
            if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(), response.getString("message"));
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean validForgotPassword(String email){
        if(Validator.validateEmail(email)){
            return true;
        }
        else{
            EditText editText = (EditText) findViewById(R.id.forgotpassword_email);
            editText.setError("Invalid email entered.");
            return false;
        }
    }

}
