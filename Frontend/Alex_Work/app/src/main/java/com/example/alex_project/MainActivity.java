package com.example.alex_project;

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

public class MainActivity extends AppCompatActivity {
    private TextView answer;
    private TextView Wrong_1;
    private TextView Wrong_2;
    private TextView Wrong_3;
    private TextView Question;
    private TextView Card_Number;
    private TextView Save_Check;
    private int Card_Number_int;
    private Button Home;
    private Button Back;
    private Button Save;
    private Button Next;
    private String BaseUrl;
    private String SqlUrl;
    private JSONObject FlashCard;
    private Date currentTime;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseUrl = "https://1b8a5bc2-eeac-4f16-a22c-dbcde8bfecdd.mock.pstmn.io/FlashCard/";
        SqlUrl = "http://coms-309-031.class.las.iastate.edu:8080/flashcards";
        answer = findViewById(R.id.Correct);
        Wrong_1 = findViewById(R.id.Wrong_1);
        Wrong_2 = findViewById(R.id.Wrong_2);
        Wrong_3 = findViewById(R.id.Wrong_3);
        Question = findViewById(R.id.Question);
        Save_Check = findViewById(R.id.Save_Check);
        Card_Number = findViewById(R.id.Card_number);
        Home = findViewById(R.id.Home);
        Back = findViewById(R.id.Prev_Card);
        Next = findViewById(R.id.Next_Card);
        Save = findViewById(R.id.Save);
        Card_Number_int = 1;
        Card_Number.setText("1");
        FlashCard = new JSONObject();
        makeJsonObjReq(BaseUrl+'1');
       //makeJsonArrayReq(SqlUrl);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int++;
                Card_Number.setText(Integer.toString(Card_Number_int));
                makeJsonObjReq(BaseUrl+Card_Number.getText());
                //makeJsonArrayReq(SqlUrl);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int = Math.max(1,Card_Number_int-1);
                Card_Number.setText(Integer.toString(Card_Number_int));
                // if there is time can change this to use card_number_int so it all updates at once
                makeJsonObjReq(BaseUrl+Card_Number.getText());
                //makeJsonArrayReq(SqlUrl);
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
                        FlashCard.put("CardNumber", Card_Number.getText());
                        FlashCard.put("Question", Question.getText());
                        FlashCard.put("Answer", answer.getText());
                        FlashCard.put("Wrong_1", Wrong_1.getText());
                        FlashCard.put("Wrong_2", Wrong_2.getText());
                        FlashCard.put("Wrong_3", Wrong_3.getText());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    makeJsonObjPost(BaseUrl + "Save/" + Card_Number.getText());
                }

            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(test);
            }
        });

    }
    private void Json_Parse(JSONArray response){
        int x = 0;
        int i = -2;
        int Current_Card = 1;
        int slot =0; //keeps track where to put the info
        String text = response.toString();
        String parsed="";
        while (x< text.length()-1){
            if(slot == 5){
                Current_Card++;
                slot =0;
            }
            if(text.charAt(x)=='\"'){
                i++;
            }
            else if(i==3 && text.charAt(x)!='\"'&&Current_Card == Card_Number_int){
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
                    }
                    slot++;
                }
                parsed="";
                x++;
            }
            x++;
        }
    }

    private void Json_Parse_Obj(JSONObject response){
        int x = 0;
        int i = 0;
        int Current_Card = 1;
        int slot =0; //keeps track where to put the info
        String text = response.toString();
        String parsed="";

        while (x< text.length()-1){
            if(text.charAt(x)=='\"'){
                i++;
            }
            else if(i==3){
                parsed =parsed + text.charAt(x);
            }
            if(i==4){
//                if(slot==0){
//                    Card_Number.setText(parsed);
//                }
                if(slot==1){
                    Question.setText(parsed);
                }
                if(slot==2){
                    answer.setText(parsed);
                }
                if(slot==3){
                    Wrong_1.setText(parsed);
                }
                if(slot==4){
                    Wrong_2.setText(parsed);
                }
                if(slot==5){
                    Wrong_3.setText(parsed);
                }
                slot++;
                i=0;
                parsed="";
                x++;
            }
            x++;
        }
    }
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
    private void makeJsonObjReq(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        Json_Parse_Obj(response);

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
}


