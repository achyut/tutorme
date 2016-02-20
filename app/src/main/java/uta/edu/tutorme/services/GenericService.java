package uta.edu.tutorme.services;

import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.SearchModel;
import uta.edu.tutorme.repositories.GenericRepository;

/**
 * Created by ananda on 2/18/16.
 */
public interface GenericService<I extends Comparable,T,R extends GenericRepository> {

    /**
     * Method to save the input data
     * @param id
     * @param input
     */
    public void save(I id, T input);

    /**
     * Method to save all the records with given input
     * @param ids
     * @param input
     * @return
     * @throws RecordNotFoundException
     * @throws InconsistentSizeException
     */
    public int saveAll(List<I> ids,List<T> input) throws RecordNotFoundException, InconsistentSizeException ;


    /**
     * Method to update the entry
     * @param id
     * @param input
     * @return
     * @throws RecordNotFoundException
     */
    public boolean update(I id,T input) throws RecordNotFoundException;

    /**
     * Update all the records that are input
     * @param ids
     * @param input
     * @return
     * @throws RecordNotFoundException
     * @throws InconsistentSizeException
     */
    public boolean updateAll(List<I> ids,List<T> input) throws RecordNotFoundException,InconsistentSizeException;

    /**
     * Find the record by given id
     * @param id
     * @return
     * @throws RecordNotFoundException
     */
    public T findById(I id) throws RecordNotFoundException;

    /**
     * Method to find all the values
     * @return
     */
    public List<T> findAll();

    /**
     * Method to delete the record with given id
     * @param id
     * @throws RecordNotFoundException
     */
    public void delete(I id) throws RecordNotFoundException;

    /**
     * Method to delte all the records
     * @param ids
     * @param input
     * @throws RecordNotFoundException
     * @throws InconsistentSizeException
     */
    public void deleteAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException;


    /**
     * Method to search the records with given search input
     * @param searchInput
     * @return
     */
    public List<T> search(SearchModel searchInput);
}
