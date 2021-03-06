package uta.edu.tutorme.utils;

/**
 * Created by ananda on 3/7/16.
 */
public class Urls {



    public static String BASE_URL = "http://12751cd4.ngrok.io";


    public static String LOGIN_URL= BASE_URL+"/login";
    public static String FORGOT_PASSWORD_URL = BASE_URL+"/forgot";
    private static String CHANGE_PASSWORD_URL = BASE_URL+"/changepassword";
    private static String USER_POSTS = BASE_URL+"/users/post";
    public static String ALL_CATEGORIES = BASE_URL+"/allcategories";
    public static String CATEGORIES = BASE_URL+"/categories";
    public static String SUBCATEGORIES = BASE_URL+"/subcategories";
    public static String POSTS = BASE_URL+"/post";
    public static String USERS = BASE_URL+"/users";
    public static String SEARCH = BASE_URL+"/search";
    public static String REVIEWS = BASE_URL+"/reviews";

    public static String getPostDetailURL(int postid){
        return POSTS+"/"+postid;
    }
    public static String getUserPostsURL(int userid){
        return USER_POSTS+"/"+userid;
    }

    public static String getUserDetails(int userid){
        return USERS+"/"+userid;
    }
    public static String getSubcategoriesOfCategory(int categoryid){
        return CATEGORIES+"/subcategories/"+categoryid;
    }

    public static String getSponsoredURL(int postid){ return BASE_URL+"/sponsor/"+postid;}

    public static String getDeletePostURL(int postid){
        return POSTS+"/delete/"+postid;
    }
    public static String getUpdatePostURL(int postid){
        return POSTS+"/"+postid;
    }
    public static String getChangePasswordUrl(int userid){
        return CHANGE_PASSWORD_URL+"/"+userid;
    }

    public static String getPostReviewsURL(int postid) {
        return POSTS+"/reviews/"+postid;
    }

}
