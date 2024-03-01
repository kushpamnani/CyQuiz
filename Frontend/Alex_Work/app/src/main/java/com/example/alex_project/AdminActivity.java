package com.example.alex_project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;


public class AdminActivity extends AppCompatActivity {
    private TextView User_name;
    private TextView Banned_list;
    private TextView Save_Check;
    private Button Ban;
    private Button Unban;
    private Button Home;
    private String ReqUrl;
    private String BanUrl;
    private String UnbanUrl;
    private JSONObject Ban_Post;
    private Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        User_name = findViewById(R.id.Ban);
        Banned_list = findViewById(R.id.Banned_List);
        Ban = findViewById(R.id.Ban_button);
        Unban = findViewById(R.id.Unban);
        Home = findViewById(R.id.Home);
        Save_Check = findViewById(R.id.Save_check);
        ReqUrl ="https://1b8a5bc2-eeac-4f16-a22c-dbcde8bfecdd.mock.pstmn.io/Ban_List";
        BanUrl = "https://1b8a5bc2-eeac-4f16-a22c-dbcde8bfecdd.mock.pstmn.io/Ban";
        UnbanUrl = "https://1b8a5bc2-eeac-4f16-a22c-dbcde8bfecdd.mock.pstmn.io/Unban";

        makeJsonObjReq(ReqUrl);
        Ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ban_Post = new JSONObject();
                try {
                    Ban_Post.put("User",User_name.toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjPost(BanUrl);

            }
        });
        Unban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ban_Post = new JSONObject();
                try {
                    Ban_Post.put("User",User_name.toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjPost(UnbanUrl);

            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(test);
            }
        });

    }
    private String Json_Parse(JSONObject response){
        int x = 0;
        int i = 0;
        String text = response.toString();
        String parsed="";
        while (x< text.length()-1){
            if(text.charAt(x)== '\"'){
                i++;
            }
            else if(x!=0){
                parsed = parsed + text.charAt(x);
            }
            if(i==4){
                parsed = parsed +'\n';
                i=0;
                x++;
            }
            x++;
        }
        return parsed;
    }

    private void makeJsonObjReq(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        Banned_list.setText(Json_Parse(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };
        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    private void makeJsonObjPost(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                Ban_Post,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        currentTime = Calendar.getInstance().getTime();
                        Save_Check.setText(Json_Parse(response)+currentTime.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };
        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
}


