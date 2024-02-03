package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView messageText;// define message textview variable
    private TextView CrazyText;
    private Button CrazyButton;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);             // link to Main activity XML

        /* initialize UI elements */
        messageText = findViewById(R.id.main_msg_txt);      // link to message textview in the Main activity XML
        messageText.setText("Hello World");
        messageText = findViewById(R.id.bottem_text);
        messageText.setText("Bottem Text");
        messageText = findViewById(R.id.Upsidedown);
        messageText.setText("Upsidedown");
        CrazyText = findViewById(R.id.Do_Everything);
        CrazyText.setText("I am Normal");
        CrazyButton = findViewById(R.id.Crazy);
        CrazyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                CrazyText.setText("I am Crazy");
                CrazyText.setScaleX(2*x);
                CrazyText.setScaleY(6-x);
                CrazyText.setRotation((x*100));

            }
        });

    }
}