package com.example.androidexample.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidexample.FlashCardActivity;
import com.example.androidexample.MainActivity;
import com.example.androidexample.R;
import com.example.androidexample.VolleySingleton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tiles extends MapActivity {
    char type;
    Button option1,option2,option3,option4;
    TextView question;
    String[] card;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(type=='0'){
            //todo call battle
        }
        else if(type=='1'){
            hp= Math.min(100,hp+20);
        }
        else if(type=='2'){
            RandomEvents();
        }
        else{
           Quiz();
        }

    }

    void event(char type){
        //0 is fight
        //1 is Heal
        //2 is Random Event
        //3 is Quiz
        this.type = type;
    }
    void Quiz() {
        setContentView(R.layout.event_quiz);
        question = findViewById(R.id.testQuestion);
        option1 = findViewById(R.id.btnResponse_1);
        option2 = findViewById(R.id.btnResponse_2);
        option3 = findViewById(R.id.btnResponse_3);
        option4 = findViewById(R.id.btnResponse_4);
        makeJsonArrayReq("http://coms-309-031.class.las.iastate.edu:8080/flashcards");

    }
    void Quiz(String[] card){
        question.setText(card[0]);
        option1.setText(card[1]);
        option2.setText(card[2]);
        option3.setText(card[3]);
        option4.setText(card[4]);
    }
    void RandomEvents(){

    }
    void makeJsonArrayReq(String url) {
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        response.length();
                        FlashCardActivity flashcard = new FlashCardActivity();
                        Random rand = new Random();
                        try {
                            Quiz(flashcard.getFlashCardStringArray(rand.nextInt(response.length()),response));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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
        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

}
