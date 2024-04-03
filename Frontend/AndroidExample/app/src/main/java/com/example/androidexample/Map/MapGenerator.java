package com.example.androidexample.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidexample.R;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Random;

class MapGenerator extends MapActivity {
    private String[] type= new String[]{"fight","Heal","Random Event","Test"};

    String NewMap(){
        String seed ="";
        Random rand = new Random();
        int x = 0;
        while(x<9){
            seed = seed +rand.nextInt(4);
            x++;
        }
        newMap(seed);
        return seed;
    }
    void newMap(String seed){
        hp = 100;
        a_1_Type= seed.charAt(0);
        a_1.setText(type[Character.getNumericValue(a_1_Type)]);
        a_2_Type= seed.charAt(1);
        a_2.setText(type[Character.getNumericValue(a_2_Type)]);
        a_3_Type= seed.charAt(2);
        a_3.setText(type[Character.getNumericValue(a_3_Type)]);
        b_1_Type= seed.charAt(3);
        b_1.setText(type[Character.getNumericValue(b_1_Type)]);
        b_2_Type= seed.charAt(4);
        b_2.setText(type[Character.getNumericValue(b_2_Type)]);
        b_3_Type= seed.charAt(5);
        b_3.setText(type[Character.getNumericValue(b_3_Type)]);
        b_4_Type= seed.charAt(6);
        b_4.setText(type[Character.getNumericValue(b_4_Type)]);
        c_1_Type= seed.charAt(7);
        c_1.setText(type[Character.getNumericValue(c_1_Type)]);
        c_2_Type= seed.charAt(8);
        c_2.setText(type[Character.getNumericValue(c_2_Type)]);
    }
}
