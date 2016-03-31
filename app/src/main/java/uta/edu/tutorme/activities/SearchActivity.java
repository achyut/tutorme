package uta.edu.tutorme.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.beans.FilterElementRange;
import uta.edu.tutorme.beans.FilterElementSimple;
import uta.edu.tutorme.beans.FilterRequest;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
