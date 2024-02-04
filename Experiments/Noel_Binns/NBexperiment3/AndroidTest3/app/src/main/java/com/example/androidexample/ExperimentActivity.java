package com.example.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExperimentActivity extends AppCompatActivity {
    private Button codeOne;
    private Button codeTwo;
    private Button codeThree;
    private Button check;
    private boolean unlocked;

    private boolean oneTrue;
    private boolean twoTrue;
    private boolean threeTrue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experiment_main);
        codeOne = findViewById(R.id.codeOne);
        codeTwo = findViewById(R.id.codeTwo);
        codeThree = findViewById(R.id.codeThree);
        check = findViewById(R.id.check);
        unlocked = false;
        oneTrue = false;
        twoTrue = false;
        threeTrue = false;


            codeOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (twoTrue && threeTrue){
                        unlocked = true;
                    }else{
                        twoTrue = false;
                        threeTrue = false;
                    }
                    oneTrue = true;


                }
            });
            codeTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            if (oneTrue || threeTrue){
                oneTrue = false;
                threeTrue = false;
            }
            twoTrue = true;
                }
            });
            codeThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (oneTrue){
                        twoTrue = false;
                        oneTrue = false;
                    }
                    threeTrue = true;

                }
            });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (unlocked){
                    Toast.makeText(getApplicationContext(), "You have unlocked the code", Toast.LENGTH_LONG).show();
                }

            }
        });






    }




}
