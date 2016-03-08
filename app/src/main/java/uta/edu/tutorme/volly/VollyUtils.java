package uta.edu.tutorme.volly;

import com.android.volley.NetworkResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ananda on 3/8/16.
 */
public class VollyUtils {

    public static JSONObject getResponseData(NetworkResponse response){
        if(response!=null){
            if(response.data!=null){
                String json = new String(response.data);
                try {
                    return new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getString(NetworkResponse response,String key){
        JSONObject resp = getResponseData(response);
        try {
            return resp.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
