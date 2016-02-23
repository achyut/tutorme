package uta.edu.tutorme.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by heary on 2/22/16.
 */
public class DisplayMessage {

    public static void displayToast(Context applicationContext, String message){
        Toast toastemail = Toast.makeText(applicationContext,"Logging in",Toast.LENGTH_LONG);
        toastemail.show();
    }
}
