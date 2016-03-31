package uta.edu.tutorme.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ananda on 3/30/16.
 */
public class FilterElementSimple implements Serializable{
    private String value;
    private String key;

    public FilterElementSimple(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
