package uta.edu.tutorme.utils;

/**
 * Created by ananda on 3/7/16.
 */
public class Urls {

    //public static String BASE_URL = "http://192.168.43.245:8000";
    public static String BASE_URL = "http://9973e748.ngrok.io";

    public static String LOGIN_URL= BASE_URL+"/login";
    private static String USER_POSTS = BASE_URL+"/users/post";
    public static String CATEGORIES = BASE_URL+"/categories";
    public static String SUBCATEGORIES = BASE_URL+"/subcategories";
    public static String POSTS = BASE_URL+"/post";

    public static String getUserPostsURL(int userid){
        return USER_POSTS+"/"+userid;
    }

    public static String getSubcategoriesOfCategory(int categoryid){
        return CATEGORIES+"/subcategories/"+categoryid;
    }
}
