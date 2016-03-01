
package uta.edu.tutorme.activities;


import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.CategoryAdapter;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.services.CategoryService;
import uta.edu.tutorme.utils.DisplayMessage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {

    CategoryAdapter customAdapter = null;
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
        customAdapter = new CategoryAdapter(this,
                R.layout.activity_populate_category, categoryList);
        ListView listView = (ListView) findViewById(R.id.category_ListView);
        listView.setAdapter(customAdapter);

    }

    private void nextButtonClick() {
        Button nextButton = (Button) findViewById(R.id.next_category_button);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryList = customAdapter.getCategoryList();
                ArrayList<String> categoryNames = new ArrayList<>();

                for(int i=0;i<categoryList.size();i++){
                    Category category = categoryList.get(i);
                    if(category.isSelected()){
                        categoryNames.add(category.getName());
                    }
                }
                if(categoryNames.size()>0){

                    // Intent subCategoryIntent = new Intent(getApplicationContext(),SubcategoryActivity.class);
                   // subCategoryIntent.putStringArrayListExtra("categoryNames", categoryNames);
                   // startActivity(subCategoryIntent);


                    Intent homepage = new Intent(getApplicationContext(),HomepageActivity.class);
                    startActivity(homepage);
                }
                else{
                    DisplayMessage.displayToast(getApplicationContext(),"Please select at least one category!!");
                }

            /*Intent subCategoryIntent = new Intent(getApplicationContext(),SubcategoryActivity.class);
            subCategoryIntent.putStringArrayListExtra("categoryNames", categoryNames);
            startActivity(subCategoryIntent);
            */



            }
        });

    }

}
