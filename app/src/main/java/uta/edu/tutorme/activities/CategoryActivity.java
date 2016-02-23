
package uta.edu.tutorme.activities;


import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.MyCustomAdapter;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.services.CategoryService;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CategoryActivity extends Activity {

    MyCustomAdapter customAdapter = null;
    CategoryService service;
    List<Category> categoryList = new ArrayList<Category>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");
        CategoryRepository repository = new CategoryRepository();
        service = new CategoryService(repository);
        displayCategoryListView();
        nextButtonClick();
    }

    private void displayCategoryListView() {
        categoryList = service.findAll();
        customAdapter = new MyCustomAdapter(this,
                R.layout.activity_populate_category, categoryList);
        ListView listView = (ListView) findViewById(R.id.category_ListView);
        listView.setAdapter(customAdapter);
    }

    private void nextButtonClick() {
        Button nextButton = (Button) findViewById(R.id.next_category_button);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            ArrayList<String> categoryNames = new ArrayList<String>();
            for(int i=0;i<categoryList.size();i++){
                Category category = categoryList.get(i);
                if(category.isSelected()){
                    categoryNames.add(category.getName());
                }
            }

            /*Intent subCategoryIntent = new Intent(getApplicationContext(),SubcategoryActivity.class);
            subCategoryIntent.putStringArrayListExtra("categoryNames", categoryNames);
            startActivity(subCategoryIntent);
            */

            }
        });

    }

}
