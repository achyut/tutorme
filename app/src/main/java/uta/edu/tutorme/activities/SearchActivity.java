package uta.edu.tutorme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import uta.edu.tutorme.R;

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

    }
}
