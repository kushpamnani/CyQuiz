package com.example.androidexample.Map;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidexample.MainActivity;
import com.example.androidexample.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Tiles extends MapActivity {
    char type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(type=='0'){
            //todo call battle
        }
        else if(type=='1'){
            hp= Math.min(100,hp+20);
        }
        else if(type=='2'){
            RandomEvents();
        }
        else{
            Quiz();
        }

    }

    void event(char type){
        //0 is fight
        //1 is Heal
        //2 is Random Event
        //3 is Quiz
        this.type = type;
    }
    void Quiz(){
        String[] card = new String[]{"what doing?","a","b","c","d"};
        setContentView(R.layout.event_quiz);
        Button response1 = findViewById(R.id.btnResponse_1);
        Button response2 = findViewById(R.id.btnResponse_2);
        Button response3 = findViewById(R.id.btnResponse_3);
        Button response4 = findViewById(R.id.btnResponse_4);
        TextView Question = findViewById(R.id.testQuestion);
        Question.setText(card[0]);
        Random rand = new Random();
        int i = Math.abs(rand.nextInt());
        int x = 1;
        while (x<5){
            i= (i%4)+1;
            if(i==1){
                response1.setText(card[x]);
            }
            if(i==2){
                response3.setText(card[x]);
            }
            if(i==3){
                response2.setText(card[x]);
            }
            if(i==4){
                response4.setText(card[x]);
            }
            x++;
        }
    }
    void RandomEvents(){

    }
}
