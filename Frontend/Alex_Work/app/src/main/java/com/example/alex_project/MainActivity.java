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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView answer;
    private TextView Wrong_1;
    private TextView Wrong_2;
    private TextView Wrong_3;
    private TextView Question;
    private TextView Card_Number;
    private int Card_Number_int;
    private Button Home;
    private Button Back;
    private Button Save;
    private Button Next;
    private String BaseUrl;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseUrl = "https://1b8a5bc2-eeac-4f16-a22c-dbcde8bfecdd.mock.pstmn.io/FlashCard/";
        answer = findViewById(R.id.Correct);
        Wrong_1 = findViewById(R.id.Wrong_1);
        Wrong_2 = findViewById(R.id.Wrong_2);
        Wrong_3 = findViewById(R.id.Wrong_3);
        Question = findViewById(R.id.Question);
        Card_Number = findViewById(R.id.Card_number);
        Home = findViewById(R.id.Home);
        Back = findViewById(R.id.Prev_Card);
        Next = findViewById(R.id.Next_Card);
        Save = findViewById(R.id.Save);
        Card_Number_int = 1;
        Card_Number.setText("1");
        makeJsonObjReq(BaseUrl+'1');
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int++;
                Card_Number.setText(Integer.toString(Card_Number_int));
                makeJsonObjReq(BaseUrl+Card_Number.getText());
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int = Math.max(1,Card_Number_int-1);
                Card_Number.setText(Integer.toString(Card_Number_int));
                makeJsonObjReq(BaseUrl+Card_Number.getText());
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // todo send data to server
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
    private void Json_Parse(JSONObject response){
        int x = 0;
        int i = 0;
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
                if(slot==0){
                    Card_Number.setText(parsed);
                }
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
            }
            x++;
        }
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
}
