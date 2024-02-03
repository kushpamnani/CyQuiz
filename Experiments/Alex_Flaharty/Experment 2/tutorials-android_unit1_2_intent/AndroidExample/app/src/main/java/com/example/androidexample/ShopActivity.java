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
    private TextView Cost;
    private TextView Skill;
    private TextView Final_Cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        More = findViewById(R.id.More);
        less = findViewById(R.id.Less);
        Cost = findViewById(R.id.Normal_Cost_txt);
        Final_Cost = findViewById(R.id.Final_Cost);
        Skill = findViewById(R.id.Skill_level);
    }
}
