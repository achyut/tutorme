package uta.edu.tutorme.services;

import java.util.List;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.SearchModel;
import uta.edu.tutorme.repositories.GenericRepository;

/**
 * Created by ananda on 2/18/16.
 */
public class GenericSerciveImpl<I extends Comparable,T,R extends GenericRepository> implements GenericService<I,T,R>{

    R repository;

    public GenericSerciveImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(I id, T input) {
        repository.save(id, input);
    }

    @Override
    public int saveAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        return repository.saveAll(ids,input);
    }

    @Override
    public boolean update(I id, T input) throws RecordNotFoundException {
        return repository.update(id,input);
    }

    @Override
    public boolean updateAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        return repository.updateAll(ids,input);
    }

    @Override
    public T findById(I id) throws RecordNotFoundException {
        return (T)repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(I id) throws RecordNotFoundException {
        repository.delete(id);
    }

    @Override
    public void deleteAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        repository.deleteAll(ids,input);
    }

    @Override
    public List<T> search(SearchModel searchInput) {
        return repository.search(searchInput);
    }
}
