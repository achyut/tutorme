package uta.edu.tutorme.generators;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.models.MasterRecord;
import uta.edu.tutorme.models.SubCategory;
import uta.edu.tutorme.repositories.CategoryRepository;
import uta.edu.tutorme.repositories.MasterRecordRepository;
import uta.edu.tutorme.repositories.SubCategoryRepository;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class CategoryGenerator {
    CategoryRepository categoryRepository;
    SubCategoryRepository subCategoryRepository;
    public CategoryGenerator(CategoryRepository repository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = repository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public void generateCategory(){
        Category category_1 = new Category("Science", false);
        Category category_2 = new Category("Mathematics", false);
        Category category_3 = new Category("Music", false);
        Category category_4 = new Category("Sports", false);
        Category category_5 = new Category("Psychology", false);


       /* SubCategory subCategory1 = new SubCategory("Guitar",false,category_3);
        SubCategory subCategory2 = new SubCategory("Tabala",false,category_3);
        List<SubCategory> musicsub = new ArrayList<>();
        musicsub.add(subCategory1);
        musicsub.add(subCategory2);
        category_4.setSubCategories(musicsub);


        SubCategory subCategory3 = new SubCategory("Football",false,category_4);
        List<SubCategory> gamesub = new ArrayList<>();
        gamesub.add(subCategory3);
        category_4.setSubCategories(gamesub);*/

        try {
            MasterRecordRepository masterRecordRepository = new MasterRecordRepository();
            String populatecat = masterRecordRepository.getValue("populatecategory");
            if(populatecat==null || populatecat.equalsIgnoreCase("false")){
                categoryRepository.deleteAll(null,null);
                categoryRepository.save(1, category_1);
                categoryRepository.save(2, category_2);
                categoryRepository.save(3, category_3);
                categoryRepository.save(4, category_4);
                categoryRepository.save(5, category_5);
                masterRecordRepository.addRecrord("populatecategory","true");
            }
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (InconsistentSizeException e) {
            e.printStackTrace();
        }


    }

    public void generateSubCategory(){

        Log.i("Generate SubCategory", "In SubCategory");

        SubCategory subCategory_1 = new SubCategory("Physics",false);
        SubCategory subCategory_2 = new SubCategory("Chemistry",false);
        SubCategory subCategory_3 = new SubCategory("Biology",false);
        SubCategory subCategory_4 = new SubCategory("Algebra",false);
        SubCategory subCategory_5 = new SubCategory("Geometry",false);
        SubCategory subCategory_6 = new SubCategory("Discrete",false);
        SubCategory subCategory_7 = new SubCategory("Guitar",false);
        SubCategory subCategory_8 = new SubCategory("Violin",false);
        SubCategory subCategory_9 = new SubCategory("Drums",false);
        SubCategory subCategory_10 = new SubCategory("Football",false);
        SubCategory subCategory_11 = new SubCategory("Baseball",false);
        SubCategory subCategory_12 = new SubCategory("Basketball",false);
        SubCategory subCategory_13 = new SubCategory("Law",false);
        SubCategory subCategory_14 = new SubCategory("BehavioralPsychology",false);
        SubCategory subCategory_15 = new SubCategory("Criminal Psychology",false);

        try {
            subCategoryRepository.deleteAll(null, null);
            subCategoryRepository.save(1, subCategory_1);
            subCategoryRepository.save(2, subCategory_2);
            subCategoryRepository.save(3, subCategory_3);
            subCategoryRepository.save(4, subCategory_4);
            subCategoryRepository.save(5, subCategory_5);
            subCategoryRepository.save(6, subCategory_6);
            subCategoryRepository.save(7, subCategory_7);
            subCategoryRepository.save(8, subCategory_8);
            subCategoryRepository.save(9, subCategory_9);
            subCategoryRepository.save(10, subCategory_10);
            subCategoryRepository.save(11, subCategory_11);
            subCategoryRepository.save(12, subCategory_12);
            subCategoryRepository.save(13, subCategory_13);
            subCategoryRepository.save(14, subCategory_14);
            subCategoryRepository.save(15, subCategory_15);

        }
        catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (InconsistentSizeException e) {
            e.printStackTrace();
        }


    }
}
