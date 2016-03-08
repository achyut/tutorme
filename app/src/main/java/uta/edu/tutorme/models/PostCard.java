package uta.edu.tutorme.models;

/**
 * Created by ananda on 3/7/16.
 */
public class PostCard {
    private int id;
    private String title;
    private double price;
    private double rating;
    private String shortdesc;

    public PostCard(int id, String title, double price, double rating, String shortdesc) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.shortdesc = shortdesc;
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
}
