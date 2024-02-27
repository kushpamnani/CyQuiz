package com.example.alex_project;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AdminActivity extends AppCompatActivity {
    private TextView Title;
    private TextView User_name;
    private TextView Banned_list;
    private Button Ban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Title = findViewById(R.id.Title);
        User_name = findViewById(R.id.Ban);
        Banned_list = findViewById(R.id.Banned_List);
        Ban = findViewById(R.id.Ban_button);
        Ban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                // Add Username to ban list and refresh banned list
            }
        });

    }
}
