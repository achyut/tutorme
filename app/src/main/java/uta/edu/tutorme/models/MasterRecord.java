package uta.edu.tutorme.models;

import com.orm.SugarRecord;

/**
 * Created by ananda on 2/23/16.
 */
public class MasterRecord extends SugarRecord {

    Long id;
    String key;
    String value;

    public MasterRecord() {

    }

    public MasterRecord(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
