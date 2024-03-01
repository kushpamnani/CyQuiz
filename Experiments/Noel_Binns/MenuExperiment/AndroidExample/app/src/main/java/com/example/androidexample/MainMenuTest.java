package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainMenuTest extends AppCompatActivity {
    private Button gamesButton;
    private Button classroomButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_test);

    gamesButton = findViewById(R.id.gameButton);
    classroomButton = findViewById(R.id.classroomButton);

        gamesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuTest.this, GameSelection.class);
                startActivity(intent);
            }
        });
        classroomButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuTest.this, ClassroomMain.class);
                startActivity(intent);
            }
        });









    }
}
