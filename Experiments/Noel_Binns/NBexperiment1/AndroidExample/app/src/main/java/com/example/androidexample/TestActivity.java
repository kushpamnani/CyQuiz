package com.example.androidexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private TextView messageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// link to Main activity XML
        Button simpleButton = (Button) findViewById(R.id.simpleButton);
        simpleButton.setVisibility(View.GONE);
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setVisibility(View.VISIBLE);
        simpleButton.setBackgroundColor(Color.BLACK);

        /* initialize UI elements */
        messageText = findViewById(R.id.main_msg_txt);      // link to message textview in the Main activity XML
        messageText.setText("Test Worked. This is a new window.");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "This Button has worked. Back window.", Toast.LENGTH_LONG).show();//display the text of button1
            }
        });

    }
}
