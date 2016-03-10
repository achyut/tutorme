package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.Post;
import uta.edu.tutorme.models.PostCard;
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
        progressDialog.setMessage("Loading your posts...");
        progressDialog.show();
        MyJsonObjectRequest postdetailrequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getPostDetailURL(postid),
                null, this, this);

        postdetailrequest.setTag(REQUEST_TAG);
        mQueue.add(postdetailrequest);
    }

    private Post getPostFromUrl(JSONObject request){

        //***** Dummy Supporters  Start *****//*
        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.set(Calendar.DATE, 10);
        calendarStartDate.set(Calendar.MONTH, 9);
        calendarStartDate.set(Calendar.YEAR, 2016);

        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.set(Calendar.DATE,17);
        calendarEndDate.set(Calendar.MONTH, 12);
        calendarEndDate.set(Calendar.YEAR,2016);

        Calendar calendarStartTime = Calendar.getInstance();
        calendarStartTime.set(Calendar.HOUR,9);
        calendarStartTime.set(Calendar.MINUTE, 00);
        calendarStartTime.set(Calendar.SECOND,00);

        Calendar calendarEndTime = Calendar.getInstance();
        calendarEndTime.set(Calendar.HOUR,10);
        calendarEndTime.set(Calendar.MINUTE, 30);
        calendarEndTime.set(Calendar.SECOND,00);


/***** Dummy Supporters End  *****/

        Post post = new Post();
        try {


            String title = request.getString("title");
            String shortdesc = request.getString("title");
            String longdesc = request.getString("title");
            String price = String.valueOf(request.getDouble("price"));
            String rating = String.valueOf(request.getDouble("rating"));
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
            //post.setCreated_by();
            post.setEmail(email);
            post.setShortdesc(shortdesc);
            post.setLongdesc(longdesc);
            post.setStartdate(calendarStartDate.getTime());
            post.setEnddate(calendarEndDate.getTime());
            post.setStarttime(calendarStartTime.getTime());
            post.setEndtime(calendarEndTime.getTime());


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
    }
}
