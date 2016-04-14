package uta.edu.tutorme.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

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

    public void doDeletePost(View view){
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete this post?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePost();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void doUpdatePost(View view){
        Intent i = new Intent(getApplicationContext(),UpdatePostDetailActivity.class);
        startActivity(i);
    }

    public void deletePost() {
        progressDialog.setMessage("Deleting post. Please wait...");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getDeletePostURL(postCard.getId()),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.hide();
                    if(!response.getBoolean("error")){
                        DisplayMessage.displayToast(getApplicationContext(), "Post deleted successfully.");
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

    public void doCall(View view){
        String phonenumber = postContact.getText().toString().trim().toLowerCase();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phonenumber));
        startActivity(intent);
    }

    public void doMessage(View view){
        String phonenumber = postContact.getText().toString().trim().toLowerCase();
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"+phonenumber));
        startActivity(sendIntent);
    }

    public void doEmail(View view){
        String title = postTitle.getText().toString().trim().toLowerCase();
        String email = postEmail.getText().toString().trim().toLowerCase();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:"
                + email
                + "?subject= Want to take the course "+title + "&body= Hello, I would like to take" +
                " the course that has been posted in tutor me. Thank you.");

        intent.setData(data);
        startActivity(intent);
    }

    public void doOpenMap(View view){
        String address = postAddress.getText().toString().trim().toLowerCase();
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void doBidding(View view){
        Intent bidIntent = new Intent(getApplicationContext(), BiddingPostActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", postCard);
        bidIntent.putExtras(bundle);
        startActivity(bidIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        SharedPrefUtils.checkIfLoggedIn(getApplicationContext());
        postCard = (PostCard)getIntent().getSerializableExtra("post");
        progressDialog = new ProgressDialog(this);

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
