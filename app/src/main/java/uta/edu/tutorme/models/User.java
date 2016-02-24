package uta.edu.tutorme.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by ananda on 2/18/16.
 */

public class User extends SugarRecord{
    Long id;
    String name;
    String email;
    String phone;
    String address;
    String password;
    String usertype;

    public User() {
    }

    public User(String name, String email, String phone, String address, String password, String usertype) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.usertype = usertype;
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
}
