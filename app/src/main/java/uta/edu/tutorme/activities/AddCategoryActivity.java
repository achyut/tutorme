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
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class AddCategoryActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    EditText edit_category;
    public static final String REQUEST_TAG = "ADD_NEWCATEGORY";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        edit_category = (EditText)findViewById(R.id.edit_addcategory);
        progressDialog = new ProgressDialog(this);
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

    public void doAddCategory(View view){
        String newCategory = edit_category.getText().toString().trim();
        JSONObject requestObj = getRequestObject(newCategory);
        if(validateAddnewCategory(newCategory)){
            progressDialog.setMessage("Adding category. Please wait...");
            progressDialog.show();
            MyJsonObjectRequest createNewCategoryRequest = new MyJsonObjectRequest(Request.Method
                    .POST, Urls.CATEGORIES,
                    getRequestObject(newCategory), this, this);
            createNewCategoryRequest.setTag(REQUEST_TAG);
            mQueue.add(createNewCategoryRequest);
        }
        else{
            edit_category.setError("Please enter category");
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
                DisplayMessage.displayToast(getApplicationContext(), "New category added.");
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

    private JSONObject getRequestObject(String newcategory){
        Map<String,String> obj = new HashMap<>();
        obj.put("name",newcategory);
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
