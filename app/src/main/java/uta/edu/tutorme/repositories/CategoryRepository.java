package uta.edu.tutorme.repositories;

import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.Category;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class CategoryRepository extends MapRepositoryImpl<Integer,Category>{

    public boolean createNewCategory(Category newcategory) throws Exception {
        this.save(1, newcategory);
        return true;
    }

    @Override
    public void save(Integer id, Category input) {
        input.save();
    }

    @Override
    public int saveAll(List<Integer> ids, List<Category> input) throws RecordNotFoundException, InconsistentSizeException {
        for (Category cat : input){
            cat.save();
        }
        return input.size();
    }

    @Override
    public List<Category> findAll() {
        List<Category> allcategory = Category.listAll(Category.class);
        return allcategory;
    }

    @Override
    public Category findById(Integer id) throws RecordNotFoundException {
       return Category.findById(Category.class, (long) id);
    }
}
