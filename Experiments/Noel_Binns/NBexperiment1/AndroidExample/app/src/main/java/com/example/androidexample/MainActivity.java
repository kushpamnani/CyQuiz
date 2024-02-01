package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView messageText;   // define message textview variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);             // link to Main activity XML
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);
        /* initialize UI elements */
        messageText = findViewById(R.id.main_msg_txt);      // link to message textview in the Main activity XML
        messageText.setText("Hello World");
        Button simpleButton = (Button) findViewById(R.id.simpleButton);
        simpleButton.setBackgroundColor(Color.BLACK);//set the black color of button background
        simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "This Button has worked. Next window.", Toast.LENGTH_LONG).show();//display the text of button1
            }
        });
    }
}
