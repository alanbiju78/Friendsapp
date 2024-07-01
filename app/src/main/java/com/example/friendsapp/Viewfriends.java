package com.example.friendsapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewfriends extends AppCompatActivity {
    String apiurl = "https://friendsapi-re5a.onrender.com/view";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewstudents);
        tv = findViewById(R.id.text);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET, apiurl,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        tv.setText(response.toString());
                        StringBuilder result=new StringBuilder();
                        for(int p=0;p<response.length();p++){
                            JSONObject jsonObject= null;
                            try {
                                jsonObject = response.getJSONObject(p);
                                String name=jsonObject.getString("name");
                                String friendName=jsonObject.getString("friendName");
                                String friendNickName=jsonObject.getString("friendNickName");
                                String DescribeYourFriend=jsonObject.getString("DescribeYourFriend");
                                result.append(name).append("\n ").append(friendName).append("\n ").append(friendNickName).append("\n ").append(" DescribeYourFriend");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        ((RequestQueue) requestQueue).add(jsonArrayRequest);

    }
}