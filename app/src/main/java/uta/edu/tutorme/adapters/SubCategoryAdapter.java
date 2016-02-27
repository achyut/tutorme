package uta.edu.tutorme.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import uta.edu.tutorme.R;
//import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.SubCategory;

/**
 * Created by anmolb77 on 2/24/2016.
 */
public class SubCategoryAdapter  extends ArrayAdapter<SubCategory> {
    public SubCategoryAdapter(Context context, int resource) {
        super(context, resource);
    }
    private List<SubCategory> subCategoryList;
    Context context;
    public SubCategoryAdapter(Context context, int resourceId,
                           List<SubCategory> subCategoryList) {
        super(context, resourceId, subCategoryList);
        this.subCategoryList = subCategoryList;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        SubCategoryHolder holder = null;
        if (view == null) {
            Log.i("Generate SubCategory", "In adapter view null");
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_populate_sub_category, null);
            holder = new SubCategoryHolder();
            holder.subcategoryName = (TextView) view.findViewById(R.id.subcategory_TextView);
            holder.checkBox = (CheckBox) view.findViewById(R.id.subcategory_CheckBox);
            view.setTag(holder);
        }
        else {
            holder = (SubCategoryHolder) view.getTag();
        }
        SubCategory subCategory = subCategoryList.get(position);
        holder.checkBox.setChecked(subCategory.isSelected());
        holder.subcategoryName.setText(subCategory.getName());
        holder.checkBox.setTag(subCategory);


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v ;
                SubCategory subCategory = (SubCategory) cb.getTag();
                subCategory.setSelected(cb.isChecked());

            }
        });

        return view;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }
}
