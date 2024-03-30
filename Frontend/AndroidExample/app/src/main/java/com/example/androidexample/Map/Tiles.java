package com.example.androidexample.Map;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidexample.MainActivity;
import com.example.androidexample.R;

public class Tiles extends MapActivity {
    void event(char type){
        //0 is fight
        //1 is Heal
        //2 is Random Event
        //3 is Quiz
        if(type=='0'){
            //todo call battle
        }
        else if(type=='1'){
            hp= Math.min(100,hp+20);
        }
        else if(type=='2'){
            //todo call Events List
        }
        else{
            Quiz();
        }
    }
    void Quiz(){
    }
}
