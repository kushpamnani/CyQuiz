package com.example.androidexample;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class BossCreatorActivity extends AppCompatActivity {

    private TextView healthGet;
    private TextView dmgGet;
    private TextView flashGet;
    private TextView errorText;
    private Button sendInfo;

    private String baseURL = "http://coms-309-031.class.las.iastate.edu:8080";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_create);

        healthGet = findViewById(R.id.healthInput);
        dmgGet = findViewById(R.id.dmgInput);
        flashGet = findViewById(R.id.cardInput);
        sendInfo = findViewById(R.id.sendBtn);
        errorText = findViewById(R.id.errorText);


        sendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayReq();



            }
        });






    }

    private void makeJsonArrayReq() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.GET,
                baseURL + "/flashcards",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }

}
