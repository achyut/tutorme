package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class AddSubCategoryActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    Map<Integer, Category> categoriesMap = new HashMap<Integer, Category>();
    public static final String REQUEST_TAG = "ADD_NEWCATEGORY";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;
    private EditText subcategory;
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category);
        this.subcategory = (EditText) findViewById(R.id.edit_addsubcategory);
        this.spinner1 = (Spinner) findViewById(R.id.spnner_addsubcategory);
        progressDialog = new ProgressDialog(this);
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        setDataInCategories();
    }


    public void setDataInCategories() {
        progressDialog.setMessage("Loading Category data.");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.ALL_CATEGORIES,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("result");

                    if (arr.length() > 0) {

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            int id = obj.getInt("id");
                            String catname = obj.getString("name");

                            JSONArray subcatarr = obj.getJSONArray("subcategory");
                            List<SubCategory> subCategories = new ArrayList<SubCategory>();
                            if (subcatarr.length() > 0) {
                                for (int j = 0; j < subcatarr.length(); j++) {
                                    JSONObject subcatobj = subcatarr.getJSONObject(j);
                                    int subcatid = subcatobj.getInt("id");
                                    String subcatname = subcatobj.getString("name");
                                    SubCategory subcat = new SubCategory(subcatid, subcatname);
                                    subCategories.add(subcat);
                                }
                            }
                            Category cat = new Category(id, catname, subCategories);
                            categoriesMap.put(i, cat);
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

    public void addItemsOnSpinner1() {

        List<String> list = new ArrayList<String>();
        for (Map.Entry<Integer, Category> entry : categoriesMap.entrySet()) {
            Integer key = entry.getKey();
            Category cat = entry.getValue();
            list.add(cat.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }

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

    public void doAddSubCategory(View view){
        String newCategory = subcategory.getText().toString().trim();
        int position = spinner1.getSelectedItemPosition();
        Category cat = categoriesMap.get(position);
        String categoryId = String.valueOf(cat.getId());
        JSONObject requestObj = getRequestObject(categoryId,newCategory);

        if(validateAddnewCategory(newCategory)){
            progressDialog.setMessage("Adding sub category. Please wait...");
            progressDialog.show();
            MyJsonObjectRequest createNewSubCategoryRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.SUBCATEGORIES,
                    requestObj, this, this);
            createNewSubCategoryRequest.setTag(REQUEST_TAG);
            mQueue.add(createNewSubCategoryRequest);
        }
    }

    private boolean validateAddnewCategory(String newCategory) {
        if(newCategory.trim().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void onResponse(JSONObject response) {
        progressDialog.hide();
        try {
            if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(), "New sub category added.");
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

    private JSONObject getRequestObject(String categoryid,String newcategory){
        Map<String,String> obj = new HashMap<>();
        obj.put("category", categoryid);
        obj.put("name", newcategory);
        return new JSONObject(obj);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressDialog.hide();
        NetworkResponse response = error.networkResponse;
        if(response!=null && response.statusCode == 400){
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(),VollyUtils.getString(response, "message"));
        }
    }
}
