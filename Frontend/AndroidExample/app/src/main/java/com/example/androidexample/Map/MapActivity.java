package com.example.androidexample.Map;

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
import com.example.androidexample.FlashCardActivity;
import com.example.androidexample.R;
import com.example.androidexample.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapActivity extends AppCompatActivity {
    static Button a_1,a_2,a_3,b_1,b_2,b_3,b_4,c_1,c_2,boss;
    static TextView start;
    Button option1,option2,option3,option4,load,New;
    TextView question;
    StringBuilder seed;
    char type;
    static int hp;
    static char a_1_Type,a_2_Type,a_3_Type,b_1_Type,b_2_Type,b_3_Type,b_4_Type,c_1_Type,c_2_Type;
    String positon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_load_map);
        New=findViewById(R.id.MapNew);
        load=findViewById(R.id.MapLoad);
        MapGenerator map = new MapGenerator();
        New.setText("New game");
        load.setText("Load game");
        New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positon="start";
                setUi();
                seed = new StringBuilder(map.NewMap());
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seed= new StringBuilder("01232102311");
                positon="a_1";
                setUi();
                map.newMap(String.valueOf(seed));
            }
        });

    }
    void setUi(){
        setContentView(R.layout.activity_map);
        a_1 = findViewById(R.id.btnMap1_1);
        a_2 = findViewById(R.id.btnMap1_2);
        a_3 = findViewById(R.id.btnMap1_3);
        b_1 = findViewById(R.id.btnMap2_1);
        b_2 = findViewById(R.id.btnMap2_2);
        b_3 = findViewById(R.id.btnMap2_3);
        b_4 = findViewById(R.id.btnMap2_4);
        c_1 = findViewById(R.id.btnMap3_1);
        c_2 = findViewById(R.id.btnMap3_2);
        start=findViewById(R.id.Start);
        boss = findViewById(R.id.btnBoss);
        start.setText("start");
        a_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon="a_1";
                    setPositon('1','1');
                    a_1.setText(a_1.getText().toString()+"*");
                    event(a_1_Type);
                }
            }
        });
        a_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon="a_2";
                    setPositon('1','2');
                    a_2.setText(a_2.getText().toString()+"*");
                    event(a_2_Type);
                }
            }
        });
        a_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon="a_3";
                    setPositon('1','3');
                    a_3.setText(a_3.getText().toString()+"*");
                    event(a_3_Type);
                }
            }
        });
        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_1") {
                    positon="b_1";
                    setPositon('2','1');
                    b_1.setText(b_1.getText().toString()+"*");
                    event(b_1_Type);
                }
            }
        });
        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_2"||positon=="a_1"){
                    positon="b_2";
                    setPositon('2','2');
                    b_2.setText(b_2.getText().toString()+"*");
                    event(b_2_Type);
                }
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_3"||positon=="a_2"){
                    positon="b_3";
                    setPositon('2','3');
                    b_3.setText(b_3.getText().toString()+"*");
                    event(b_3_Type);
                }
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_3"){
                    positon="b_4";
                    setPositon('2','4');
                    b_4.setText(b_4.getText().toString()+"*");
                    event(b_4_Type);
                }
            }
        });
        c_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="b_1"||positon=="b_2"){
                    positon="c_1";
                    setPositon('3','1');
                    c_1.setText(c_1.getText().toString()+"*");
                    event(c_1_Type);
                }

            }
        });
        c_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="b_3"||positon=="b_4"){
                    positon="c_2";
                    setPositon('3','2');
                    c_2.setText(c_2.getText().toString()+"*");
                    event(c_2_Type);
                }
            }
        });
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="c_2"||positon=="c_1"){
                    setPositon('4','1');
                }

            }
        });
    }
    void setPositon(char y, char x){
        seed.setCharAt(9,y);
        seed.setCharAt(10,x);
    }
    void event(char event){
        if(event=='0'){
            //todo call battle
        }
        else if(event=='1'){
            hp= Math.min(100,hp+20);
        }
        else if(event=='2'){
            RandomEvents();
        }
        else{
            setContentView(R.layout.event_quiz);
            Quiz();
        }
    }
    void Quiz() {
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
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapGenerator map = new MapGenerator();
                setUi();
                map.newMap(String.valueOf(seed));
            }
        });
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

