package uta.edu.tutorme.generators;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.MasterRecord;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.repositories.MasterRecordRepository;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class CategoryGenerator {
    CategoryRepository repository;
    public CategoryGenerator(CategoryRepository repository) {
        this.repository = repository;
    }

    public void generateCategory(){
        Category category_1 = new Category("Science", false);
        Category category_2 = new Category("Mathematics", false);
        Category category_3 = new Category("Music", false);
        Category category_4 = new Category("Sports", false);
        Category category_5 = new Category("Psychology", false);


        SubCategory subCategory1 = new SubCategory("Guitar",false,category_3);
        SubCategory subCategory2 = new SubCategory("Tabala",false,category_3);
        List<SubCategory> musicsub = new ArrayList<>();
        musicsub.add(subCategory1);
        musicsub.add(subCategory2);
        category_4.setSubCategories(musicsub);


        SubCategory subCategory3 = new SubCategory("Football",false,category_4);
        List<SubCategory> gamesub = new ArrayList<>();
        gamesub.add(subCategory3);
        category_4.setSubCategories(gamesub);

        try {
            repository.findById(1);
            MasterRecordRepository masterRecordRepository = new MasterRecordRepository();
            String populatecat = masterRecordRepository.getValue("populatecategory");
            if(populatecat==null || populatecat.equalsIgnoreCase("false")){
                repository.deleteAll(null,null);
                repository.save(1, category_1);
                repository.save(2, category_2);
                repository.save(3, category_3);
                repository.save(4, category_4);
                repository.save(5, category_5);
                masterRecordRepository.addRecrord("populatecategory","true");
            }
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (InconsistentSizeException e) {
            e.printStackTrace();
        }


    }
}
