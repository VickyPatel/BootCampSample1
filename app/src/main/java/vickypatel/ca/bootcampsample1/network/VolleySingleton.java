package vickypatel.ca.bootcampsample1.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by VickyPatel on 2016-06-13.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;

    public VolleySingleton(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
