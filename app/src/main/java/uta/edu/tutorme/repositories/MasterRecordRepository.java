package uta.edu.tutorme.repositories;

import java.util.List;

import uta.edu.tutorme.models.MasterRecord;

/**
 * Created by ananda on 2/23/16.
 */
public class MasterRecordRepository {

    public String getValue(String key){
        List<MasterRecord> records = MasterRecord.find(MasterRecord.class, "key = ?",key);
        if(!records.isEmpty())
            return records.get(0).getValue();
        return null;
    }

    public void addRecrord(String key, String value){
        MasterRecord record = new MasterRecord(key,value);
        MasterRecord.save(record);
    }
}
