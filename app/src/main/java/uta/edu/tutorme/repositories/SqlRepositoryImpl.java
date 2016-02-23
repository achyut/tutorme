package uta.edu.tutorme.repositories;

import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.SearchModel;

/**
 * Created by anmolb77 on 2/22/2016.
 */
public class SqlRepositoryImpl<I extends Comparable,T> implements GenericRepository<I, T > {
    @Override
    public void save(I id, T input) {

    }

    @Override
    public int saveAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        return 0;
    }

    @Override
    public boolean update(I id, T input) throws RecordNotFoundException {
        return false;
    }

    @Override
    public boolean updateAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        return false;
    }

    @Override
    public T findById(I id) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void delete(I id) throws RecordNotFoundException {

    }

    @Override
    public void deleteAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {

    }

    @Override
    public List<T> search(SearchModel searchInput) {
        return null;
    }
}
