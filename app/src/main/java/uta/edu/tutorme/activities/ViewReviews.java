package uta.edu.tutorme.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.ReviewAdapter;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.Review;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class ViewReviews extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    public static final String REQUEST_TAG = "VIEW_REVIEWS";
    private RequestQueue mQueue;
    ProgressDialog progressDialog;
    PostCard postCard;
    User user;
    ReviewAdapter adapter;
    RecyclerView list;
    FloatingActionButton fabSubmit;
    TextView emptyText;
    Dialog dialog;

    private JSONObject getReviewObj(String review,String rating){
        Map<String, String> reqmap = new HashMap<String, String>();
        reqmap.put("review",review);
        reqmap.put("post",String.valueOf(postCard.getId()));
        reqmap.put("rating",rating);
        reqmap.put("created_by",String.valueOf(user.getId()));
        JSONObject reqobj = new JSONObject(reqmap);
        return reqobj;
    }

    private void postNewReview(){
        EditText review = (EditText)dialog.findViewById(R.id.editText_post_review);
        RatingBar bar = (RatingBar) dialog.findViewById(R.id.review_rating);
        String reviewstr = review.getText().toString().trim();
        String rating = String.valueOf(bar.getRating());
        if(reviewstr.isEmpty()){
            review.setError("Please enter review");
            return;
        }
        if(bar.getRating()==0.0){
            review.setError("Please enter rating");
            return;
        }

        progressDialog.setMessage("Adding new review. Please wait...");
        progressDialog.show();
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .POST, Urls.REVIEWS,
                getReviewObj(reviewstr,rating), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.hide();
                    dialog.hide();
                    if(!response.getBoolean("error")){
                        DisplayMessage.displayToast(getApplicationContext(), "Review successfully Added.");
                        loadPostReviews(postCard.getId());
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
                dialog.hide();
            }
        });
        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);
    }
    private void openAddReview(){

        dialog = new Dialog(ViewReviews.this);
        dialog.setContentView(R.layout.add_review);
        dialog.setTitle("Add new review..");
        Button btn = (Button) dialog.findViewById(R.id.review_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNewReview();
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_reviews);

        emptyText = (TextView) findViewById(R.id.empty_text);
        fabSubmit = (FloatingActionButton) findViewById(R.id.add_review);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddReview();
            }

        });

        LinearLayoutManager lm = new LinearLayoutManager(this);
        adapter = new ReviewAdapter();

        list = (RecyclerView) findViewById(R.id.review_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(lm);
        list.setAdapter(adapter);

        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        postCard = (PostCard)getIntent().getSerializableExtra("post");
        progressDialog = new ProgressDialog(this);
        user = SharedPrefUtils.getUserFromSession(getApplicationContext());
        loadPostReviews(postCard.getId());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    private void loadPostReviews(int postid){
        adapter.emptyCards();
        progressDialog.setMessage("Loading post reviews...");
        progressDialog.show();
        MyJsonObjectRequest reviewloadrequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getPostReviewsURL(postid),
                null, this, this);

        reviewloadrequest.setTag(REQUEST_TAG);
        mQueue.add(reviewloadrequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        //progressDialog.hide();
        if (response != null && response.statusCode == 400) {
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        } else {
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray arr = response.getJSONArray("result");

            if(arr.length()>0) {
                emptyText.setVisibility(View.INVISIBLE);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String review = obj.getString("review");
                    String rating = obj.getString("rating");

                    Review card = new Review(review,rating);
                    card.setPostid(postCard.getId());
                    card.setUserid(user.getId());
                    adapter.addCard(card);
                }
                list.scrollToPosition(0);
            }
            else{
                emptyText.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.hide();
    }
}
