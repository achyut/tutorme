package uta.edu.tutorme.models;

/**
 * Created by ananda on 2/19/16.
 */
public class SubCategory {
    Long id;
    String name;
    boolean selected;

    Category category;

    public SubCategory(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    public SubCategory(String name, boolean selected, Category category) {
        this.name = name;
        this.selected = selected;
        this.category = category;
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
/*

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
*/

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }


}
