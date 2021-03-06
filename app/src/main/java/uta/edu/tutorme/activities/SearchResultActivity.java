package uta.edu.tutorme.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.PostCardAdapter;
import uta.edu.tutorme.beans.FilterRequest;
import uta.edu.tutorme.models.PostCard;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;
import uta.edu.tutorme.volly.VollyUtils;

public class SearchResultActivity extends AppCompatActivity {

    PostCardAdapter adapter;
    RecyclerView list;
    private TextView emptyView;
    Gson gson;
    private RequestQueue mQueue;
    ProgressDialog progressDialog;
    public static final String REQUEST_TAG = "SEARCH_RESULT_REQUEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        emptyView = (TextView)findViewById(R.id.empty_view);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        adapter = new PostCardAdapter();
        gson = new Gson();
        list = (RecyclerView) findViewById(R.id.search_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(lm);
        list.setAdapter(adapter);

        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();

        FilterRequest filterRequest = (FilterRequest)getIntent().getSerializableExtra("filterrequst");

        progressDialog = new ProgressDialog(this);
        displaySearchResult(filterRequest);
    }

    public JSONObject getFilterRequestObject(FilterRequest request){
        Map<String,String> req = new HashMap<>();
        req.put("keyword",request.getKeyword());
        req.put("rating",request.getRating());
        req.put("category",request.getCategory());
        req.put("subcategory",request.getSubcategory());
        req.put("price-from",request.getPriceFrom());
        req.put("price-to",request.getPriceTo());
        return new JSONObject(req);
    }

    public void displaySearchResult(FilterRequest searchQuery){
        progressDialog.setMessage("Loading search results.");
        progressDialog.show();

        MyJsonObjectRequest searchRequest = new MyJsonObjectRequest(Request.Method
                .POST, Urls.SEARCH,
                getFilterRequestObject(searchQuery), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("result");
                    if (arr.length() > 0) {
                        list.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            int id = obj.getInt("id");
                            String title = obj.getString("title");
                            double price = obj.getDouble("price");
                            double rating = obj.getDouble("rating");
                            double sponsored = obj.getDouble("sponsored");
                            String shortdesc = obj.getString("shortdesc");
                            PostCard card = new PostCard(id,title,price,rating,shortdesc,sponsored);
                            adapter.addCard(card);
                            list.scrollToPosition(0);
                        }
                    }
                    else{
                        list.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.hide();
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
                    DisplayMessage.displayToast(getApplicationContext(), "OOPS!! Some error occured ");
                }
            }
        });
        searchRequest.setTag(REQUEST_TAG);
        mQueue.add(searchRequest);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }
}
