package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.utils.Validator;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class UpdateProfilePage extends AppCompatActivity  implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "POST_UPDATE";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;

    EditText name;
    EditText email;
    EditText phone;
    EditText address;
    User user;

    private void initialize(){
        name = (EditText)findViewById(R.id.update_edit_Name);
        email = (EditText)findViewById(R.id.update_edit_email);
        phone = (EditText)findViewById(R.id.update_edit_phone);
        address = (EditText)findViewById(R.id.update_edit_address);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_page);
        initialize();
        progressDialog = new ProgressDialog(this);
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        loadUser();
    }

    public void loadUser() {
        User usr = SharedPrefUtils.getUserFromSession(getApplication());
        progressDialog.setMessage("Loading user data.");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getUserDetails(usr.getId()),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject user) {
                try {

                    name.setText(user.getString("name"));
                    email.setText(user.getString("email"));
                    phone.setText(user.getString("contact"));
                    address.setText(user.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
            }
        });

        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);
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


    private boolean validateProfile(){
        String namestr = name.getText().toString();
        String emailstr = email.getText().toString();
        String phonestr = phone.getText().toString();
        String addressstr = address.getText().toString();

        boolean result = true;
        if(!Validator.validateName(namestr)){
            name.setError("Name cannot be empty");
            result = false;
        }
        if(!Validator.validatePhoneNumber(phonestr)){
            phone.setError("Phone number cannot be empty");
            result = false;
        }

        if(!Validator.validateAddress(addressstr)){
            address.setError("Address cannot be empty");
            result = false;
        }
        return result;
    }

    private JSONObject getUpdateProfileJsonObject(User usr){
        String namestr = name.getText().toString().trim();
        String phonestr = phone.getText().toString().trim();
        String addressstr = address.getText().toString().trim();
        Map<String, String> reqmap = new HashMap<String, String>();
        reqmap.put("name", namestr);
        reqmap.put("address",addressstr);
        reqmap.put("contact",phonestr);
        reqmap.put("email",usr.getEmail());
        reqmap.put("usertype",usr.getUsertype());
        JSONObject obj = new JSONObject(reqmap);
        return obj;
    }


    public void doUpdateProfile(View view){
        if(validateProfile()){
            User usr = SharedPrefUtils.getUserFromSession(getApplication());
            progressDialog.setMessage("Updating Profile!!!");
            progressDialog.show();
            MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.getUserDetails(usr.getId()),
                    getUpdateProfileJsonObject(usr), this, this);


            postRequest.setTag(REQUEST_TAG);
            mQueue.add(postRequest);

        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "Please enter correct values");
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
                DisplayMessage.displayToast(getApplicationContext(), "Profile has been updated!!!");
                Intent intent = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(intent);

                String username = response.getString("name");
                String address = response.getString("address");
                String contact = response.getString("contact");
                String email = response.getString("email");
                String usertype = response.getString("usertype");
                int id = response.getInt("id");
                User usr = new User();
                usr.setEmail(email);
                usr.setAddress(address);
                usr.setPhone(contact);
                usr.setUsertype(usertype);
                usr.setName(username);
                usr.setId(id);
                TextView usrn = (TextView) findViewById(R.id.drawer_username);

                usrn.setText(username);

                TextView eml = (TextView) findViewById(R.id.drawer_user_email);
                eml.setText(email);
                SharedPrefUtils.storeUserInsharedPref(getApplicationContext(),usr);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
