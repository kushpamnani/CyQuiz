package com.example.androidexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BattleActivity extends AppCompatActivity {
    private String baseURL = "http://coms-309-031.class.las.iastate.edu:8080";
    private TextView battleID;
    private Button startButton;
    private String responseJSON = "";
    private int smallEnemies;
    private int largeEnemies;
    private TextView debugging;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battleID = findViewById(R.id.battleIDInput);
        startButton = findViewById(R.id.startBattleBtn);
        debugging = findViewById(R.id.debugging);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!battleID.getText().toString().equals("")){
                    makeJsonObjReqGet();

                }




            }
        });









    }
    private void makeJsonObjReqGet() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.GET,
                baseURL + "/battles/" + battleID.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        responseJSON = response.toString();
                        if (!Objects.equals(responseJSON, "")){
                            startButton.setVisibility(View.GONE);
                            battleID.setVisibility(View.GONE);
                        }



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


        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }


}
