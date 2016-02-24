package uta.edu.tutorme.adapters;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by ananda on 2/23/16.
 */
public class CategoryHolder {
    TextView categoryName;
    CheckBox checkBox;

    public TextView getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(TextView categoryName) {
        this.categoryName = categoryName;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
