package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

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


public class AddNewAdvActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {


    FloatingActionButton fabSubmit;
    public static final String REQUEST_TAG = "POST_ADDNEW";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;
    User user;

    EditText title;
    EditText shortDesc;
    EditText longDesc;
    EditText price;
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

        user = SharedPrefUtils.getUserFromSession(getApplicationContext());
        progressDialog = new ProgressDialog(this);
    }

    private void initialize() {
        this.title = (EditText)findViewById(R.id.edit_posttitle);
        this.shortDesc = (EditText)findViewById(R.id.edit_shortdes);
        this.longDesc = (EditText)findViewById(R.id.edit_longdes);
        this.price = (EditText)findViewById(R.id.edit_price);
        this.startDate = (EditText)findViewById(R.id.edit_startdate);
        this.endDate = (EditText)findViewById(R.id.edit_enddate);
        this.address=(EditText)findViewById(R.id.edit_address);
        this.endTime=(EditText)findViewById(R.id.edit_endtime);
        this.startTime=(EditText)findViewById(R.id.edit_starttime);
        this.phoneNumber=(EditText)findViewById(R.id.edit_phonenumber);
        this.email=(EditText)findViewById(R.id.edit_Emailaddress);

    }
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        //progressDialog.hide();
        if(response!=null && response.statusCode == 400){
            DisplayMessage.displayToast(getApplicationContext(),VollyUtils.getString(response,"message"));
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

    private JSONObject getPostJSONObject(){
        String title1 = title.getText().toString();
        String shortDesc1 = shortDesc.getText().toString();
        String longDesc1 = longDesc.getText().toString();
        String price1 = price.getText().toString();
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
        reqmap.put("shortdesc",shortDesc1);
        reqmap.put("longdesc",longDesc1);
        reqmap.put("price",price1);
        reqmap.put("category","6");
        reqmap.put("subcategory","1");
        reqmap.put("startdate",startDate1);
        reqmap.put("enddate",endDate1);
        reqmap.put("starttime",startTime1);
        reqmap.put("endtime",endTime1);
        reqmap.put("address",address1);
        reqmap.put("contact",phoneNumber1);
        reqmap.put("email",email1);
        reqmap.put("preferedcontact","Mobile");

        reqmap.put("created_by",String.valueOf(user.getId()));

        JSONObject reqobj = new JSONObject(reqmap);
        return reqobj;
    }
    private void sendPost()
    {
        progressDialog.setMessage("Adding new post.");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .POST, Urls.POSTS,
                getPostJSONObject(), this, this);


        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();
        DisplayMessage.displayToast(getApplicationContext(), "Successfully Added new post");
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }

}
