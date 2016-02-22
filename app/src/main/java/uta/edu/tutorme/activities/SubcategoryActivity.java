package uta.edu.tutorme.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.SubCategory;

public class SubcategoryActivity extends Activity {

    MyCustomAdapter customAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        setTitle("Sub SubCategory");
        displaySubCategoryListView();
        backButtonClick();
        submitButtonClick();
    }

    private class MyCustomAdapter extends ArrayAdapter<SubCategory> {
        private ArrayList<SubCategory> subCategoryList;
        public MyCustomAdapter(Context context, int resourceId,
                               ArrayList<SubCategory> subCategoryList) {
            super(context, resourceId, subCategoryList);
            this.subCategoryList = new ArrayList<SubCategory>();
            this.subCategoryList.addAll(subCategoryList);
        }

        private class Holder {
            TextView subCategoryName;
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
                holder.subCategoryName = (TextView) view.findViewById(R.id.subcategory_TextView);
                holder.checkBox = (CheckBox) view.findViewById(R.id.subcategory_CheckBox);
                view.setTag(holder);
            }
            else {
                holder = (Holder) view.getTag();
            }
            SubCategory country = subCategoryList.get(position);
            holder.checkBox.setChecked(country.isSelected());
            holder.subCategoryName.setText(country.getName());
            holder.checkBox.setTag(country);
            return view;
        }
    }

    private void displaySubCategoryListView() {
/*
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        SubCategory subCategory = new SubCategory(1,"Guitar",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(2,"Flute",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(3,"Algebra",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(4,"Geometry",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(5,"Planets",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(6,"Satellites",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(7,"Morden Arts",false);
        subCategoryList.add(subCategory);
        subCategory = new SubCategory(8,"Fine Arts",false);
        subCategoryList.add(subCategory);
*/

        Intent intent = getIntent();
        ArrayList<String> categoryList = intent.getStringArrayListExtra("categoryNames");
        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        SubCategory subCategory = new SubCategory(1,"Guitar",false);
        for(int i=0;i<categoryList.size();i++){
            if(categoryList.get(i).equals("Music")){
                subCategory = new SubCategory(1,"Guitar",false);
                subCategoryList.add(subCategory);
                subCategory = new SubCategory(2,"Flute",false);
                subCategoryList.add(subCategory);
            }
            if(categoryList.get(i).equals("Quant")){
                subCategory = new SubCategory(3,"Algebra",false);
                subCategoryList.add(subCategory);
                subCategory = new SubCategory(4,"Geometry",false);
                subCategoryList.add(subCategory);
            }
            if(categoryList.get(i).equals("Astronomy")){
                subCategory = new SubCategory(5,"Planets",false);
                subCategoryList.add(subCategory);
                subCategory = new SubCategory(6,"Satellites",false);
                subCategoryList.add(subCategory);
            }
            if(categoryList.get(i).equals("Arts")){
                subCategory = new SubCategory(7,"Morden Arts",false);
                subCategoryList.add(subCategory);
                subCategory = new SubCategory(8,"Fine Arts",false);
                subCategoryList.add(subCategory);
            }

        }

        customAdapter = new MyCustomAdapter(this,
                R.layout.activity_populate_category, subCategoryList);
        ListView listView = (ListView) findViewById(R.id.category_ListView);
        listView.setAdapter(customAdapter);
    }

    private void backButtonClick() {


    }

    private void submitButtonClick(){
        Button submitButton = (Button) findViewById(R.id.next_category_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SubCategory> subCategoryList = customAdapter.subCategoryList;
                ArrayList<String> categoryNames = new ArrayList<String>();
                for (int i = 0; i < subCategoryList.size(); i++) {
                    SubCategory category = subCategoryList.get(i);
                    if (category.isSelected()) {
                        categoryNames.add(category.getName());
                    }
                }
                Intent subCategoryIntent = new Intent(getApplicationContext(), SubcategoryActivity.class);
                subCategoryIntent.putStringArrayListExtra("countryNames", categoryNames);
                startActivity(subCategoryIntent);


            }
        });

    }


}

