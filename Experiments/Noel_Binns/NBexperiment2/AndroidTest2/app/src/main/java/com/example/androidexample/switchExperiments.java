package com.example.androidexample;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class switchExperiments extends AppCompatActivity {
    private TextView messageText;
    private TextView explainText;
    private Switch switchOne;
    private Switch switchTwo;

    private Switch switchThree;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_test);
        messageText = findViewById(R.id.switchText);
        explainText = findViewById(R.id.explainText);

        switchOne = findViewById(R.id.switch1);
        switchTwo = findViewById(R.id.switch2);
        switchThree = findViewById(R.id.switch3);
switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (switchOne.isChecked()) {
            switchTwo.setChecked(false);
            switchThree.setChecked(false);
        }
    }
});
        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (switchTwo.isChecked()) {
                    switchOne.setChecked(false);
                    switchThree.setChecked(false);
                }
            }
        });
        switchThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (switchThree.isChecked()) {
                    switchTwo.setChecked(false);
                    switchOne.setChecked(false);
                }
            }
        });
    }
}
