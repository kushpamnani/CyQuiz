package com.example.androidexample.Map;

import static com.example.androidexample.BattleActivity.healthGet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.android.volley.toolbox.StringRequest;
import com.example.androidexample.BattleActivity;
import com.example.androidexample.ChatActivity;
import com.example.androidexample.EnemyCreatorActivity;
import com.example.androidexample.FlashCardActivity;
import com.example.androidexample.LoginActivity;
import com.example.androidexample.MainActivity;
import com.example.androidexample.R;
import com.example.androidexample.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import com.example.androidexample.LoginActivity;

public class MapActivity extends AppCompatActivity {
    static Button a_1, a_2, a_3, b_1, b_2, b_3, b_4, c_1, c_2, boss,Continue;
    static TextView start;
    Button option1, option2, option3, option4, load, New, CreateEvent,Win;
    TextView question;
    TextView desciption;
    static TextView Heath;
    TextView Hpchange;
    TextView Description;
    TextView Conditon;
    StringBuilder seed;
    static int hp;
    static char a_1_Type, a_2_Type, a_3_Type, b_1_Type, b_2_Type, b_3_Type, b_4_Type, c_1_Type, c_2_Type;
    String  positon;
    private String  url,url_event,id;
    private JSONObject info,RandomEvent;
    private JSONObject userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        info = new JSONObject();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_load_map);
        New = findViewById(R.id.MapNew);
        load = findViewById(R.id.MapLoad);
        CreateEvent = findViewById(R.id.EventCreateBtn);
        MapGenerator map = new MapGenerator();
        New.setText("New game");
        load.setText("Load game");
        url = "http://coms-309-031.class.las.iastate.edu:8080/";
        url_event = "http://coms-309-031.class.las.iastate.edu:8080/events";
        if(LoginActivity.getUserInfo() !=null){
            userInfo = LoginActivity.getUserInfo();
        }
        New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(LoginActivity.getUserInfo().getJSONArray("classroomRegistrations").getJSONObject(0) != null){
                        String test = String.valueOf(userInfo.getJSONArray("classroomRegistrations").getJSONObject(0).getJSONObject("map").getInt("id"));
                        makeMapDel(url+"maps/"+String.valueOf(userInfo.getJSONArray("classroomRegistrations").getJSONObject(0).getJSONObject("map").getInt("id")));
                    }
                } catch (JSONException e) {
                }
                positon = "start";
                hp = 100;
                setUi();
                seed = new StringBuilder(map.NewMap());
                try {
                    setPositon('0','0');
                    info.put("heath",hp);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeMapSave(url+"maps");

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    userInfo = LoginActivity.getUserInfo().getJSONArray("classroomRegistrations").getJSONObject(0);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                setUi();
                try {
                    seed = new StringBuilder(userInfo.getJSONObject("map").getString("seed"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                startingPositon(String.valueOf(seed));
                map.newMap(String.valueOf(seed));
            }
        });
        CreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapActivity.this, EventCreator.class));
            }
        });

    }

    void setUi() {
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
        start = findViewById(R.id.Start);
        boss = findViewById(R.id.btnBoss);
        Win= findViewById(R.id.Btn_Win);
        Win.setVisibility(View.GONE);
        start.setText("start");
        Heath = findViewById(R.id.Heath_val);
        Heath.setText(Integer.toString(hp));
        a_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "start") {
                    positon = "a_1";
                    try {
                        setPositon('1', '1');
                        setHp(hp);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    a_1.setText(a_1.getText().toString() + "*");
                    try {
                        event(a_1_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        a_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "start") {
                    positon = "a_2";
                    try {
                        setPositon('1', '2');
                        setHp(hp);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    a_2.setText(a_2.getText().toString() + "*");
                    try {
                        event(a_2_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        a_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "start") {
                    positon = "a_3";
                    try {
                        setPositon('1', '3');
                        setHp(hp);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    a_3.setText(a_3.getText().toString() + "*");
                    try {
                        event(a_3_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "a_1") {
                    positon = "b_1";
                    try {
                        setPositon('2', '1');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    b_1.setText(b_1.getText().toString() + "*");
                    try {
                        event(b_1_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "a_2" || positon == "a_1") {
                    positon = "b_2";
                    try {
                        setPositon('2', '2');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    b_2.setText(b_2.getText().toString() + "*");
                    try {
                        event(b_2_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "a_3" || positon == "a_2") {
                    positon = "b_3";
                    try {
                        setPositon('2', '3');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    b_3.setText(b_3.getText().toString() + "*");
                    try {
                        event(b_3_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "a_3") {
                    positon = "b_4";
                    try {
                        setPositon('2', '4');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    b_4.setText(b_4.getText().toString() + "*");
                    try {
                        event(b_4_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        c_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "b_1" || positon == "b_2") {
                    positon = "c_1";
                    try {
                        setPositon('3', '1');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    c_1.setText(c_1.getText().toString() + "*");
                    try {
                        event(c_1_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        c_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "b_3" || positon == "b_4") {
                    positon = "c_2";
                    try {
                        setPositon('3', '2');
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    c_2.setText(c_2.getText().toString() + "*");
                    try {
                        event(c_2_Type);
                        update();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon == "c_2" || positon == "c_1") {
                    try {
                        setPositon('4', '1');
                        setHp(healthGet());
                        startActivity(new Intent(MapActivity.this, BattleActivity.class));
                        Win.setVisibility(view.VISIBLE);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char test = seed.charAt(9);
                if(seed.charAt(9) == '4'){
                    try {
                        makeMapDel(url+"maps/"+userInfo.getString("id"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    finish();
                }
            }
        });
    }

    void setPositon(char y, char x) throws JSONException {
        info.put("seed",seed);
        seed.setCharAt(9, y);
        seed.setCharAt(10, x);
    }
    void changehp(int amount) throws JSONException {
        hp=Math.min(100,hp+amount);
        Heath.setText(Integer.toString(hp));
        info.put("heath", Integer.toString(hp));
        if(hp<=0){
            makeMapDel(url+"maps/"+userInfo.getString("id"));
        }
    }
    public void setHp(int amount) throws JSONException {
        hp= amount;
        Heath.setText(Integer.toString(hp));
        info.put("heath",String.valueOf(hp));
        if(hp<=0){
            makeMapDel(url);
        }
    }
    static public void afterbattleHp(int afterbattleHp){
        hp = afterbattleHp;
        Heath.setText(Integer.toString(hp));

    }
    public static int getHp() {
        return hp;
    }

    void event(char event) throws JSONException {
        if (event == '0') {
            setHp(healthGet());
            startActivity(new Intent(MapActivity.this, BattleActivity.class));

        } else if (event == '1') {
            changehp(20);
        } else if (event == '2') {
            setContentView(R.layout.event_random_event);
            Continue= findViewById(R.id.RE_Continue);
            RandomEvents();
        } else {
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

    void Quiz(String[] card) {
        question.setText(card[0]);
        Random rand = new Random();
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

    void RandomEvents() {
        makerandomEventreq(url_event);
    }
    void RandomEvents(JSONArray events) throws JSONException, InterruptedException {
        Random rand = new Random();
        JSONObject event = events.getJSONObject(Math.abs(rand.nextInt(events.length())));
        Hpchange = findViewById(R.id.RE_HealAmount);
        Description = findViewById(R.id.RE_Description);
        Conditon = findViewById(R.id.RE_Condition);
        Hpchange.setText(event.getString("hpChange"));
        Description.setText(event.getString("description"));
        if(!event.getString("condition1").equals("")&&!event.getString("condition2").equals("")){
            Conditon.setText("If hp is more then "+event.getString("condition2")+"Hp is less then "+event.getString("condition1"));
            if(hp<Integer.parseInt(event.getString("condition1"))&&hp>Integer.parseInt(event.getString("condition2"))){
                changehp(Integer.parseInt(event.getString("hpChange")));
            }
        } else if (!event.getString("condition1").equals("")) {
            Conditon.setText("If hp is less then "+event.getString("condition1"));
            if(hp<Integer.parseInt(event.getString("condition1"))){
                changehp(Integer.parseInt(event.getString("hpChange")));
            }
        }
        else if (!event.getString("condition2").equals("")) {
            Conditon.setText("If hp is more then "+event.getString("condition2"));
            if(hp>Integer.parseInt(event.getString("condition2"))){
                changehp(Integer.parseInt(event.getString("hpChange")));
            }
        }
        else{
            Conditon.setText("");
            changehp(Integer.parseInt(event.getString("hpChange")));
        }
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapGenerator map = new MapGenerator();
                setUi();
                map.newMap(String.valueOf(seed));
            }
        });
    }
    private void update() throws JSONException {
        info.put("id",userInfo.getJSONObject("map").getString("id"));
        info.put("hp",Integer.toString(hp));
        makeMapUpdate(url+"maps/"+userInfo.getJSONObject("map").getString("id"));
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
                            Quiz(flashcard.getFlashCardStringArray(rand.nextInt(response.length()), response));
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

    private void makeMapSave(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                info,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String temp = response.getString("id");
                            String test = userInfo.getJSONArray("classroomRegistrations").getJSONObject(0).getString("id");
                            String tempurl = url+"/"+response.getString("id")+"/classroom_registrations/"+userInfo.getJSONArray("classroomRegistrations").getJSONObject(0).getString("id");
                            makeMaplink(url+"/"+response.getString("id")+"/classroom_registrations/"+userInfo.getJSONArray("classroomRegistrations").getJSONObject(0).getString("id"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Volley Response", response.toString());
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
    private void makeMapDel(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                info,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
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
    private void makeMaplink(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            userInfo = LoginActivity.getUserInfo().getJSONArray("classroomRegistrations").getJSONObject(0);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Log.d("Volley Response", response.toString());
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
    private void makeMapUpdate(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                info,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
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
    void startingPositon(String seed){
        if(seed.charAt(9)=='0'){
            positon="start";
        }
        else if(seed.charAt(9)=='1'){
            if(seed.charAt(10)=='1'){
                positon="a_1";
            }
            else if(seed.charAt(10)=='2'){
                positon="a_2";
            }
            else if(seed.charAt(10)=='3'){
                positon="a_3";

            }

        }
        else if(seed.charAt(9)=='2'){
            if(seed.charAt(10)=='1'){
                positon="b_1";
            }
            else if(seed.charAt(10)=='2'){
                positon="b_2";
            }
            else if(seed.charAt(10)=='3'){
                positon="b_3";
            }
            else if(seed.charAt(10)=='4'){
                positon="b_4";
            }

        }
        else if(seed.charAt(9)=='3'){
            if(seed.charAt(10)=='1'){
                positon="c_1";
            }
            else if(seed.charAt(10)=='2'){
                positon="c_2";
            }

        }
    }
    private void makerandomEventreq(String url) {
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());
                        try {
                            RandomEvents(response);
                        } catch (JSONException e) {
                        } catch (InterruptedException e) {
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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(JsonArrayRequest);
    }
    private void makemapidreq(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        try {
                           id = response.getString("map");
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

