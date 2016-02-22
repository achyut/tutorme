package uta.edu.tutorme.models;

/**
 * Created by Administrator on 21-02-2016.
 */
    public class SubCategory1 {
        Integer id = null;
        String name = null;
        boolean selected = false;

        public SubCategory1(Integer id, String name, boolean selected) {
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
