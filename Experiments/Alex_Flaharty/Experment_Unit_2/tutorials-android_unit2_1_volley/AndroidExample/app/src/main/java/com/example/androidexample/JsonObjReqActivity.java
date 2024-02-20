package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjReqActivity extends AppCompatActivity {

    private Button btnJsonObjReq;
    private Button flash_button;
    private TextView msgResponse;
    private TextView object;
    private TextView title;
    private TextView question;
    private TextView answer;
    private TextView wrong_1;
    private TextView wrong_2;
    private TextView wrong_3;
    private String type;
    private String name;
    private String title_txt;
    private String question_txt;
    private String correct;
    private String fail_1;
    private String fail_2;
    private String fail_3;
    private int request_type;

    //private static final String URL_JSON_OBJECT = "https://jsonplaceholder.typicode.com/users/1";
    //private static final String URL_JSON_OBJECT = "http://date.jsontest.com/";
    private static final String URL_JSON_OBJECT = "https://270850cd-16f4-4610-8e1b-a64f07b81113.mock.pstmn.io/name/";
    private static final String URL_JSON_Card = "https://270850cd-16f4-4610-8e1b-a64f07b81113.mock.pstmn.io/card/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_obj_req);

        btnJsonObjReq = findViewById(R.id.btnJsonObj);
        flash_button = findViewById(R.id.card_button);
        title = findViewById(R.id.card_title);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        wrong_1 = findViewById(R.id.wrong_1);
        wrong_2 = findViewById(R.id.wrong_2);
        wrong_3 = findViewById(R.id.wrong_3);
        msgResponse = findViewById(R.id.Type);
        object = findViewById(R.id.Object);

        btnJsonObjReq.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                request_type =0;
                makeJsonObjReq(URL_JSON_OBJECT);
            }
        });
        flash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request_type =1;
                makeJsonObjReq(URL_JSON_Card);
            }
        });
    }

    /**
     * Making json object request
     */
    private void makeJsonObjReq(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        JSONParse(response.toString());
                        if(request_type==0){
                            msgResponse.setText(type);
                            object.setText(name);
                        }
                        else{
                            title.setText(title_txt);
                            question.setText(question_txt);
                            answer.setText(correct);
                            wrong_1.setText(fail_1);
                            wrong_2.setText(fail_2);
                            wrong_3.setText(fail_3);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };
        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    private void JSONParse(String text){
        type = "";
        name = "";
        title_txt="";
        question_txt="";
        correct="";
        fail_1="";
        fail_2="";
        fail_3="";
        int z = 0;
        int i = 0;
        int x =text.length();
        while(i<x){
            if(text.charAt(i)== '\"'){
                z++;
            }
            else{
                if(request_type==0){
                    if(z==1){
                        type =type + text.charAt(i);
                    }
                    if(z==3){
                        name = name + text.charAt(i);
                    }
                }
                else{
                    if(z==3){
                        title_txt =title_txt + text.charAt(i);
                    }
                    if(z==7){
                        question_txt = question_txt + text.charAt(i);
                    }
                    if(z==11){
                        correct =correct + text.charAt(i);
                    }
                    if(z==15){
                        fail_1 = fail_1 + text.charAt(i);
                    }
                    if(z==19){
                        fail_2 =fail_2 + text.charAt(i);
                    }
                    if(z==23){
                        fail_3 = fail_3 + text.charAt(i);
                    }
                }
            }
            i++;
        }
    }

}