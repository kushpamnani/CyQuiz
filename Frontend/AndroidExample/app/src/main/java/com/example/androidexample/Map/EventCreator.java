package com.example.androidexample.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexample.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EventCreator extends AppCompatActivity {
    TextView title,hpmin,hpmax,healamount;
    Button save;
    Checkable morehp,lesshp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creator);
        healamount = findViewById(R.id.HealAmount);
        hpmin = findViewById(R.id.lessHeath);
        hpmax = findViewById(R.id.moreHeath);
        title= findViewById(R.id.EventTitle);
        morehp = findViewById(R.id.checkBoxMorehp);
        lesshp = findViewById(R.id.checkBoxLesshp);
        save = findViewById(R.id.EventSave);
        JSONObject event = new JSONObject();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    event.put("title",title.getText().toString());
                    if (morehp.isChecked()&&lesshp.isChecked()){
                        event.put("Condition 1",hpmax.getText().toString());
                        event.put("Condition 2",hpmin.getText().toString());
                    }
                    else if (morehp.isChecked()){
                        event.put("Condition 1",hpmax.getText().toString());
                        event.put("Condition 2", "");
                    }
                    else if (lesshp.isChecked()){
                        event.put("Condition 1","");
                        event.put("Condition 2", hpmin.getText().toString());
                    }
                    else{
                        event.put("Condition 1","");
                        event.put("Condition 2", "");
                    }
                    event.put("Hp change",healamount.getText().toString());
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
