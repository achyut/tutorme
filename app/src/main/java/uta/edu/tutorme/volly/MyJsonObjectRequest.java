package uta.edu.tutorme.volly;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ananda on 3/7/16.
 */
public class MyJsonObjectRequest extends JsonObjectRequest {

    private int mStatusCode;
    private JSONObject responseobj;

    public MyJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }
    public int getStatusCode() {
        return mStatusCode;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        if(response!=null){
            mStatusCode = response.statusCode;
            if(response.data!=null){
                String json = new String(response.data);
                try {
                    responseobj = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return super.parseNetworkResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {

        //return volleyError;

        return super.parseNetworkError(volleyError);
    }

    public JSONObject getResponseData(){
        return this.responseobj;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        // here you can write a custom retry policy
        return super.getRetryPolicy();
    }
}
