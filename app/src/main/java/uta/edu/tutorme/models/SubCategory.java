package uta.edu.tutorme.models;

/**
 * Created by ananda on 2/19/16.
 */
public class SubCategory {
    Integer id = null;
    String name = null;
    boolean selected = false;

    public SubCategory(Integer id, String name, boolean selected) {
        super();
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

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

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
