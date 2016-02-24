package uta.edu.tutorme.repositorytest;

import org.junit.Before;
import org.junit.Test;

import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.Category;
import uta.edu.tutorme.repositories.CategoryRepository;

import static org.junit.Assert.*;

/**
 * Created by anmolb77 on 2/20/2016.
 */


public class CategoryRepositoryTest {

    CategoryRepository repository;

    @Before
    public void setUp(){
        repository = new CategoryRepository();
    }

    @Test
    public void testCreateNewCategory(){
        Category catgory1 = new Category("Music",false);
        catgory1.setName("Music");
        catgory1.setId(1L);

        try{
            boolean result = repository.createNewCategory(catgory1);
            assertTrue(result);
        }
        catch (Exception e){
            fail("Test failed due to duplicate category");
        }
    }

    @Test(expected = RecordNotFoundException.class)
    public void testGetCategoryByIdWhenNotExists() throws RecordNotFoundException {
        Category cat = repository.findById(1);
        assertNotNull(cat);
        assertEquals("Music",cat.getName());
    }

    @Test
    public void testGetCategoryByIdWhenExists(){
        Category catgory1 = new Category("Music",false);
        catgory1.setName("Music");
        catgory1.setId(1L);

        try {
            boolean result = repository.createNewCategory(catgory1);
            assertTrue(result);
            Category cat = repository.findById(1);
            assertNotNull(cat);
            assertEquals("Music",cat.getName());

        }
        catch(RecordNotFoundException e){
            fail("Record for the given id not found");
        }
        catch (Exception e) {
           fail("creating new category failed");
        }


    }
}
