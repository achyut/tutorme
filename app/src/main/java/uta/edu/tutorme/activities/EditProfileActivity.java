package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class EditProfileActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    public static final String REQUEST_TAG = "EDIT_PROFILE";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;

    EditText name;
    EditText email;
    EditText phonenumber;
    EditText address;
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

   public void getUserFromSession()
    {
       progressDialog.setMessage("Showing User Details...");
       progressDialog.show();
        int userid = SharedPrefUtils.getUserFromSession(getApplication()).getId();
        String url = Urls.getUserDetails(userid);
        MyJsonObjectRequest userdetailrequest = new MyJsonObjectRequest(Request.Method.GET,url,null,this,this);
        userdetailrequest.setTag(REQUEST_TAG);
        mQueue.add(userdetailrequest);
    }

    private User getUserInSession(JSONObject request)
    {User usr= new User();
        try {
            String uname = request.getString("name");
            String uemail = request.getString("email");
            String uaddr = request.getString("address");
            String ucno = request.getString("contact");
            name.setText(uname);
            email.setText(uemail);
            address.setText(uaddr);
            phonenumber.setText(ucno);
            usr.setPhone(ucno);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return usr;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        progressDialog = new ProgressDialog(this);

        name = (EditText)findViewById(R.id.editText_name_edit);
        email=(EditText)findViewById(R.id.editText_email_edit);
        address=(EditText)findViewById(R.id.editText_address_edit);
        phonenumber=(EditText)findViewById(R.id.editText_edit_contactnumber);
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        NetworkResponse response = error.networkResponse;

        if (response != null && response.statusCode == 400) {
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        } else {
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();
User usr = getUserInSession(response);

    }
}
