package uta.edu.tutorme.models;

import java.util.List;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class Category {
    private Integer id;
    private String name;
    private List<SubCategory> subCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
