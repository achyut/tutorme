package uta.edu.tutorme.adapters;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by anmolb77 on 2/24/2016.
 */
public class SubCategoryHolder {
    TextView subcategoryName;
    CheckBox checkBox;

    public TextView getCategoryName() {
        return subcategoryName;
    }

    public void setSubCategoryName(TextView subCategoryName) {
        this.subcategoryName = subCategoryName;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
