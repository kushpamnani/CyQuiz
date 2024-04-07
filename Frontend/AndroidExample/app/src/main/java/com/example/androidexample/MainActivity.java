package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidexample.Map.MapActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button strBtn, jsonObjBtn, jsonArrBtn, imgBtn, flashCardBtn, adminBtn, signout, map, enemyCreateBtn,anouncments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anouncments= findViewById(R.id.anouncments);
        strBtn = findViewById(R.id.btnStringRequest);
        jsonObjBtn = findViewById(R.id.btnJsonObjRequest);
        jsonArrBtn = findViewById(R.id.btnJsonArrRequest);
        imgBtn = findViewById(R.id.btnImageRequest);
        flashCardBtn = findViewById(R.id.btnFlashCard);
        adminBtn = findViewById(R.id.btnAdmin);
        signout = findViewById(R.id.btnSIgnOut);
        map = findViewById(R.id.btnMapTest);
        enemyCreateBtn = findViewById(R.id.btnEnemyCreate);

        /* button click listeners */
        strBtn.setOnClickListener(this);
        jsonObjBtn.setOnClickListener(this);
        jsonArrBtn.setOnClickListener(this);
        imgBtn.setOnClickListener(this);
        flashCardBtn.setOnClickListener(this);
        adminBtn.setOnClickListener(this);
        signout.setOnClickListener(this);
        map.setOnClickListener(this);
        enemyCreateBtn.setOnClickListener(this);
        anouncments.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnStringRequest) {
            startActivity(new Intent(MainActivity.this, StringReqActivity.class));
        } else if (id == R.id.btnJsonObjRequest) {
            startActivity(new Intent(MainActivity.this, JsonObjReqActivity.class));
        } else if (id == R.id.btnJsonArrRequest) {
            startActivity(new Intent(MainActivity.this, JsonArrReqActivity.class));
        } else if (id == R.id.btnImageRequest) {
            startActivity(new Intent(MainActivity.this, ImageReqActivity.class));
        } else if (id == R.id.btnFlashCard) {
            startActivity(new Intent(MainActivity.this, FlashCardActivity.class));
        } else if (id == R.id.btnAdmin) {
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        } else if (id == R.id.btnSIgnOut) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.btnMapTest) {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        } else if (id == R.id.btnEnemyCreate){
            startActivity(new Intent(MainActivity.this, EnemyCreatorActivity.class));
        }
        else if (id == R.id.anouncments){
            startActivity(new Intent(MainActivity.this, ChatActivity.class));
        }
    }
}