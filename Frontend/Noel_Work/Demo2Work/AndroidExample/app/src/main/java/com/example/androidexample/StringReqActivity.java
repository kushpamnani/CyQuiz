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

    private  boolean idTaken = false;

    private boolean nameTaken = false;

    private boolean passwordTaken = false;

    private boolean classroomInfoTaken = false;

    private boolean classID1Taken = false;

    private boolean classID2Taken = false;

    private boolean classNameTaken = false;

    private String classID1 = "";

    private String classID2 = "";

    private String className = "";

    private String HelperString = "";

    private String classCode = "";

    private String teacherID = "";

    private String teacherName = "";

    private String teacherIsActive = "";

    private String studentIsActive = "";

    private boolean classCodeTaken = false;

    private boolean teacherIDTaken = false;

    private boolean teacherNameTaken = false;

    private boolean teachIsActTaken = false;

    private boolean studentIsActTaken = false;








   //private static final String URL_STRING_REQ = "https://bb1bfe69-3299-49d4-8006-e8e24e5faf63.mock.pstmn.io/GetTest2/";
     // public static final String URL_STRING_REQ = "http://coms-309-031.class.las.iastate.edu:8080/users/61";
       public static final String URL_STRING_REQ = "https://d34095c7-496f-4dc3-8497-9d514eef0c4c.mock.pstmn.io/ScanTest/";

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

                         HelperString = response.toString();
                         int length = HelperString.length();

                        String Answer = "";
                        int counter = 0;


                        String ID = "";
                        String name = "";
                        String password = "";
                        boolean bugfix = false;
                        for (int i = 0; i < length; i++){
                            if (!idTaken) {
                                if (HelperString.charAt(i) == ':' || HelperString.charAt(i) == ',') {
                                    counter++;
                                } else if (counter == 1) {


                                    ID = ID + HelperString.charAt(i);


                                }
                                if (counter == 2) {
                                    counter = 0;
                                    idTaken = true;
                                }
                            }

                            else if (!nameTaken){
                                if (HelperString.charAt(i) == '\"') {
                                    counter++;
                                } else if (counter == 3) {


                                    name = name + HelperString.charAt(i);


                                }
                                if (counter == 4) {
                                    counter = 0;
                                    nameTaken = true;
                                }
                            }
                            else if (!passwordTaken){
                                if (HelperString.charAt(i) == '\"') {
                                    counter++;
                                } else if (counter == 3) {


                                    password = password + HelperString.charAt(i);


                                }
                                if (counter == 4) {
                                    counter = 0;
                                    passwordTaken = true;
                                }
                            }
                            else if (!classroomInfoTaken){
                               classScanner();
                               counter = 0;

                            }
                            else if (!studentIsActTaken){
                                if (HelperString.charAt(i) == ']' ) {


                                    for (int j = i; j < length; j++){
                                        if (HelperString.charAt(j) == '\n' || HelperString.charAt(j) == '"'){
                                            counter++;
                                        }

                                            if (counter == 3){
                                            studentIsActive = studentIsActive + HelperString.charAt(j + 3);
                                        }








                                        if (counter == 4) {
                                            counter = 0;

                                            studentIsActTaken = true;


                                        }
                                    }


                                }

                            }





                        }

//                         for (int i = 0; i < length; i++){
//                             if (HelperString.charAt(i) == '\"') {
//                                counter++;
//                             }
//                               else if (counter == 3) {
//
//
//                                       Answer = Answer + HelperString.charAt(i);
//
//
//                               }
//                             if (counter == 4){
//                                 counter = 0;
//                             }
//                         }

                        TestText.setText("ID is: " + ID + " name: " + name + " password: " + password + " class id1: " + classID1 + " class id2: " + classID2 + " class name: " + className + " class code: " + classCode + " Teacher ID: " + teacherID + " Teacher name: " + teacherName + " teacher is active: " + teacherIsActive + " Student is active: " + studentIsActive);

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
    public void classScanner() {
        int length = HelperString.length();
        int counter = 0;
        for (int i = 0; i < length; i++) {
            if (HelperString.charAt(i) == '[') {

                for (int j = i; j < length; j++) {
                    if (!classID1Taken){
                    if (HelperString.charAt(j) == ':' || HelperString.charAt(j) == ',') {
                        counter++;
                    } else if (counter == 1) {


                        classID1 = classID1 + HelperString.charAt(j);


                    }
                    if (counter == 2) {
                        counter = 0;

                        classID1Taken = true;


                    }
                    }
                    else if (!classID2Taken){
                        if (HelperString.charAt(j) == ':' || HelperString.charAt(j) == ',') {
                            counter++;
                        } else if (counter == 2) {


                            classID2 = classID2 + HelperString.charAt(j);


                        }
                        if (counter == 3) {
                            counter = 0;

                            classID2Taken = true;


                        }
                    }
                    else if (!classNameTaken){
                        if (HelperString.charAt(j) == '\"') {
                            counter++;
                        } else if (counter == 3) {


                            className = className + HelperString.charAt(j);


                        }
                        if (counter == 4) {
                            counter = 0;
                            classNameTaken = true;
                        }
                    }
                    else if (!classCodeTaken){
                        if (HelperString.charAt(j) == ':' || HelperString.charAt(j) == ',') {
                            counter++;
                        } else if (counter == 2) {


                            classCode = classCode + HelperString.charAt(j);


                        }
                        if (counter == 3) {
                            counter = 0;

                            classCodeTaken = true;


                        }
                    }
                    else if (!teacherIDTaken){
                        if (HelperString.charAt(j) == ':' || HelperString.charAt(j) == ',') {
                            counter++;
                        } else if (counter == 2) {


                            teacherID = teacherID + HelperString.charAt(j);


                        }
                        if (counter == 3) {
                            counter = 0;

                            teacherIDTaken = true;


                        }
                    }
                    else if (!teacherNameTaken){
                        if (HelperString.charAt(j) == '\"') {
                            counter++;
                        } else if (counter == 3) {


                            teacherName = teacherName + HelperString.charAt(j);


                        }
                        if (counter == 4) {
                            counter = 0;
                            teacherNameTaken = true;
                        }
                    }
                    else if (!teachIsActTaken){
                        if (HelperString.charAt(j) == ':' || HelperString.charAt(j) == '\n') {
                            counter++;
                        } else if (counter == 2) {

                                teacherIsActive = teacherIsActive + HelperString.charAt(j);




                        }
                        if (counter == 3) {
                            counter = 0;

                            teachIsActTaken = true;


                        }
                    }


                }

            }
        }
        classroomInfoTaken = true;

    }
}