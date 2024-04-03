package com.example.androidexample.Map;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexample.MainActivity;
import com.example.androidexample.R;
import com.example.androidexample.Map.MapGenerator;
import com.example.androidexample.Map.Tiles;

public class MapActivity extends AppCompatActivity {
    static Button a_1,a_2,a_3,b_1,b_2,b_3,b_4,c_1,c_2,boss;
    static int hp;
    static char a_1_Type,a_2_Type,a_3_Type,b_1_Type,b_2_Type,b_3_Type,b_4_Type,c_1_Type,c_2_Type;
    String positon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        boss = findViewById(R.id.btnBoss);
        positon="start";
        MapGenerator map = new MapGenerator();
        Tiles events = new Tiles();
        map.NewMap();
        a_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon="a_1";
                    events.event(a_1_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        a_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon = "a_2";
                    events.event(a_2_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        a_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="start"){
                    positon = "a_3";
                    events.event(a_3_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_1") {
                    positon = "b_1";
                    events.event(b_1_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_2"||positon=="a_1"){
                    positon = "b_2";
                    events.event(b_2_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_3"||positon=="a_2"){
                    positon = "b_3";
                    events.event(b_3_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="a_3"){
                    positon = "b_4";
                    events.event(b_4_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        c_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="b_1"||positon=="b_2"){
                    positon = "c_1";
                    events.event(c_1_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }

            }
        });
        c_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="b_3"||positon=="b_4"){
                    positon = "c_2";
                    events.event(c_2_Type);
                    startActivity(new Intent(MapActivity.this, Tiles.class));
                }
            }
        });
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positon=="c_2"||positon=="c_1"){
                    positon = "a_1";
                }

            }
        });
    }
    void setlayout(String layout){
        if(layout == "quiz"){
            setContentView(R.layout.event_quiz);
        }
    }

}
