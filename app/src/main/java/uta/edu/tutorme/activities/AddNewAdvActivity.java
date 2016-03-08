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

}
