package uta.edu.tutorme.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uta.edu.tutorme.exceptions.InconsistentSizeException;
import uta.edu.tutorme.exceptions.RecordNotFoundException;
import uta.edu.tutorme.models.SearchModel;

/**
 * Created by ananda on 2/18/16.
 */
public class MapRepositoryImpl<I extends  Comparable,T> implements GenericRepository<I,T>{
    Map<I,T> table = new HashMap<I,T>();

    @Override
    public void save(I id, T input) {
        table.put(id,input);
    }

    @Override
    public int saveAll(List<I> ids,List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        if(ids.size()!=input.size()){
            throw new InconsistentSizeException("All input values should be defined the Id");
        }
        int i = 0;
        for(I id:ids){
            table.put(id,input.get(i));
            i++;
        }
        return i+1;
    }

    @Override
    public boolean update(I id, T input) throws RecordNotFoundException {
        if(table.containsKey(id)){
            table.put(id,input);
            return true;
        }
        else{
            throw new RecordNotFoundException(id+" id to update not found");
        }
    }

    @Override
    public boolean updateAll(List<I> ids, List<T> input) throws RecordNotFoundException,InconsistentSizeException {
        if(ids.size()!=input.size()){
            throw new InconsistentSizeException("All input values should be defined the Id");
        }
        int i = 0;
        for(I id:ids){
            if(!table.containsKey(id))
                throw new RecordNotFoundException(id+" id to update not found");
            i++;
        }
        i = 0;
        for(I id:ids){
            table.put(id,input.get(i));
            i++;
        }
        return true;
    }

    @Override
    public T findById(I id) throws RecordNotFoundException {
        if(!table.containsKey(id))
            throw new RecordNotFoundException(id +" id value not found");
        return table.get(id);
    }

    @Override
    public List<T> findAll() {
        List<T> records = new ArrayList<T>();
        for(T obj:table.values()){
            records.add(obj);
        }
        return records;

    }

    @Override
    public void delete(I id) throws RecordNotFoundException {
        if(!table.containsKey(id))
            throw new RecordNotFoundException(id +" id value not found");
        table.remove(id);
    }

    @Override
    public void deleteAll(List<I> ids, List<T> input) throws RecordNotFoundException, InconsistentSizeException {
        if(ids.size()!=input.size()){
            throw new InconsistentSizeException("All input values should be defined the Id");
        }
        int i = 0;
        for(I id:ids){
            if(!table.containsKey(id))
                throw new RecordNotFoundException(id+" id to update not found");
            i++;
        }
        i = 0;
        for(I id:ids){
            table.remove(ids.get(i));
            i++;
        }
    }

    @Override
    public List<T> search(SearchModel searchInput) {
        throw new UnsupportedOperationException("Method not Implemented");
    }


}
