package com.example.androidexample;

import android.app.Activity;
import android.content.Intent;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FlashCardActivity extends AppCompatActivity {
    private TextView answer;
    private TextView Wrong_1;
    private TextView Wrong_2;
    private TextView Wrong_3;
    private TextView Question;
    private TextView Card_Number;
    private TextView Save_Check;
    private int Card_Number_int;
    private int Total_Cards;
    private Button Home;
    private Button Back;
    private Button Save;
    private Button Next;
    private String BaseUrl;
    private String SqlUrl;
    private JSONObject FlashCard;
    private Date currentTime;


    /**
     * Initilizes ui and Gets the first Flash Card
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        SqlUrl = "http://coms-309-031.class.las.iastate.edu:8080/flashcards";
        Question = findViewById(R.id.Question);
        answer = findViewById(R.id.Correct);
        Wrong_1 = findViewById(R.id.Wrong_1);
        Wrong_2 = findViewById(R.id.Wrong_2);
        Wrong_3 = findViewById(R.id.Wrong_3);
        Save_Check = findViewById(R.id.Save_Check);
        Card_Number = findViewById(R.id.Card_number);
        Home = findViewById(R.id.Home);
        Back = findViewById(R.id.Prev_Card);
        Next = findViewById(R.id.Next_Card);
        Save = findViewById(R.id.Save);
        Card_Number_int = 1;
        Card_Number.setText("1");
        FlashCard = new JSONObject();
       makeJsonArrayReq(SqlUrl);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int++;
                Card_Number.setText(Integer.toString(Card_Number_int));
                if(Total_Cards>Card_Number_int){
                    makeJsonArrayReq(SqlUrl);
                }
                else{
                    Question.setText("");
                    answer.setText("");
                    Wrong_1.setText("");
                    Wrong_2.setText("");
                    Wrong_3.setText("");
                }
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int = Math.max(1,Card_Number_int-1);
                Card_Number.setText(Integer.toString(Card_Number_int));
                // if there is time can change this to use card_number_int so it all updates at once
                makeJsonArrayReq(SqlUrl);
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = "";
                test = test+ Question.getText();
                if (test == "") {

                    try {
                        FlashCard.put("CardNumber", Card_Number.getText());
                        makeJsonObjDel(BaseUrl + "Delete");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        FlashCard.put("question", Question.getText());
                        FlashCard.put("answer", answer.getText());
                        FlashCard.put("option1", Wrong_1.getText());
                        FlashCard.put("option2", Wrong_2.getText());
                        FlashCard.put("option3", Wrong_3.getText());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    if(Total_Cards>Card_Number_int){
                        makeJsonObjSave(SqlUrl);
                    }
                    else{
                        makeJsonObjPost(SqlUrl);
                    }
                }

            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(FlashCardActivity.this, MainActivity.class);
                startActivity(test);
            }
        });

    }

    /**
     * Parses JSON Array
     * @param response
     */
    private void Json_Parse(JSONArray response){
        int x = 0;
        int i = -2;
        int Current_Card = 1;
        int slot =0; //keeps track where to put the info
        String text = response.toString();
        String parsed="";
        while (x< text.length()-1){
            if(text.charAt(x)=='\"'){
                i++;
            }
            else if(i==3 && text.charAt(x)!='\"'){
                parsed =parsed + text.charAt(x);
            }
            if(i==4){
                i=0;
                if(Current_Card == Card_Number_int){
                    if(slot==0){
                        Question.setText(parsed);
                    }
                    if(slot==1){
                        answer.setText(parsed);
                    }
                    if(slot==2){
                        Wrong_1.setText(parsed);
                    }
                    if(slot==3){
                        Wrong_2.setText(parsed);
                    }
                    if(slot==4){
                        Wrong_3.setText(parsed);
                        Current_Card++;
                        i =-2;
                        slot =-1;
                    }
                }
                else{
                    i=0;
                    if(slot==4){
                        Current_Card++;
                        slot =-1;
                        i =-2;
                    }

                }
                slot++;
                parsed="";
                x++;
            }
            x++;
        }
        Total_Cards = Current_Card;
    }

    /**
     * Gets Flash Card
     * @param url
     */
    private void makeJsonArrayReq(String url) {
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        Json_Parse(response);

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

    /**
     * Saves new FlashCard
     * @param url
     */
    private void makeJsonObjPost(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                FlashCard,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        currentTime = Calendar.getInstance().getTime();
                        Save_Check.setText("Save Successful "+currentTime.toString());
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

    /**
     * Deletes FlashCard
     * @param url
     */
    private void makeJsonObjDel(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                FlashCard,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        currentTime = Calendar.getInstance().getTime();
                        Save_Check.setText("Delete Successful "+currentTime.toString());
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

    /**
     * Updates FlashCard
     * @param url
     */
    private void makeJsonObjSave(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                FlashCard,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        currentTime = Calendar.getInstance().getTime();
                        Save_Check.setText("Delete Successful "+currentTime.toString());
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


