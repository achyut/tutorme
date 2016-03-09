package uta.edu.tutorme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Validator;
import uta.edu.tutorme.volly.VolleyRequestQueue;


public class AddNewAdvActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {


    FloatingActionButton fabSubmit;
    public static final String REQUEST_TAG = "POST";
    private RequestQueue mQueue;
    EditText title;
    EditText shortDesc;
    EditText longDesc;
    EditText category;
    EditText subcategory;
    EditText startDate;
    EditText endDate;
    EditText startTime;
    EditText endTime;
    EditText address;
    EditText phoneNumber;
    EditText email;
    RadioButton prefCM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_adv);
        initialize();
        fabSubmit = (FloatingActionButton) findViewById(R.id.fab1);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }

        });
    }

    private void initialize() {
        this.title = (EditText)findViewById(R.id.edit_posttitle);
        this.shortDesc = (EditText)findViewById(R.id.edit_shortdes);
        this.longDesc = (EditText)findViewById(R.id.edit_longdes);
        this.startDate = (EditText)findViewById(R.id.edit_Startdate);
        this.endDate = (EditText)findViewById(R.id.edit_endTime);
        this.address=(EditText)findViewById(R.id.edit_address);
        this.endTime=(EditText)findViewById(R.id.edit_endTime);
        this.startTime=(EditText)findViewById(R.id.edit_StartTime);
        this.phoneNumber=(EditText)findViewById(R.id.edit_phonenumber);
        this.email=(EditText)findViewById(R.id.edit_Emailaddress);

    }
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;

        if(response!=null && response.statusCode == 400){
            DisplayMessage.displayToast(getApplicationContext(), "Post Not Posted");
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }

    }

    protected void onStart() {
        super.onStart();
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
    }
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    private void sendPost()
    {
        String title1 = title.getText().toString();
        String shortDesc1 = shortDesc.getText().toString();
        String longDesc1 = longDesc.getText().toString();
        String category1;
        String subcategory;
        String startDate1 = startDate.getText().toString();
        String endDate1= endDate.getText().toString();
        String startTime1=startTime.getText().toString();
        String endTime1= endTime.getText().toString();
        String address1 = address.getText().toString();
        String phoneNumber1 = phoneNumber.getText().toString();
        String email1 = email.getText().toString();

        Map<String,String> reqmap = new HashMap<String,String>();
        reqmap.put("title", title1);


        JSONObject reqobj = new JSONObject(reqmap);

        //MyJsonObjectRequest req = new MyJsonObjectRequest();
        //req.setTag();
        //mQueue.add(req);

    }

    @Override
    public void onResponse(JSONObject response) {
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    private boolean validateAdvertisement(String title1,String shortDesc1, String longDesc1,String category1, String subCategory1,String startDate1,
                                String endDate1, String startTime1, String endTime1,String address1,String phoneNumber1,
                                String email1) {
        boolean result = true;
        if (email1.isEmpty()) {
            email.setError(getString(R.string.email_blank));
            result = false;
        } else if (!Validator.validateEmail(email1)) {
            email.setError(getString(R.string.validate_email));
            result = false;
        } else if (title1.isEmpty()) {
            title.setError("title cannot be blank");
            result = false;
        } else if (shortDesc1.isEmpty()) {
            shortDesc.setError("Short Description cannot be blank");
            result = false;
        } else if (longDesc1.isEmpty()) {
            longDesc.setError("Long Description cannot be blank");
            result = false;
        } else if (category1.isEmpty()) {
            category.setError("Category cannot be blank");
            result = false;
        } else if (subCategory1.isEmpty()) {
            subcategory.setError(" Sub Category cannot be blank");
            result = false;
        } else if (startDate1.isEmpty()) {
            startDate.setError("Start Date cannot be blank");
            result = false;
        } else if (endDate1.isEmpty()) {
            endDate.setError("End Date cannot be blank");
            result = false;
        } else if (startTime1.isEmpty()) {
            startTime.setError("Start Time cannot be blank");
            result = false;
        } else if (endTime1.isEmpty()) {
            endTime.setError("End Time cannot be blank");
            result = false;
        } else if (address1.isEmpty()) {
            address.setError("Address cannot be blank");
            result = false;
        } else if (phoneNumber1.isEmpty()) {
            phoneNumber.setError("End Time cannot be blank");
            result = false;
        }

        return result;
    }}