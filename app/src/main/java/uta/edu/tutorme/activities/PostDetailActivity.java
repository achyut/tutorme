package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class PostDetailActivity extends AppCompatActivity implements  Response.Listener<JSONObject>,
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
            String shortdesc = request.getString("title");
            String longdesc = request.getString("title");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        postCard = (PostCard)getIntent().getSerializableExtra("post");
        progressDialog = new ProgressDialog(this);
        //DisplayMessage.displayToast(getApplicationContext(), "Position " + postCard.getTitle());

        postTitle =  (TextView)findViewById(R.id.exiting_edit_posttitle);
        postAddress =  (TextView)findViewById(R.id.exiting_edit_address);
        postShortDesc =  (TextView)findViewById(R.id.exiting_edit_shortdes);
        postLongDesc =  (TextView)findViewById(R.id.exiting_edit_longdes);
        postEmail =  (TextView)findViewById(R.id.exiting_edit_Emailaddress);
        postContact =  (TextView)findViewById(R.id.exiting_edit_phonenumber);
        postStartDate =  (TextView)findViewById(R.id.exiting_edit_startdate);
        postEndDate =  (TextView)findViewById(R.id.exiting_edit_enddate);
        postStartTime =  (TextView)findViewById(R.id.exiting_edit_starttime);
        postEndTime =  (TextView)findViewById(R.id.exiting_edit_endtime);
        postCategory =  (TextView)findViewById(R.id.exiting_edt_category);
        postSubCategory =  (TextView)findViewById(R.id.exiting_edt_subcategory);
        postPrice =  (TextView)findViewById(R.id.exiting_edit_price);


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
/*
=======
        post.setTitle("Piano Class Available");
        post.setAddress("University Center, Arlington Texas");
      //  post.setCategory("Music");
        post.setContact("342554353");
        //post.setCreated_by();
        post.setEmail("ap@gmail.com");
        post.setShortdesc("Basic piano learning classes");
        post.setLongdesc("In this class, you will learn the basics and the notes for the piano");
        post.setStartdate(calendarStartDate.getTime());
        post.setEnddate(calendarEndDate.getTime());
        post.setStarttime(calendarStartTime.getTime());
        post.setEndtime(calendarEndTime.getTime());
        Category category = new Category();
        category.setName("Music");
        SubCategory subCategory = new SubCategory("Piano", true);
        subCategory.setName("Piano");
        post.setCategory(category);
        post.setSubcategory(subCategory);
/***** Dummy Data End  *****/

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
        postPrice.setText("$"+post.getPrice()+"/hr");

    }
}
