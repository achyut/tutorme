package uta.edu.tutorme.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ananda on 3/30/16.
 */
public class FilterElementRange implements Serializable{
    @SerializedName("from-value")
    protected String fromValue;
    @SerializedName("to-value")
    protected String toValue;

    protected String key;

    public FilterElementRange(String fromValue, String toValue, String key) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.key = key;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
