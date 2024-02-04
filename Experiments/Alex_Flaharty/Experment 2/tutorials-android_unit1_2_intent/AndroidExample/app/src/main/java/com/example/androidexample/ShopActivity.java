package com.example.androidexample;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class ShopActivity extends AppCompatActivity {
    private Button More;
    private Button less;
    private int Level;
    private TextView Cost_txt;
    int Cost;
    int Final_Cost;
    private TextView Skill;
    private TextView Final_Cost_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Level = 0;
        Cost = 100;
        More = findViewById(R.id.More);
        less = findViewById(R.id.Less);
        Cost_txt = findViewById(R.id.Cost);
        Final_Cost_txt = findViewById(R.id.Final_Cost);
        Skill = findViewById(R.id.Skill_Level);
        Cost_txt.setText(String.valueOf(Cost));
        Skill.setText("1");
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Level++;
                Skill.setText(String.valueOf(Level));
                Final_Cost = Cost -(5*Level);
                Final_Cost_txt.setText(String.valueOf(Final_Cost));
            }
        });
        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Level--;
                Skill.setText(String.valueOf(Level));
                Final_Cost = Cost -(5*Level);
                Final_Cost_txt.setText(String.valueOf(Final_Cost));
            }
        });
        Final_Cost = Cost -(5*Level);
        Final_Cost_txt.setText(String.valueOf(Final_Cost));

    }
}
