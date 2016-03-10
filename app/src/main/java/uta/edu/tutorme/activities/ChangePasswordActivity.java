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
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class ChangePasswordActivity extends AppCompatActivity  implements Response.Listener<JSONObject>,
        Response.ErrorListener{
    public static final String REQUEST_TAG = "CHANGE_PASSWORD_ACTIVITY";

    ProgressDialog pDialog;
    private RequestQueue mQueue;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }


    public void doUpdatePassword(View view){

        String oldpass = getValueFromTextView(R.id.updatepassword_oldpassword).trim();
        String newpass1 = getValueFromTextView(R.id.updatepassword_newpass1).trim();
        String newpass2 = getValueFromTextView(R.id.updatepassword_newpass2).trim();

        if(validateChangePassword(oldpass,newpass1,newpass2)){
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Updating new password...");
            pDialog.show();
            User user = SharedPrefUtils.getUserFromSession(getApplication());
            JSONObject obj = new JSONObject(getUpdatePasswordParameters());
            MyJsonObjectRequest loginRequest = new MyJsonObjectRequest(Request.Method
                    .POST,Urls.getChangePasswordUrl(user.getId()),
                    obj, this, this);

            loginRequest.setTag(REQUEST_TAG);
            mQueue.add(loginRequest);
        }

    }


    private boolean validateChangePassword(String oldpass,String newpass1,String newpass2){

        if(!newpass1.equals(newpass2)){
            DisplayMessage.displayToast(getApplicationContext(), "New password and confirm password should be same.");
            return false;
        }

        return true;
    }

    private String getValueFromTextView(int editTextId){
        EditText editText = (EditText) findViewById(editTextId);
        return editText.getText().toString();
    }

    private Map<String,String> getUpdatePasswordParameters() {
        Map<String, String> props = new HashMap<String, String>();
        props.put("oldpassword", getValueFromTextView(R.id.updatepassword_oldpassword));
        props.put("newpassword1", getValueFromTextView(R.id.updatepassword_newpass1));
        props.put("newpassword2", getValueFromTextView(R.id.updatepassword_newpass2));
        return props;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pDialog.hide();
        NetworkResponse response = error.networkResponse;
        if(response!=null && response.statusCode == 400){
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        pDialog.hide();
        try {
            if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(), response.getString("message"));
                Intent i = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(i);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
