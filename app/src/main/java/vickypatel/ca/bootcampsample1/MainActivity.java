package vickypatel.ca.bootcampsample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vickypatel.ca.bootcampsample1.adapters.CustomAdapter;
import vickypatel.ca.bootcampsample1.network.VolleySingleton;
import vickypatel.ca.bootcampsample1.pojos.User;

public class MainActivity extends AppCompatActivity {

    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLinearLayout;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleySingleton = new VolleySingleton(this);
        requestQueue = volleySingleton.getRequestQueue();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mLinearLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayout);

        Button testVolley = (Button) findViewById(R.id.testVolleyButton);
        testVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNetworkCall();
            }
        });
    }

    public void makeNetworkCall() {
        System.out.println("Get volley clicked");
        Toast.makeText(this, "Get volley clicked", Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/comments",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Yup Got data!!", Toast.LENGTH_LONG).show();

                        ArrayList<User> comments = parseResponse(response);
                        mAdapter = new CustomAdapter(MainActivity.this, comments);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }


    public ArrayList<User> parseResponse(JSONArray response) {
        ArrayList<User> userComments = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject commentData = response.getJSONObject(i);
                System.out.println(commentData.get("id"));
                User user = new User();
                user.setId(commentData.getInt("id"));
                user.setName(commentData.getString("name"));
                user.setEmail(commentData.getString("email"));
                user.setBody(commentData.getString("body"));
                userComments.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userComments;
    }
}
