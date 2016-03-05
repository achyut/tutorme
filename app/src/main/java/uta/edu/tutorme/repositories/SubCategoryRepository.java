package uta.edu.tutorme.repositories;

import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.SubCategory;

/**
 * Created by anmolb77 on 2/24/2016.
 */
public class SubCategoryRepository extends MapRepositoryImpl<Integer, SubCategory>{

    public boolean createNewSubCategory(SubCategory newSubCategory) throws Exception {
        this.save(1, newSubCategory);
        return true;
    }

    @Override
    public void save(Integer id, SubCategory input) {
        input.save();
    }

    @Override
    public int saveAll(List<Integer> ids, List<SubCategory> input) throws RecordNotFoundException, InconsistentSizeException {
        for (SubCategory cat : input){
            cat.save();
        }
        return input.size();
    }

    @Override
    public List<SubCategory> findAll() {
        return SubCategory.listAll(SubCategory.class);
    }

    @Override
    public SubCategory findById(Integer id) throws RecordNotFoundException {
        return SubCategory.findById(SubCategory.class, (long) id);
    }

    @Override
    public void deleteAll(List<Integer> ids, List<SubCategory> input) throws RecordNotFoundException, InconsistentSizeException {
        SubCategory.deleteAll(SubCategory.class);
    }
}
