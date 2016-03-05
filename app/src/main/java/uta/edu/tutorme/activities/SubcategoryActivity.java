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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.adapters.SubCategoryAdapter;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.repositories.SubCategoryRepository;

public class SubcategoryActivity extends Activity {

    SubCategoryAdapter customAdapter = null;
    SubCategoryRepository repo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        setTitle("SubCategory");
        repo = new SubCategoryRepository();
        displaySubCategoryListView();
        // backButtonClick();
        // submitButtonClick();
    }

    /* private class MyCustomAdapter extends ArrayAdapter<SubCategory> {
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
 */
    private void displaySubCategoryListView() {
/*        ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
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
        subCategoryList.add(subCategory);*/

        Bundle bundle = getIntent().getExtras();

        //  ArrayList<String> categoryList = intent.getStringArrayListExtra("categoryNames");
      /*  ArrayList<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        SubCategory subCategory = new SubCategory(1,"Guitar",false);
        for(int i=0;i<categoryList.size();i++){
            if(categoryList.get(i).equals("Music")){
                SubCategory subCategory =
            }

        }*/


        /*List<SubCategory> subCategories = repo.findAll();
        Iterator<SubCategory> iterator = subCategories.iterator();
        while (iterator.hasNext()){
            Log.i("Subcategory ",subCategories.toString());
        }*/
        
        List<SubCategory> subCategoryList = new ArrayList<SubCategory>();
        subCategoryList = repo.findAll();


        if(subCategoryList.isEmpty()){
            Log.i("SubCategory List :","is Empty");
        }else{
            Log.i("SubCategory List :","is Not Empty");
        }

        customAdapter = new SubCategoryAdapter(this,
                R.layout.activity_populate_sub_category, subCategoryList);
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

                Intent homepage = new Intent(getApplicationContext(),HomepageActivity.class);
                startActivity(homepage);

                /*ArrayList<SubCategory> subCategoryList = customAdapter.subCategoryList;
                ArrayList<String> categoryNames = new ArrayList<String>();
                for (int i = 0; i < subCategoryList.size(); i++) {
                    SubCategory category = subCategoryList.get(i);
                    if (category.isSelected()) {
                        categoryNames.add(category.getName());
                    }
                }
                Intent subCategoryIntent = new Intent(getApplicationContext(), SubcategoryActivity.class);
                subCategoryIntent.putStringArrayListExtra("countryNames", categoryNames);
                startActivity(subCategoryIntent);*/


            }
        });

    }


}
