package com.example.androidexample;

import static com.example.androidexample.OnlineTrackerActivity.getOnlineList;
import static com.example.androidexample.OnlineTrackerActivity.onlineCheck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class BattleCreatorActivity extends AppCompatActivity {

    private TextView smallEnemies;
    private TextView largeEnemies;
    private TextView displayText;
    private TextView bossNum;
    private Button battlePost;
    private String baseURL = "http://coms-309-031.class.las.iastate.edu:8080/";
    private JSONObject battleJSON;
    private Button updateButton;
    private Button deleteButton;
    private Button getButton;
    private TextView battleID;
    private TextView healthGet;
    private TextView dmgGet;
    private TextView flashGet;
    private TextView defenseGet;
    private TextView nameGet;

    private boolean bossExist;
    private ImageView onlineStar;
    private ImageView offlineStar;
    private TextView onlineList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_create);


        smallEnemies = findViewById(R.id.smallEnemies);
        largeEnemies = findViewById(R.id.largeEnemies);
        bossNum = findViewById(R.id.bossID);
        battlePost = findViewById(R.id.postButton);
        updateButton = findViewById(R.id.updateButtonBattle);
        deleteButton = findViewById(R.id.deleteButtonBattle);
        battleID = findViewById(R.id.battleID);
        displayText = findViewById(R.id.displayText);
        getButton = findViewById(R.id.getButtonBattle);
        onlineStar = findViewById(R.id.onlineStar);
        offlineStar = findViewById(R.id.offlineStar);
        onlineList = findViewById(R.id.onlineList);
        healthGet = findViewById(R.id.healthInput);
        dmgGet = findViewById(R.id.dmgInput);
        flashGet = findViewById(R.id.cardInput);
        defenseGet = findViewById(R.id.defenseInput);
        nameGet = findViewById(R.id.bossNameInput);

        if (onlineCheck()){
            offlineStar.setVisibility(View.GONE);
            onlineStar.setVisibility(View.VISIBLE);
            onlineList.setText(getOnlineList().toString());
        } else{
            offlineStar.setVisibility(View.VISIBLE);
            onlineStar.setVisibility(View.GONE);
        }

        battlePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battleJSON = new JSONObject();

                try {
                    battleJSON.put("smallEnemiesCount", smallEnemies.getText().toString());
                    battleJSON.put("largeEnemiesCount", largeEnemies.getText().toString());
                   // battleJSON.put("boss", bossNum.getText().toString());
                    battleJSON.put("name", nameGet.getText().toString());
                    battleJSON.put("health", healthGet.getText().toString());
                    battleJSON.put("attack", dmgGet.getText().toString());
                    battleJSON.put("defense", defenseGet.getText().toString());
                    battleJSON.put("flashcards", flashGet.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReq();


            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battleJSON = new JSONObject();

                try {
                    battleJSON.put("smallEnemiesCount", smallEnemies.getText().toString());
                    battleJSON.put("largeEnemiesCount", largeEnemies.getText().toString());
                    //battleJSON.put("boss", bossNum.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReqPut();


            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReqDelete();
            }
        });
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReqGet();
            }
        });

    }



    private void makeJsonObjReq() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.POST,
                baseURL + "battles",
                battleJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        displayText.setText(response.toString());

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
    private void makeJsonObjReqPut() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.PUT,
                baseURL + "battle/" + battleID.getText().toString(),
                battleJSON,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        displayText.setText(response.toString());


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
    private void makeJsonObjReqGet() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.GET,
                baseURL + "battle/" + battleID.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        displayText.setText(response.toString());



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

    private void makeJsonObjReqDelete() {




        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.DELETE,
                baseURL + "battle/" + battleID.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        displayText.setText(response.toString());



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

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };


        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }




}
