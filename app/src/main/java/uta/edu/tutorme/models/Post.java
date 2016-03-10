package uta.edu.tutorme.models;
import java.io.Serializable;
import java.util.Date;
public class Post implements Serializable{
    private int id;
    private String title;
    private String shortdesc;
    private String longdesc;
    private double price;
    private double rating;
    private Date startdate;
    private Date enddate;
    private Date starttime;
    private Date endtime;
    private String address;
    private String contact;
    private String email;
    private String preferedcontact;
    private Category category;
    private SubCategory subcategory;
    private User created_by;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferedcontact() {
        return preferedcontact;
    }

    public void setPreferedcontact(String preferedcontact) {
        this.preferedcontact = preferedcontact;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }
}
