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
    private int health;
    private int[] smallEnemyTemp = new int[3];
    private int[] largeEnemyTemp = new int[3];
    private int smallHealth, smallAttack, smallDefense;
    private int largeHealth, largeAttack, largeDefense;
    private Button spearHit, swordHit;
    private boolean fightingSmall, fightingLarge, fightingBoss;

    private TextView debugging;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battleID = findViewById(R.id.battleIDInput);
        startButton = findViewById(R.id.startBattleBtn);
        debugging = findViewById(R.id.debugging);
        swordHit = findViewById(R.id.swordHit);
        spearHit = findViewById(R.id.spearHit);

        swordHit.setVisibility(View.GONE);
        spearHit.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        battleID.setVisibility(View.VISIBLE);
        fightingSmall = true;
        fightingLarge = true;
        fightingBoss = false;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!battleID.getText().toString().equals("")){
                    makeJsonObjReqGet();
                }
            }
        });
        swordHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fightingSmall()){
                    debugging.setText(Integer.toString(smallEnemies));
                    smallHealth = smallHealth - (15-smallDefense);
                    if (smallHealth <= 0){
                       // smallEnemies = smallEnemies-1;
                        debugging.setText(Integer.toString(smallEnemies));

                    }
                }
            }
        });
        spearHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                            swordHit.setVisibility(View.VISIBLE);
                            spearHit.setVisibility(View.VISIBLE);
                        }


                          smallEnemies = response.optInt("smallEnemiesCount");


                         largeEnemies = response.optInt("largeEnemiesCount");

                        battleFight();



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
/*
temp array holds in this order: Health, Attack, Defense. take in that order.
 */
    private void battleFight(){
           smallEnemyTemp = smallEnemyCreate();
           smallHealth = smallEnemyTemp[0];
           smallAttack = smallEnemyTemp[1];
           smallDefense = smallEnemyTemp[2];
           debugging.setText(Integer.toString(smallHealth));




    }
    private int[] smallEnemyCreate(){
        smallEnemyTemp[0] = (int)Math.floor(Math.random() * (20 - 5 + 1) + 5);
        smallEnemyTemp[1] = (int)Math.floor(Math.random() * (8 - 2 + 1) + 2);
        smallEnemyTemp[2] = (int)Math.floor(Math.random() * (10 - 2 + 1) + 2);

        return smallEnemyTemp;
    }
    private int[] largeEnemyCreate(){
        largeEnemyTemp[0] = (int)Math.floor(Math.random() * (35 - 10 + 1) + 5);
        largeEnemyTemp[1] = (int)Math.floor(Math.random() * (12 - 5 + 1) + 5);
        largeEnemyTemp[2] = (int)Math.floor(Math.random() * (15 - 5 + 1) + 5);

        return largeEnemyTemp;
    }
    private boolean fightingSmall(){
        if (smallEnemies == 0){
            fightingSmall = false;
        }

        return fightingSmall;
    }
    private boolean fightingLarge(){
        if (largeEnemies == 0){
            fightingLarge = false;
        }

        return fightingLarge;
    }
    private boolean fightingBoss(){
        if (!fightingSmall && !fightingLarge){
            fightingBoss = true;
        }

        return fightingLarge;
    }
    public int healthGet() {return health;}


}
