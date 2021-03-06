package uta.edu.tutorme.models;

import java.io.Serializable;

/**
 * Created by ananda on 3/7/16.
 */
public class PostCard implements Serializable{
    private int id;
    private String title;
    private double price;
    private double rating;
    private String shortdesc;
    private double sponsored;


    public PostCard(int id, String title, double price, double rating, String shortdesc,double sponsored) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.shortdesc = shortdesc;

        this.sponsored = sponsored;
    }


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

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public double getSponsored() {
        return sponsored;
    }

    public void setSponsored(double sponsored) {
        this.sponsored = sponsored;
    }

    @Override
    public String toString() {
        return "PostCard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", shortdesc='" + shortdesc + '\'' +
                ", sponsored=" + sponsored +
                '}';
    }
}
