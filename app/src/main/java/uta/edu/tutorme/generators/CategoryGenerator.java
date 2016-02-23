package uta.edu.tutorme.generators;

import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;

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


//        category_1.setId(1);
//        category_1.setName("Science");
//
//        category_2.setId(2);
//        category_2.setName("Mathematics");
//
//        category_3.setId(3);
//        category_3.setName("Music");
//
//        category_4.setId(4);
//        category_4.setName("Sports");
//
//        category_5.setId(5);
//        category_5.setName("Psychology");

        try {
            repository.findById(1);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        repository.save(1, category_1);
        repository.save(2, category_2);
        repository.save(3, category_3);
        repository.save(4, category_4);
        repository.save(5, category_5);
    }
}
