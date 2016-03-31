package uta.edu.tutorme.beans;

/**
 * Created by ananda on 3/30/16.
 */
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilterRequest implements Serializable{
    private String keyword;
    private String rating;
    private String category;
    private String subcategory;
    private String priceFrom;
    private String priceTo;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }
}