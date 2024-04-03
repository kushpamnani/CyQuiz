package com.example.androidexample;

import android.os.Bundle;

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

public class EnemyCreatorActivity extends AppCompatActivity {
    private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_creator);



    }

}
