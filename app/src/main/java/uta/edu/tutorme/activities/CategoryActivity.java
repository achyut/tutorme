
package uta.edu.tutorme.activities;


import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;


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
import java.util.StringTokenizer;

public class CategoryActivity extends Activity {

    MyCustomAdapter customAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Category");
        displayCategoryListView();
        nextButtonClick();
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
                view = layoutInflater.inflate(R.layout.activity_populate_category, null);

                holder = new Holder();
                holder.categoryName = (TextView) view.findViewById(R.id.category_TextView);
                holder.checkBox = (CheckBox) view.findViewById(R.id.category_CheckBox);
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

    private void displayCategoryListView() {
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
                R.layout.activity_populate_category, categoryList);
        ListView listView = (ListView) findViewById(R.id.category_ListView);
        listView.setAdapter(customAdapter);
    }

    private void nextButtonClick() {
        Button nextButton = (Button) findViewById(R.id.next_category_button);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Category> categoryList = customAdapter.categoryList;
                ArrayList<String> categoryNames = new ArrayList<String>();
                for(int i=0;i<categoryList.size();i++){
                    Category category = categoryList.get(i);
                    if(category.isSelected()){
                        categoryNames.add(category.getName());
                    }
                }
                Intent subCategoryIntent = new Intent(getApplicationContext(),SubcategoryActivity.class);
                subCategoryIntent.putStringArrayListExtra("categoryNames", categoryNames);
                startActivity(subCategoryIntent);


            }
        });

    }

}
