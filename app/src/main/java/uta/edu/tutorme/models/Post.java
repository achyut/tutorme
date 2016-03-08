package uta.edu.tutorme.models;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aishwarya on 3/1/2016.
 */
public class Post extends SugarRecord implements Serializable{
    private Long id;
    private String title;
    private String shortDesc;
    private String longDesc;
    private Category catg;
    private SubCategory subCatg;
    private double price;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    private String address;
    private String phoneNumber;
    private String email;
    private String prefContact;
    private User u;

    @Override
    public  Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Category getCatg() {
        return catg;
    }

    public void setCatg(Category catg) {
        this.catg = catg;
    }

    public SubCategory getSubCatg() {
        return subCatg;
    }

    public void setSubCatg(SubCategory subCatg) {
        this.subCatg = subCatg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefContact() {
        return prefContact;
    }

    public void setPrefContact(String prefContact) {
        this.prefContact = prefContact;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
