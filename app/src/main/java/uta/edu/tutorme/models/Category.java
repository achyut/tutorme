package uta.edu.tutorme.models;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class Category extends SugarRecord {
    Long id;
    String name;
    boolean selected;
    List<SubCategory> subCategories;

    public Category() {
    }

    public Category(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<SubCategory> getSubCategories() {
        String catid = String.valueOf(getId());
        return SubCategory.find(SubCategory.class, "category = ?",catid);
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;}

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                ", subCategories=" + subCategories +
                '}';
    }
}
