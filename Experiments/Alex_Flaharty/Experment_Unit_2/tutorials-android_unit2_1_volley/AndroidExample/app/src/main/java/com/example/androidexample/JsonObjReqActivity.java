package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjReqActivity extends AppCompatActivity {

    private Button btnJsonObjReq;
    private TextView msgResponse;
    private TextView object;
    private String type;
    private String name;

    //private static final String URL_JSON_OBJECT = "https://jsonplaceholder.typicode.com/users/1";
    //private static final String URL_JSON_OBJECT = "http://date.jsontest.com/";
    private static final String URL_JSON_OBJECT = "https://270850cd-16f4-4610-8e1b-a64f07b81113.mock.pstmn.io/Object/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_obj_req);

        btnJsonObjReq = findViewById(R.id.btnJsonObj);
        msgResponse = findViewById(R.id.Type);
        object = findViewById(R.id.Object);

        btnJsonObjReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReq();
            }
        });
    }

    /**
     * Making json object request
     */
    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                URL_JSON_OBJECT,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        JSONParse(response.toString());
                        msgResponse.setText(type);
                        object.setText(name);
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
    private void JSONParse(String text){
        type = "";
        name = "";
        int z = 0;
        int i = 0;
        int x =text.length();
        while(i<x){
            if(text.charAt(i)== '\"'){
                z++;
            }
            else{
                if(z==1){
                    type =type + text.charAt(i);
                }
                if(z==3){
                    name = name + text.charAt(i);
                }
            }
            i++;
        }
    }

}