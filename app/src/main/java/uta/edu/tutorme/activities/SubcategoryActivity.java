package uta.edu.tutorme.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;

public class SubcategoryActivity extends Activity {

    MyCustomAdapter customAdapter = null;
    ArrayList<String> categoryListString = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        setTitle("Sub SubCategory");
        displaySubCategoryListView();
        backButtonClick();
        submitButtonClick();
    }

    private class MyCustomAdapter extends ArrayAdapter<Category> {
        private ArrayList<Category> categoryList;
        public MyCustomAdapter(Context context, int resourceId,
                               ArrayList<Category> categoryList) {
            super(context, resourceId, categoryList);
            this.categoryList = new ArrayList<Category>();
            this.categoryList.addAll(categoryList);
        }

        private class Holder {
            TextView categoryName;
            CheckBox checkBox;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            Holder holder = null;
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.activity_populate_sub_category, null);

                holder = new Holder();
                holder.categoryName = (TextView) view.findViewById(R.id.subcategory_TextView);
                holder.checkBox = (CheckBox) view.findViewById(R.id.subcategory_CheckBox);
                view.setTag(holder);
            }
            else {
                holder = (Holder) view.getTag();
            }
            Category category = categoryList.get(position);
            holder.checkBox.setChecked(category.isSelected());
            holder.categoryName.setText(category.getName());
            holder.checkBox.setTag(category);
            return view;
        }
    }

    private void displaySubCategoryListView() {
        Intent intent = getIntent();
       categoryListString = intent.getStringArrayListExtra("categoryNames");


        ArrayList<Category> categoryList = new ArrayList<Category>();
        Category category = new Category(1,"Music",false);
        categoryList.add(category);
        category = new Category(2,"Quant",false);
        categoryList.add(category);
        category = new Category(3,"Astronomy",false);
        categoryList.add(category);
        category = new Category(4,"Arts",false);
        categoryList.add(category);

        customAdapter = new MyCustomAdapter(this,
                R.layout.activity_populate_sub_category, categoryList);
        ListView listView = (ListView) findViewById(R.id.subcategory_ListView);
        listView.setAdapter(customAdapter);
    }

    private void backButtonClick() {


    }

    private void submitButtonClick(){

        Button nextButton = (Button) findViewById(R.id.submit_subcategory_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        categoryListString.get(0), Toast.LENGTH_LONG).show();


            }
        });
    }


}

