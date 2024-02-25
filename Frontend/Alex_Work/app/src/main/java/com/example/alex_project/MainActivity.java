package com.example.alex_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView answer;
    private TextView Wrong_1;
    private TextView Wrong_2;
    private TextView Wrong_3;
    private TextView Question;
    private TextView Card_Number;
    private int Card_Number_int;
    private Button Home;
    private Button Back;
    private Button Save;
    private Button Next;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = findViewById(R.id.Correct);
        Wrong_1 = findViewById(R.id.Wrong_1);
        Wrong_2 = findViewById(R.id.Wrong_2);
        Wrong_3 = findViewById(R.id.Wrong_3);
        Question = findViewById(R.id.Question);
        Card_Number = findViewById(R.id.Card_number);
        Home = findViewById(R.id.Home);
        Back = findViewById(R.id.Prev_Card);
        Next = findViewById(R.id.Next_Card);
        Save = findViewById(R.id.Save);
        Card_Number_int = 1;
        Card_Number.setText("1");
        //todo pull data from server
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int++;
                Card_Number.setText(Integer.toString(Card_Number_int));
                //todo pull new data from server
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card_Number_int = Math.max(1,Card_Number_int-1);
                Card_Number.setText(Integer.toString(Card_Number_int));
                //todo pull new data from server
            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // todo send data to server
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo go to admin
            }
        });

    }
}
