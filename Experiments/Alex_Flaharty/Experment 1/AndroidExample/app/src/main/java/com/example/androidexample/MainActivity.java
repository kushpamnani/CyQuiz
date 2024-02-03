package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView messageText;// define message textview variable
    private TextView CrazyText;
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
        CrazyText.setText("I am crazy");
        while (x<5){
            // wanted to have the text spin while it was open but caused crash with the wait statment
            // probly just cant have wait in main
            CrazyText.setScaleX(4);
            CrazyText.setScaleY(4);
            CrazyText.setRotation(5);
            CrazyText.setRotation(100);
            CrazyText.setScaleX(x-1);
            CrazyText.setScaleY(x);
            x++;
        }
    }
}