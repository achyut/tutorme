package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import uta.edu.tutorme.R;

public class EditProfileActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {
    public static final String REQUEST_TAG = "EDIT_PROFILE";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;

    EditText editname;
    EditText editemail;
    EditText editphone;
    EditText editaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
