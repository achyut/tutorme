package uta.edu.tutorme.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import android.widget.EditText;
import android.widget.Spinner;

import uta.edu.tutorme.R;
import uta.edu.tutorme.beans.FilterRequest;

public class SearchActivity extends AppCompatActivity {

    EditText keywords;
    EditText priceFrom;
    EditText priceTo;
    Spinner categories;
    Spinner subcategories;
    EditText rating;

    private void initialize(){
        keywords = (EditText)findViewById(R.id.input_search_keywords);
        priceFrom = (EditText)findViewById(R.id.input_price_from);
        priceTo = (EditText)findViewById(R.id.input_price_to);
        categories = (Spinner)findViewById(R.id.spinner_categories);
        subcategories = (Spinner)findViewById(R.id.spinner_subcategories);
        rating =  (EditText)findViewById(R.id.input_rating);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initialize();

    }

    public void doSearch(View view){
        FilterRequest filterRequest = new FilterRequest();
        FilterElementSimple simple1 = new FilterElementSimple("keyword","advanced");
        FilterElementSimple simple2 = new FilterElementSimple("rating","3.0");
        FilterElementSimple simple3 = new FilterElementSimple("category","music");
        FilterElementSimple simple4 = new FilterElementSimple("subcategory","guitar");
        FilterElementRange range1 = new FilterElementRange("10","100","price");

        List<FilterElementSimple> simpleHints = new ArrayList<FilterElementSimple>();
        simpleHints.add(simple1);
        simpleHints.add(simple2);
        simpleHints.add(simple3);
        simpleHints.add(simple4);
        List<FilterElementRange> rangeHints = new ArrayList<FilterElementRange>();
        rangeHints.add(range1);

        filterRequest.setSimpleHints(simpleHints);
        filterRequest.setRangeHints(rangeHints);

        Intent intent = new Intent(getApplicationContext(),SearchResultActivity.class);
        intent.putExtra("filterrequst",filterRequest);
        startActivity(intent);
    }
}
