package uta.edu.tutorme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import uta.edu.tutorme.R;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.utils.DisplayMessage;

/**
 * Created by ananda on 2/23/16.
 */
public class MyCustomAdapter extends ArrayAdapter<Category> {
    private List<Category> categoryList;
    Context context;
    public MyCustomAdapter(Context context, int resourceId,
                           List<Category> categoryList) {
        super(context, resourceId, categoryList);
        this.categoryList = categoryList;
        this.context = context;
    }

    private class Holder {
        TextView categoryName;
        CheckBox checkBox;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Holder holder = null;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(
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