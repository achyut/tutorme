package uta.edu.tutorme.activities;

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
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class BiddingPostActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "BIDDING_ACTIVITY";
    EditText amount;
    PostCard postCard;
    private RequestQueue mQueue;

    public void initialize(){
        this.amount = (EditText)findViewById(R.id.input_amount);
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

    public JSONObject getBiddingRequestObject(EditText amount){
        Map<String,String> obj = new HashMap<>();
        obj.put("sponsorprice",amount.getText().toString());
        return new JSONObject(obj);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if (response != null && response.statusCode == 400) {
            DisplayMessage.displayToast(getApplicationContext(), VollyUtils.getString(response, "message"));
        }
        else{
            DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
        }

    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            if(!response.getBoolean("error")){
                DisplayMessage.displayToast(getApplicationContext(), "Bid amount successfully placed!");
                Intent i = new Intent(getApplicationContext(),PostDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", postCard);
                i.putExtras(bundle);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
            else{
                DisplayMessage.displayToast(getApplicationContext(),response.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public  void setAmount(){
        MyJsonObjectRequest promoteRequest = new MyJsonObjectRequest(Request.Method
                .POST, Urls.getSponsoredURL(postCard.getId()),
                getBiddingRequestObject(amount), this, this);

        /*MyJsonObjectRequest postdetailrequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.getPostDetailURL(postid),
                null, this, this);*/

        promoteRequest.setTag(REQUEST_TAG);
        mQueue.add(promoteRequest);
    }

    public void doSubmit(View view){
        if (!this.amount.getText().toString().isEmpty()){
            setAmount();
        }else {
            this.amount.setError("Please enter a valid amount");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_post);
        postCard = (PostCard)getIntent().getSerializableExtra("post");
        initialize();
    }
}
