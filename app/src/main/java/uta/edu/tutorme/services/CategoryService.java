package uta.edu.tutorme.services;

import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;

/**
 * Created by anmolb77 on 2/22/2016.
 */
public class CategoryService extends GenericSerciveImpl<Integer,Category,CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }
}
