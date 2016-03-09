package uta.edu.tutorme.models;

import java.util.List;

/**
 * Created by anmolb77 on 2/20/2016.
 */
public class Category {
    int id;
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

    public Category(int id, String name, List<SubCategory> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

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
