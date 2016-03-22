package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.User;

public class UpdatePostDetailActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

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
    private Spinner spinner1;
    private Spinner spinner2;

    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post_detail);
    }

    private void initialize() {
        this.title = (EditText) findViewById(R.id.edit_posttitle_update);
        this.shortDesc = (EditText) findViewById(R.id.edit_shortdes_update);
        this.longDesc = (EditText) findViewById(R.id.edit_longdes_update);
        this.price = (EditText) findViewById(R.id.edit_price_update);
        this.startDate = (EditText) findViewById(R.id.edit_startdate_update);
        this.endDate = (EditText) findViewById(R.id.edit_enddate_update);
        this.address = (EditText) findViewById(R.id.edit_address_update);
        this.endTime = (EditText) findViewById(R.id.edit_endtime_update);
        this.startTime = (EditText) findViewById(R.id.edit_starttime_update);
        this.phoneNumber = (EditText) findViewById(R.id.edit_phonenumber_update);
        this.email = (EditText) findViewById(R.id.edit_Emailaddress_update);
        this.spinner1 = (Spinner) findViewById(R.id.spinner1_update);
        this.spinner2 = (Spinner) findViewById(R.id.spinner2_update);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        
    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
