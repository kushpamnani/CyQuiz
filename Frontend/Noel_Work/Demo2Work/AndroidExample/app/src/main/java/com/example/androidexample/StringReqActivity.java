package com.example.androidexample;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.lastIndexOf;
import static android.text.TextUtils.substring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StringReqActivity extends AppCompatActivity {

    private Button btnStringReq;
    private TextView msgResponse;

    private TextView TestText;




   private static final String URL_STRING_REQ = "https://4438904a-d327-4dc4-a0d4-57eb49e95091.mock.pstmn.io/work_example/";
     // public static final String URL_STRING_REQ = "http://coms-309-031.class.las.iastate.edu:8080/classrooms/23";
    //   public static final String URL_STRING_REQ = "http://10.0.2.2:8080/users/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_req);


        btnStringReq = (Button) findViewById(R.id.btnStringReq);
        msgResponse = (TextView) findViewById(R.id.msgResponse);
        TestText = (TextView) findViewById(R.id.textView2);

        btnStringReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeStringReq();
            }
        });
    }


    /**
     * Making string request
     **/
    private void makeStringReq() {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL_STRING_REQ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Volley Response", response.toString());
                        //msgResponse.setText(response.toString());

                         String HelperString = response.toString();
                         int length = HelperString.length();

                        String Answer = "";
                        int counter = 0;
                         for (int i = 0; i < length; i++){
                             if (HelperString.charAt(i) == '\"') {
                                counter++;
                             }
                               else if (counter == 3) {


                                       Answer = Answer + HelperString.charAt(i);


                               }
                             if (counter == 4){
                                 counter = 0;
                             }
                         }

                        TestText.setText(Answer);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any errors that occur during the request
                        Log.e("Volley Error", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer YOUR_ACCESS_TOKEN");
//                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("param1", "value1");
//                params.put("param2", "value2");
                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}