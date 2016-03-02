package uta.edu.tutorme.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import uta.edu.tutorme.activities.CategoryActivity;
import uta.edu.tutorme.activities.LoginActivity;
import uta.edu.tutorme.models.User;

/**
 * Created by ananda on 3/1/16.
 */
public class SharedPrefUtils {

    private static final String PREFS_NAME = "tutorme";

    public static void checkIfLoggedIn(Context activity){
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, 0);
        boolean loggedIn = settings.getBoolean("logged", true);
        if(!loggedIn){
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();
            Intent i = new Intent(activity,LoginActivity.class);
            activity.startActivity(i);
        }
        else{
            Intent categoryactivity = new Intent(activity,CategoryActivity.class);
            categoryactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(categoryactivity);
        }
    }


    public static User getUserFromSession(Context activity){
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, 0);
        User usr = new User();
        Long id = settings.getLong("userid",-1);
        String username = settings.getString("username", null);
        String email = settings.getString("email",null);
        String usertype = settings.getString("usertype",null);
        usr.setId(id);
        usr.setName(username);
        usr.setEmail(email);
        usr.setUsertype(usertype);
        return usr;
    }

    public static void storeUserInsharedPref(Context context,User user){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("userid", user.getId());
        editor.putString("username", user.getName());
        editor.putString("email",user.getEmail());
        editor.putString("usertype",user.getUsertype());
        editor.putBoolean("logged",true);
        editor.commit();
    }

    public static void deleteUserFromSharedPref(Context activity){
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
