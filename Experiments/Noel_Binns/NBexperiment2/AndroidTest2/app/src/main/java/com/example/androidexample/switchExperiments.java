package com.example.androidexample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class switchExperiments extends AppCompatActivity {
    private TextView messageText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_test);
        messageText = findViewById(R.id.switchText);


    }
}
