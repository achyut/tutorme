package uta.edu.tutorme.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uta.edu.tutorme.R;
import uta.edu.tutorme.beans.FilterRequest;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.utils.Urls;
import uta.edu.tutorme.volly.MyJsonObjectRequest;
import uta.edu.tutorme.volly.VolleyRequestQueue;

public class SearchActivity extends AppCompatActivity {

    public static final String REQUEST_TAG = "POST_ADDNEW";
    EditText keywords;
    EditText priceFrom;
    EditText priceTo;
    Spinner categories;
    Spinner subcategories;
    EditText rating;
    private RequestQueue mQueue;


    Map<Integer, Category> categoriesMap = new TreeMap<Integer, Category>();

    private void initialize(){
        keywords = (EditText)findViewById(R.id.input_search_keywords);
        priceFrom = (EditText)findViewById(R.id.input_price_from);
        priceTo = (EditText)findViewById(R.id.input_price_to);
        categories = (Spinner)findViewById(R.id.spinner_categories);
        subcategories = (Spinner)findViewById(R.id.spinner_subcategories);
        rating =  (EditText)findViewById(R.id.input_rating);
    }

    public void setDataInCategories() {
        MyJsonObjectRequest postRequest = new MyJsonObjectRequest(Request.Method
                .GET, Urls.ALL_CATEGORIES,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("result");
                    SubCategory subcat2 = new SubCategory(0,"");
                    List<SubCategory> subcatlisfirst = new ArrayList<SubCategory>();
                    subcatlisfirst.add(subcat2);
                    Category cat = new Category(0,"",subcatlisfirst);
                    categoriesMap.put(0, cat);
                    if (arr.length() > 0) {

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            int id = obj.getInt("id");
                            String catname = obj.getString("name");

                            JSONArray subcatarr = obj.getJSONArray("subcategory");
                            List<SubCategory> subCategories = new ArrayList<SubCategory>();
                            SubCategory subcat = new SubCategory(0,"");
                            subCategories.add(subcat);
                            if (subcatarr.length() > 0) {
                                for (int j = 0; j < subcatarr.length(); j++) {
                                    JSONObject subcatobj = subcatarr.getJSONObject(j);
                                    int subcatid = subcatobj.getInt("id");
                                    String subcatname = subcatobj.getString("name");
                                    SubCategory subcat1 = new SubCategory(subcatid, subcatname);
                                    subCategories.add(subcat1);

                                }
                            }
                            Category cat1 = new Category(id, catname, subCategories);
                            categoriesMap.put(i+1, cat1);
                        }
                    }
                    addItemsOnSpinner1();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        postRequest.setTag(REQUEST_TAG);
        mQueue.add(postRequest);
    }


    public void addListenerOnSpinner1ItemSelection() {
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        for (Map.Entry<Integer, Category> entry : categoriesMap.entrySet()) {
            Integer key = entry.getKey();
            Category cat = entry.getValue();
            list.add(cat.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(dataAdapter);
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2(int position) {
        List<String> list = new ArrayList<String>();
        Category cat = categoriesMap.get(position);
        for (SubCategory subcat : cat.getSubCategories()) {
            list.add(subcat.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategories.setAdapter(dataAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initialize();
        mQueue = VolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        setDataInCategories();
        addListenerOnSpinner1ItemSelection();
    }

    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    public void doSearch(View view){
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setKeyword(keywords.getText().toString().toLowerCase());
        filterRequest.setRating(rating.getText().toString().toLowerCase());
        int catpos = categories.getSelectedItemPosition();
        String category = "";
        if(catpos>1){
            category = String.valueOf(catpos-1);
        }
        filterRequest.setCategory(category);
        int subcatpos = subcategories.getSelectedItemPosition();
        String subcategory = "";
        if(subcatpos>1){
            subcategory = String.valueOf(subcatpos-1);
        }
        filterRequest.setSubcategory(String.valueOf(subcategory));
        filterRequest.setPriceFrom(priceFrom.getText().toString().toLowerCase());
        filterRequest.setPriceTo(priceTo.getText().toString().toLowerCase());
        Intent intent = new Intent(getApplicationContext(),SearchResultActivity.class);
        intent.putExtra("filterrequst",filterRequest);
        startActivity(intent);
    }
}
