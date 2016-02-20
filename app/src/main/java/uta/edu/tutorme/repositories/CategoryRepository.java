package uta.edu.tutorme.repositories;

import uta.edu.tutorme.models.Category;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class CategoryRepository extends MapRepositoryImpl<Integer,Category>{

    public boolean createNewCategory(Category newcategory) throws Exception {
        this.save(newcategory.getId(),newcategory);
        return true;
    }
}
