package com.example.androidexample;

import static com.example.androidexample.OnlineTrackerActivity.getOnlineList;
import static com.example.androidexample.OnlineTrackerActivity.onlineCheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class EnemyCreatorActivity extends AppCompatActivity{
    private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080";
    private Button teacherCheck, battleButton, bossButton;
    private TextView testText;
    private ImageView onlineStar;
    private ImageView offlineStar;
    private TextView onlineList;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_creator);

        teacherCheck = findViewById(R.id.teacherCheck);
        testText = findViewById(R.id.testingText);
        bossButton = findViewById(R.id.bossCreateButton);
        battleButton = findViewById(R.id.battleCreateButton);
        onlineStar = findViewById(R.id.onlineStar);
        offlineStar = findViewById(R.id.offlineStar);
        onlineList = findViewById(R.id.onlineList);
        battleButton.setVisibility(View.GONE);
        bossButton.setVisibility(View.GONE);
        if (onlineCheck()){
            offlineStar.setVisibility(View.GONE);
            onlineStar.setVisibility(View.VISIBLE);
            onlineList.setText(getOnlineList().toString());
        } else{
            offlineStar.setVisibility(View.VISIBLE);
            onlineStar.setVisibility(View.GONE);
        }





        teacherCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               teacherCheck.setVisibility(View.GONE);
               testText.setVisibility(View.GONE);
               battleButton.setVisibility(View.VISIBLE);
               bossButton.setVisibility(View.VISIBLE);
            }
        });
        bossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnemyCreatorActivity.this, BossCreatorActivity.class));

            }
        });
        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnemyCreatorActivity.this, BattleCreatorActivity.class));
            }
        });


    }



}
