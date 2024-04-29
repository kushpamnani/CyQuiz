package com.example.androidexample;

import static com.example.androidexample.OnlineTrackerActivity.getOnlineList;
import static com.example.androidexample.OnlineTrackerActivity.onlineCheck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
    private int health = 100;
    private int[] smallEnemyTemp = new int[3];
    private int[] largeEnemyTemp = new int[3];
    private int smallHealth, smallAttack, smallDefense;
    private int largeHealth, largeAttack, largeDefense;
    private Button spearHit, swordHit, nextFight;
    private boolean fightingSmall, fightingLarge, fightingBoss;
    private TextView healthNum;
    private ProgressBar healthBar;
    private ImageView onlineStar;
    private ImageView offlineStar;
    private TextView onlineList;

    private TextView debugging, enemyHealthText, enemyDefenseText, enemyLeftText;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battleID = findViewById(R.id.battleIDInput);
        startButton = findViewById(R.id.startBattleBtn);
        debugging = findViewById(R.id.debugging);
        swordHit = findViewById(R.id.swordHit);
        spearHit = findViewById(R.id.spearHit);
        enemyHealthText = findViewById(R.id.enemyHealth);
        enemyDefenseText = findViewById(R.id.enemyDefense);
        enemyLeftText = findViewById(R.id.enemyLeft);
        nextFight = findViewById(R.id.nextFight);
        healthBar = findViewById(R.id.healthBar);
        healthNum = findViewById(R.id.healthNum);
        onlineStar = findViewById(R.id.onlineStar);
        offlineStar = findViewById(R.id.offlineStar);
        onlineList = findViewById(R.id.onlineList);
        if (onlineCheck()){
            offlineStar.setVisibility(View.GONE);
            onlineStar.setVisibility(View.VISIBLE);
            onlineList.setText(getOnlineList().toString());
        } else{
            offlineStar.setVisibility(View.VISIBLE);
            onlineStar.setVisibility(View.GONE);
        }

        swordHit.setVisibility(View.GONE);
        spearHit.setVisibility(View.GONE);
        healthNum.setVisibility(View.GONE);
        healthBar.setVisibility(View.GONE);
        nextFight.setVisibility(View.GONE);
        enemyLeftText.setVisibility(View.GONE);
        enemyDefenseText.setVisibility(View.GONE);
        enemyHealthText.setVisibility(View.GONE);
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
        nextFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swordHit.setVisibility(View.VISIBLE);
                spearHit.setVisibility(View.VISIBLE);
                nextFight.setVisibility(View.GONE);
                if(!fightingSmall && fightingLarge) {
                    battleFight();
                    enemyLeftText.setText("large enemies left: " + Integer.toString(largeEnemies));
                    enemyHealthText.setText("large health left: " + Integer.toString(largeHealth));
                    enemyDefenseText.setText("enemy defense: " + Integer.toString(largeDefense));
                }

            }
        });
        swordHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fightingSmall()){
                    enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                    enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));
                    enemyDefenseText.setText("enemy defense: " +  Integer.toString(smallDefense));
                    smallHealth = smallHealth - (15-smallDefense);
                    enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));

                    if (smallHealth <= 0){
                       if (smallEnemies == 1){
                           enemyLeftText.setText("you have beaten all of the small enemies");
                       fightingSmall = false;
                           swordHit.setVisibility(View.GONE);
                           spearHit.setVisibility(View.GONE);
                           nextFight.setVisibility(View.VISIBLE);
                       }
                       else{
                           smallEnemies = smallEnemies-1;
                           enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                           battleFight();
                           enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                           enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));
                           enemyDefenseText.setText("enemy defense: " +  Integer.toString(smallDefense));

                       }

                    }
                    else{
                        health = health-3;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                    }
                }
                else if (fightingLarge()) {
                    enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                    enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));
                    enemyDefenseText.setText("enemy defense: " +  Integer.toString(largeDefense));
                    if (largeDefense < 15) {
                        largeHealth = largeHealth - (15-largeDefense);
                        enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));

                    }
                    if (largeHealth <= 0){
                        if (largeEnemies == 1){
                            enemyLeftText.setText("you have beaten all of the large enemies");
                            fightingLarge = false;
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            nextFight.setVisibility(View.VISIBLE);
                        }
                        else{
                            largeEnemies = largeEnemies-1;
                            enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                            battleFight();
                            enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                            enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));
                            enemyDefenseText.setText("enemy defense: " +  Integer.toString(largeDefense));

                        }

                    } else{
                        health = health-7;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                    }

                }
            }
        });
        spearHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fightingSmall()){
                    enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                    enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));
                    enemyDefenseText.setText("enemy defense: " +  Integer.toString(smallDefense));
                    smallHealth = smallHealth - 7;
                    enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));

                    if (smallHealth <= 0){
                        if (smallEnemies == 1){
                            enemyLeftText.setText("you have beaten all of the small enemies");
                            fightingSmall = false;
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            nextFight.setVisibility(View.VISIBLE);
                        }
                        else{
                            smallEnemies = smallEnemies-1;
                            enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                            battleFight();
                            enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                            enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));
                            enemyDefenseText.setText("enemy defense: " +  Integer.toString(smallDefense));

                        }

                    }
                    else{
                        health = health-3;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                    }
                }
                else if (fightingLarge()) {
                    enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                    enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));
                    enemyDefenseText.setText("enemy defense: " +  Integer.toString(largeDefense));
                        largeHealth = largeHealth - 7;
                        enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));
                    if (largeHealth <= 0){
                        if (largeEnemies == 1){
                            enemyLeftText.setText("you have beaten all of the large enemies");
                            fightingLarge = false;
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            nextFight.setVisibility(View.VISIBLE);
                        }
                        else{
                            largeEnemies = largeEnemies-1;
                            enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                            battleFight();
                            enemyLeftText.setText("large enemies left: " +  Integer.toString(largeEnemies));
                            enemyHealthText.setText("large health left: " +  Integer.toString(largeHealth));
                            enemyDefenseText.setText("enemy defense: " +  Integer.toString(largeDefense));

                        }

                    } else{
                        health = health-7;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                    }

                }
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
                            enemyLeftText.setVisibility(View.VISIBLE);
                            enemyDefenseText.setVisibility(View.VISIBLE);
                            enemyHealthText.setVisibility(View.VISIBLE);
                            healthNum.setVisibility(View.VISIBLE);
                            healthBar.setVisibility(View.VISIBLE);
                            healthNum.setText(Integer.toString(health));
                            healthBar.setProgress(health);
                        }


                          smallEnemies = response.optInt("smallEnemiesCount");


                         largeEnemies = response.optInt("largeEnemiesCount");

                        battleFight();
                        enemyLeftText.setText("small enemies left: " +  Integer.toString(smallEnemies));
                        enemyHealthText.setText("enemy health left: " +  Integer.toString(smallHealth));
                        enemyDefenseText.setText("enemy defense: " +  Integer.toString(smallDefense));




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

        if (fightingSmall()) {
            smallEnemyTemp = smallEnemyCreate();
            smallHealth = smallEnemyTemp[0];
            smallAttack = smallEnemyTemp[1];
            smallDefense = smallEnemyTemp[2];
        }
        else if (fightingLarge()) {
            largeEnemyTemp = largeEnemyCreate();
            largeHealth = largeEnemyTemp[0];
            largeAttack = largeEnemyTemp[1];
            largeDefense = largeEnemyTemp[2];
        }else {

        }




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
        largeEnemyTemp[2] = (int)Math.floor(Math.random() * (14 - 5 + 1) + 5);


        return largeEnemyTemp;
    }
    private boolean fightingSmall(){
        if (smallEnemies <= 0){
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
