package uta.edu.tutorme.generators;

import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.MasterRecord;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.models.User;

/**
 * Created by ananda on 2/24/16.
 */
public class InitializeTables {

    public void generateTables(){
        Category.findById(Category.class,1);
        SubCategory.findById(SubCategory.class,1);
        MasterRecord.findById(MasterRecord.class,1);
        User.findById(User.class,1);

    }
}
