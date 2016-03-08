package uta.edu.tutorme.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;

import uta.edu.tutorme.utils.Validator;

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
    private Spinner spinner1;
    private Spinner spinner2;

    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;


    Map<Integer,Category> categoriesMap = new HashMap<Integer,Category>();

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
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        setDataInCategories();
        addListenerOnSpinner1ItemSelection();
    }

    public void setDataInCategories(){
        progressDialog.setMessage("Loading Category data.");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.ALL_CATEGORIES,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("result");

                    if(arr.length()>0) {

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            int id = obj.getInt("id");
                            String catname = obj.getString("name");

                            JSONArray subcatarr = obj.getJSONArray("subcategory");
                            List<SubCategory> subCategories = new ArrayList<SubCategory>();
                            if(subcatarr.length()>0){
                                for(int j=0;j<subcatarr.length();j++){
                                    JSONObject subcatobj = subcatarr.getJSONObject(j);
                                    int subcatid = subcatobj.getInt("id");
                                    String subcatname = subcatobj.getString("name");
                                    SubCategory subcat = new SubCategory(subcatid,subcatname);
                                    subCategories.add(subcat);
                                }
                            }
                            Category cat = new Category(id,catname,subCategories);
                            categoriesMap.put(i,cat);
                        }
                    }
                    addItemsOnSpinner1();
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


    public void addListenerOnSpinner1ItemSelection() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addItemsOnSpinner2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner1() {

        List<String> list = new ArrayList<String>();
        for (Map.Entry<Integer,Category> entry : categoriesMap.entrySet()) {
            Integer key = entry.getKey();
            Category cat = entry.getValue();
            list.add(cat.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2(int position) {
        List<String> list = new ArrayList<String>();
        Category cat = categoriesMap.get(position);
        for(SubCategory subcat:cat.getSubCategories()){
            list.add(subcat.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
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
        this.spinner1 = (Spinner) findViewById(R.id.spinner1);
        this.spinner2 = (Spinner) findViewById(R.id.spinner2);
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
        int pos1 = spinner1.getSelectedItemPosition();
        Category cat = categoriesMap.get(pos1);
        int pos2 = spinner2.getSelectedItemPosition();

        reqmap.put("category",String.valueOf(cat.getId()));
        reqmap.put("subcategory",String.valueOf(cat.getSubCategories().get(pos2).getId()));
        reqmap.put("startdate",startDate1);
        reqmap.put("enddate",endDate1);
        reqmap.put("starttime",startTime1);
        reqmap.put("endtime",endTime1);
        reqmap.put("address",address1);
        reqmap.put("contact",phoneNumber1);
        reqmap.put("email",email1);
        reqmap.put("preferedcontact", "Mobile");

        reqmap.put("created_by",String.valueOf(user.getId()));

        JSONObject reqobj = new JSONObject(reqmap);
        return reqobj;
    }
    private void sendPost()
    {
        String title1 = title.getText().toString();
        String shortDesc1 = shortDesc.getText().toString();
        String longDesc1 = longDesc.getText().toString();
        String price1 = price.getText().toString();
        String startDate1 = startDate.getText().toString();
        String endDate1= endDate.getText().toString();
        String startTime1=startTime.getText().toString();
        String endTime1= endTime.getText().toString();
        String address1 = address.getText().toString();
        String phoneNumber1 = phoneNumber.getText().toString();
        String email1 = email.getText().toString();

        if(validateAdvertisement(title1, shortDesc1,longDesc1,startDate1,endDate1,
                startTime1,endTime1,address1,phoneNumber1,email1))
        {
        progressDialog.setMessage("Adding new post.");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .POST, Urls.POSTS,
                getPostJSONObject(), this, this);


        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);
    }
    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();
        DisplayMessage.displayToast(getApplicationContext(), "Successfully Added new post");
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    private boolean validateAdvertisement(String title1,String shortDesc1, String longDesc1,String startDate1,
                                String endDate1, String startTime1, String endTime1,String address1,String phoneNumber1,
                                String email1) {
        boolean result = true;
        if (email1.isEmpty()) {
            email.setError(getString(R.string.email_blank));
            result = false;
        }
        if (!Validator.validateEmail(email1)) {
            email.setError(getString(R.string.validate_email));
            result = false;
        }
        if (title1.isEmpty()) {
            title.setError("title cannot be blank");
            result = false;
        }
        if (shortDesc1.isEmpty()) {
            shortDesc.setError("Short Description cannot be blank");
            result = false;
        }
        if (longDesc1.isEmpty()) {
            longDesc.setError("Long Description cannot be blank");
            result = false;
        }
        if (startDate1.isEmpty()) {
            startDate.setError("Start Date cannot be blank");
            result = false;
        }
        if (endDate1.isEmpty()) {
            endDate.setError("End Date cannot be blank");
            result = false;
        }
        if (startTime1.isEmpty()) {
            startTime.setError("Start Time cannot be blank");
            result = false;
        }
        if (endTime1.isEmpty()) {
            endTime.setError("End Time cannot be blank");
            result = false;
        }
        if (address1.isEmpty()) {
            address.setError("Address cannot be blank");
            result = false;
        }
        if (phoneNumber1.isEmpty()) {
            phoneNumber.setError("End Time cannot be blank");
            result = false;
        }
        return result;
        }
}
