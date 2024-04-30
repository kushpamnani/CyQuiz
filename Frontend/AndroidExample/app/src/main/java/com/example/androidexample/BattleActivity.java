package com.example.androidexample;

import static com.example.androidexample.Map.MapActivity.afterbattleHp;
import static com.example.androidexample.Map.MapActivity.getHp;
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
import com.example.androidexample.Map.MapActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BattleActivity extends AppCompatActivity {
    private String baseURL = "http://coms-309-031.class.las.iastate.edu:8080";
    private TextView battleID;
    private Button startButton;
    private String responseJSON = "";
    private int smallEnemies;
    private int largeEnemies;
    private static int health = 100;
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
    private int bossHealth, bossAttack, bossDefense;
    private String answer, opt1, opt2, opt3, opt4, question;
    private TextView answerBox;
    private Button dodgeButton;
    private TextView questionBox, answerOne, answerTwo, answerThree, answerFour;

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
        answerBox = findViewById(R.id.answerBox);
        dodgeButton = findViewById(R.id.dodgeButton);
        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);
        answerFour = findViewById(R.id.answerFour);
        questionBox = findViewById(R.id.questionBox);
        health = getHp();

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
        dodgeButton.setVisibility(View.GONE);
        answerBox.setVisibility(View.GONE);
        enemyLeftText.setVisibility(View.GONE);
        enemyDefenseText.setVisibility(View.GONE);
        enemyHealthText.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        battleID.setVisibility(View.VISIBLE);
        answerOne.setVisibility(View.GONE);
        answerTwo.setVisibility(View.GONE);
        answerThree.setVisibility(View.GONE);
        answerFour.setVisibility(View.GONE);
        questionBox.setVisibility(View.GONE);


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
        dodgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answerBox.getText().toString().equals(answer)){
                    enemyLeftText.setText("YOU WIN!");
                    dodgeButton.setVisibility(View.GONE);
                    answerBox.setVisibility(View.GONE);
                    afterbattleHp(health);
                    finish();
                }else{
                    enemyLeftText.setText("You missed the question and got hit!");
                    health = health - 25;
                    healthNum.setText(Integer.toString(health));
                    healthBar.setProgress(health);
                    if (health <= 0){
                        dodgeButton.setVisibility(View.GONE);
                        answerBox.setVisibility(View.GONE);
                        answerOne.setVisibility(View.GONE);
                        answerTwo.setVisibility(View.GONE);
                        answerThree.setVisibility(View.GONE);
                        answerFour.setVisibility(View.GONE);
                        questionBox.setVisibility(View.GONE);
                        swordHit.setVisibility(View.GONE);
                        spearHit.setVisibility(View.GONE);
                        healthNum.setVisibility(View.GONE);
                        healthBar.setVisibility(View.GONE);
                        nextFight.setVisibility(View.GONE);
                        dodgeButton.setVisibility(View.GONE);
                        answerBox.setVisibility(View.GONE);
                        enemyDefenseText.setVisibility(View.GONE);
                        enemyHealthText.setVisibility(View.GONE);
                        enemyLeftText.setText("YOU HAVE DIED!");

                    }
                }
            }
        });
        nextFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swordHit.setVisibility(View.VISIBLE);
                spearHit.setVisibility(View.VISIBLE);
                nextFight.setVisibility(View.GONE);
                if(fightingLarge) {
                    battleFight();
                    enemyLeftText.setText("large enemies left: " + Integer.toString(largeEnemies));
                    enemyHealthText.setText("large health left: " + Integer.toString(largeHealth));
                    enemyDefenseText.setText("enemy defense: " + Integer.toString(largeDefense));
                } else if(fightingBoss){
                    enemyLeftText.setText("You are fighting the boss!!");
                    enemyHealthText.setText("Boss health left: " +  Integer.toString(bossHealth));
                    enemyDefenseText.setText("enemy defense: " +  Integer.toString(bossDefense));

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
                        health = health-2;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
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
                            fightingBoss = true;
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
                        health = health-5;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
                    }

                }
                else if (fightingBoss){
                    enemyLeftText.setText("You are fighting the boss!!");
                    enemyHealthText.setText("Boss health left: " +  Integer.toString(bossHealth));
                    enemyDefenseText.setText("Boss defense: " +  Integer.toString(bossDefense));
                    if (bossDefense < 15) {
                        bossHealth = bossHealth - (15-bossDefense);
                        enemyHealthText.setText("Boss health left: " +  Integer.toString(bossHealth));
                    }
                    if (bossHealth <= 0){
                        enemyLeftText.setText("The boss has been slain! Watch out for the final attack!");
                        enemyDefenseText.setVisibility(View.GONE);
                        enemyHealthText.setVisibility(View.GONE);
                        dodgeButton.setVisibility(View.VISIBLE);
                        answerBox.setVisibility(View.VISIBLE);
                        swordHit.setVisibility(View.GONE);
                        spearHit.setVisibility(View.GONE);
                        answerOne.setVisibility(View.VISIBLE);
                        answerTwo.setVisibility(View.VISIBLE);
                        answerThree.setVisibility(View.VISIBLE);
                        answerFour.setVisibility(View.VISIBLE);
                        questionBox.setVisibility(View.VISIBLE);


                    } else{
                        health = health-bossAttack;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
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
                        health = health-2;
                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
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
                            fightingBoss = true;
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
                        health = health-5;

                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
                    }

                }
                else if (fightingBoss){
                    enemyLeftText.setText("You are fighting the boss!!");
                    enemyHealthText.setText("Boss health left: " +  Integer.toString(bossHealth));
                    enemyDefenseText.setText("Boss defense: " +  Integer.toString(bossDefense));
                    bossHealth = bossHealth - 7;
                        enemyHealthText.setText("Boss health left: " +  Integer.toString(bossHealth));

                    if (bossHealth <= 0){
                        enemyLeftText.setText("The boss has been slain! Watch out for the final attack!");
                        enemyDefenseText.setVisibility(View.GONE);
                        enemyHealthText.setVisibility(View.GONE);
                        dodgeButton.setVisibility(View.VISIBLE);
                        answerBox.setVisibility(View.VISIBLE);
                        swordHit.setVisibility(View.GONE);
                        spearHit.setVisibility(View.GONE);
                        answerOne.setVisibility(View.VISIBLE);
                        answerTwo.setVisibility(View.VISIBLE);
                        answerThree.setVisibility(View.VISIBLE);
                        answerFour.setVisibility(View.VISIBLE);
                        questionBox.setVisibility(View.VISIBLE);
                        questionBox.setText(question);




                    } else{
                        health = health-bossAttack;

                        healthNum.setText(Integer.toString(health));
                        healthBar.setProgress(health);
                        if (health <= 0){
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            answerOne.setVisibility(View.GONE);
                            answerTwo.setVisibility(View.GONE);
                            answerThree.setVisibility(View.GONE);
                            answerFour.setVisibility(View.GONE);
                            questionBox.setVisibility(View.GONE);
                            swordHit.setVisibility(View.GONE);
                            spearHit.setVisibility(View.GONE);
                            healthNum.setVisibility(View.GONE);
                            healthBar.setVisibility(View.GONE);
                            nextFight.setVisibility(View.GONE);
                            dodgeButton.setVisibility(View.GONE);
                            answerBox.setVisibility(View.GONE);
                            enemyDefenseText.setVisibility(View.GONE);
                            enemyHealthText.setVisibility(View.GONE);
                            enemyLeftText.setText("YOU HAVE DIED!");

                        }
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
                        JSONObject helper, helper2;
                        helper = response.optJSONObject("boss");


                          smallEnemies = response.optInt("smallEnemiesCount");


                         largeEnemies = response.optInt("largeEnemiesCount");
                        bossHealth = helper.optInt("health");
                         bossAttack = helper.optInt("attack");
                         bossDefense = helper.optInt("defense");
                         helper2 = helper.optJSONObject("flashcard");
                         answer = helper2.optString("answer");
                        opt1 = helper2.optString("option1");
                        opt2 = helper2.optString("option2");
                        opt3 = helper2.optString("option3");
                        question = helper2.optString("question");
                        questionBox.setText(question);
                        ArrayList<String> answerList = new ArrayList<String>();
                        answerList.add(opt1);
                        answerList.add(opt2);
                        answerList.add(opt3);
                        answerList.add(answer);
                        Collections.shuffle(answerList);
                        answerOne.setText(answerList.get(0));
                        answerTwo.setText(answerList.get(1));
                        answerThree.setText(answerList.get(2));
                        answerFour.setText(answerList.get(3));





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
    public static int healthGet() {return health;}


}
