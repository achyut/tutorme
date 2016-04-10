package uta.edu.tutorme.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

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
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class UpdatePostDetailActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    TextView postTitle;
    TextView postAddress;
    TextView postShortDesc;
    TextView postLongDesc;
    TextView postEmail;
    TextView postContact;
    TextView postStartDate;
    TextView postEndDate;
    TextView postStartTime;
    TextView postEndTime;
    TextView postCategory;
    TextView postSubCategory;
    TextView postPrice;

    PostCard postCard;
    User user;
    private RequestQueue mQueue;
    ProgressDialog progressDialog;
    public static final String REQUEST_TAG = "POST_DETAIL_ACTIVITY";

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }


    private void loadPostFromUrl(int postid){
        progressDialog.setMessage("Loading your post...");
        progressDialog.show();
        MyJsonObjectRequest postdetailrequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getPostDetailURL(postid),
                null, this, this);

        postdetailrequest.setTag(REQUEST_TAG);
        mQueue.add(postdetailrequest);
    }

    private Post getPostFromUrl(JSONObject request){
        Post post = new Post();
        try {
            String title = request.getString("title");
            String shortdesc = request.getString("shortdesc");
            String longdesc = request.getString("longdesc");
            double price = request.getDouble("price");
            double rating = request.getDouble("rating");
            String startdate = request.getString("startdate");
            String enddate = request.getString("enddate");
            String starttime = request.getString("starttime");
            String endtime = request.getString("endtime");
            String address = request.getString("address");
            String contact = request.getString("contact");
            String email = request.getString("email");
            String preferredcontact = request.getString("preferedcontact");
            String category = request.getJSONObject("category").getString("name");
            String subcategory = request.getJSONObject("subcategory").getString("name");

            post.setTitle(title);
            post.setAddress(address);
            post.setCategory(new Category(category, false));
            post.setContact(contact);
            post.setEmail(email);
            post.setShortdesc(shortdesc);
            post.setLongdesc(longdesc);
            post.setStartdate(startdate);
            post.setEnddate(enddate);
            post.setStarttime(starttime);
            post.setEndtime(endtime);
            post.setCategory(new Category(category, false));
            post.setSubcategory(new SubCategory(subcategory,false));
            post.setPrice(price);
            post.setRating(rating);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return post;
    }

    public void doUpdate(View view){
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Update this post?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UpdatePost();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private JSONObject getPostJSONObject() {


        String title1 = postTitle.getText().toString();
        String shortDesc1 = postShortDesc.getText().toString();
        String longDesc1 = postLongDesc.getText().toString();
        String price1 = postPrice.getText().toString();
        String category1 = postCategory.getText().toString();
        String subcategory = postSubCategory.getText().toString();
        String startDate1 = postStartDate.getText().toString();
        String endDate1 = postEndDate.getText().toString();
        String startTime1 = postStartTime.getText().toString();
        String endTime1 = postEndTime.getText().toString();
        String address1 = postAddress.getText().toString();
        String phoneNumber1 = postContact.getText().toString();
        String email1 = postEmail.getText().toString();

        Map<String, String> reqmap = new HashMap<String, String>();
        reqmap.put("title", title1);
        reqmap.put("shortdesc", shortDesc1);
        reqmap.put("longdesc", longDesc1);
        reqmap.put("price", price1);

        reqmap.put("category",category1);
        reqmap.put("subcategory",subcategory);


        /*
        int pos1 = spinner1.getSelectedItemPosition();
        Category cat = categoriesMap.get(pos1);
        int pos2 = spinner2.getSelectedItemPosition();

        reqmap.put("category", String.valueOf(cat.getId()));
        reqmap.put("subcategory", String.valueOf(cat.getSubCategories().get(pos2).getId()));

        */
        reqmap.put("startdate", startDate1);
        reqmap.put("enddate", endDate1);
        reqmap.put("starttime", startTime1);
        reqmap.put("endtime", endTime1);
        reqmap.put("address", address1);
        reqmap.put("contact", phoneNumber1);
        reqmap.put("email", email1);
        reqmap.put("preferedcontact", "Mobile");

        reqmap.put("created_by", String.valueOf(user.getId()));

        JSONObject reqobj = new JSONObject(reqmap);
        return reqobj;
    }

    public void UpdatePost() {
        progressDialog.setMessage("Updating post. Please wait...");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getUpdatePostURL(postCard.getId()),
                getPostJSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.hide();
                    if(!response.getBoolean("error")){
                        DisplayMessage.displayToast(getApplicationContext(), "Post Updated successfully.");
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
        }, new Response.ErrorListener() {
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
        });
        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post_detail);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        postCard = (PostCard)getIntent().getSerializableExtra("post");
        progressDialog = new ProgressDialog(this);
        user = SharedPrefUtils.getUserFromSession(getApplicationContext());

        postTitle =  (TextView)findViewById(R.id.edit_posttitle);
        postAddress =  (TextView)findViewById(R.id.edit_address);
        postShortDesc =  (TextView)findViewById(R.id.edit_shortdes);
        postLongDesc =  (TextView)findViewById(R.id.edit_longdes);
        postEmail =  (TextView)findViewById(R.id.edit_Emailaddress);
        postContact =  (TextView)findViewById(R.id.edit_phonenumber);
        postStartDate =  (TextView)findViewById(R.id.edit_startdate);
        postEndDate =  (TextView)findViewById(R.id.edit_enddate);
        postStartTime =  (TextView)findViewById(R.id.edit_starttime);
        postEndTime =  (TextView)findViewById(R.id.edit_endtime);
        postCategory =  (TextView)findViewById(R.id.edit_txt_category);
        postSubCategory =  (TextView)findViewById(R.id.edit_txt_subcategory);
        postPrice =  (TextView)findViewById(R.id.edit_price);


        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();

        loadPostFromUrl(postCard.getId());

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

        Post post = getPostFromUrl(response);

        postTitle.setText(post.getTitle());
        postAddress.setText(post.getAddress());
        postShortDesc.setText(post.getShortdesc());
        postLongDesc.setText(post.getLongdesc());
        postEmail.setText(post.getEmail());
        postContact.setText(post.getContact());
        postStartDate.setText(post.getStartdate().toString());
        postEndDate.setText(post.getEnddate().toString());
        postStartTime.setText(post.getStarttime().toString());
        postEndTime.setText(post.getEndtime().toString());
        postCategory.setText(post.getCategory().getName());
        postSubCategory.setText(post.getSubcategory().getName());
        postPrice.setText(String.valueOf(post.getPrice()));

    }
}
