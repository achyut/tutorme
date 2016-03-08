
package uta.edu.tutorme.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.CategoryAdapter;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.User;
import uta.edu.tutorme.utils.DisplayMessage;
import uta.edu.tutorme.utils.SharedPrefUtils;

public class CategoryActivity extends Activity {

    CategoryAdapter customAdapter = null;
    List<Category> categoryList = new ArrayList<Category>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");
        displayCategoryListView();
        nextButtonClick();
    }

    private void displayCategoryListView() {
        customAdapter = new CategoryAdapter(this,
                R.layout.activity_populate_category, categoryList);
        ListView listView = (ListView) findViewById(R.id.category_ListView);
        listView.setAdapter(customAdapter);

    }

    private void setCategoriesForUser(List<Category> categories){
        User user = SharedPrefUtils.getUserFromSession(getApplicationContext());
        user.setCategories(categories);
        // write code to store user categories
    }

    private void nextButtonClick() {
        Button nextButton = (Button) findViewById(R.id.next_category_button);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryList = customAdapter.getCategoryList();
                ArrayList<Category> categoryNames = new ArrayList<>();

                for(int i=0;i<categoryList.size();i++){
                    Category category = categoryList.get(i);
                    if(category.isSelected()){
                        categoryNames.add(category);
                    }
                }
                if(categoryNames.size()>0){
                    setCategoriesForUser(categoryNames);
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
