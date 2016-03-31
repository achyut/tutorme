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
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
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
    EditText password;
    EditText confirmpass;
    EditText usertype;

    private void initialize(){
        name = (EditText)findViewById(R.id.update_edit_Name);
        email = (EditText)findViewById(R.id.update_edit_email);
        phone = (EditText)findViewById(R.id.update_edit_phone);
        address = (EditText)findViewById(R.id.update_edit_address);
           usertype = (EditText)findViewById(R.id.edit_address);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_page);
        initialize();
        progressDialog = new ProgressDialog(this);
        User user = new User();
        user.setName("Achyut");
        user.setEmail("achyut.pdl@gmail.com");
        user.setAddress("Arbor Oaks, UTA");
        user.setPhone("4563733729");
        user.setUsertype("Tutor");

        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getAddress());
        address.setText(user.getPhone());
        usertype.setText(user.getUsertype());
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
        String usertypestr = usertype.getText().toString();

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

    private JSONObject getUpdateProfileJsonObject(){
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


    public void doUpdateProfile(View view){
        if(validateProfile()){
            progressDialog.setMessage("Updating Profile!!!");
            progressDialog.show();
            MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.USERS,
                    getUpdateProfileJsonObject(), this, this);


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
         //   if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(),"Profile has been updated!!!");
                Intent intent = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(intent);
           // }
          /*  else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
