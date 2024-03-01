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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjReqActivity extends AppCompatActivity {

    private Button btnJsonObjReq;
    private TextView msgResponse;

    private JSONObject TestLog;

    private TextView testText;

    private static final String URL_JSON_OBJECT = "https://87c2ba07-6f71-4b03-adcb-747b9cb5798c.mock.pstmn.io/PostTester/";
   // private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080/users";
    //private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080/teachers";
   // private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080/teachers/9/add_classroom";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_obj_req);

        btnJsonObjReq = findViewById(R.id.btnJsonObj);
        msgResponse = findViewById(R.id.msgResponse);
        testText = findViewById(R.id.textView3);

        btnJsonObjReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                   TestLog = new JSONObject();
                    TestLog.put("name", "Test Classroom");
                    //TestLog.put("password", "password");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReq();
            }
        });
    }

    /**
     * Making json object request
     */
    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.POST,
                URL_JSON_OBJECT,
                TestLog,
                 // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
//                        String HelperString = response.toString();
//                        int length = HelperString.length();
//
//                        String Answer = "";
//                        int counter = 0;
//                        for (int i = 0; i < length; i++){
//                            if (HelperString.charAt(i) == '\"') {
//                                counter++;
//                            }
//                            else if (counter == 3) {
//
//
//                                Answer = Answer + HelperString.charAt(i);
//
//
//                            }
//                            if (counter == 4){
//                                counter = 0;
//                            }
//                        }
//
//                        testText.setText(Answer);
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
}