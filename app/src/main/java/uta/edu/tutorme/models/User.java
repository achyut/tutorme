package uta.edu.tutorme.models;

import java.util.List;

/**
 * Created by ananda on 2/18/16.
 */

public class User{

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String usertype;
    private List<Category> categories;
    private List<SubCategory> subCategories;

    public User()
    {
    }

    public User(String name, String email, String phone, String address, String password, String usertype) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.usertype = usertype;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
