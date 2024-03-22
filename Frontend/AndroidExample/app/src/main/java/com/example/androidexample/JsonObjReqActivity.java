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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Area where users can be created or deleted, as well as updated
 */
public class JsonObjReqActivity extends AppCompatActivity {

    /**
    Sends json Objects
     */
    private Button btnJsonObjReq;
    /**
    shows the message the server responds
     */
    private TextView msgResponse;

    /**
    Json Object building base
     */
    private JSONObject TestLog;

    /**
    test text var
     */
    private TextView testText;

    /**
    user enters their name here
     */
    private TextView NameGet;

   /**
   user enters their password here
    */
    private TextView PassGet;

    /**
    button for submitting a put update
     */
    private Button putButton;

   /**
   button to select adding a user
    */
    private Button addUser;

    /**
    button to select adding a teacher
     */
    private Button addTeacher;

   /**
   button to select adding a class
    */
    private Button addClass;

    /**
     * place for user to ender their ID
     */
    private TextView userID;

    /**
     * place for user to enter a classroom ID
     */
    private TextView classID;

    /**
     * button to delete
     */
    private Button deleteButton;



    //private static final String URL_JSON_OBJECT = "https://87c2ba07-6f71-4b03-adcb-747b9cb5798c.mock.pstmn.io/PostTester/";
    //private static final String URL_JSON_OBJECT = "https://bb1bfe69-3299-49d4-8006-e8e24e5faf63.mock.pstmn.io/PostTest/";
    //private static final String URL_JSON_OBJECT = "https://bb1bfe69-3299-49d4-8006-e8e24e5faf63.mock.pstmn.io/DemoDelete/";

    /**
     * base URL string for the server
     */
    private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080";
    //private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080/teachers/11/add_classroom";
   //private static final String URL_JSON_OBJECT = "http://coms-309-031.class.las.iastate.edu:8080/users/72/classrooms/25";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_obj_req);

        btnJsonObjReq = findViewById(R.id.btnJsonObj);
        msgResponse = findViewById(R.id.msgResponse);
        testText = findViewById(R.id.textView3);
        NameGet  = findViewById(R.id.NameBox);
        PassGet = findViewById(R.id.PassBox);
        putButton = findViewById(R.id.putButton);
        addUser = findViewById(R.id.AddUser);
        addClass = findViewById(R.id.AddClass);
        addTeacher = findViewById(R.id.AddTeacher);
       userID = findViewById(R.id.userIDBox);
       classID = findViewById(R.id.classIDBox);
       deleteButton = findViewById(R.id.deleteButton);

        btnJsonObjReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                   TestLog = new JSONObject();
                    TestLog.put("name", NameGet.getText().toString());
                    TestLog.put("password", PassGet.getText().toString());
//                    TestLog.put("name", "TestNoel");
//                    TestLog.put("password", "TestNoelPass");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReq();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReqDelete();
            }
        });
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addClass.isActivated() && addUser.isActivated()){
                    try{
                        TestLog = new JSONObject();
                        TestLog.put("name", NameGet.getText().toString());


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                try{
                    TestLog = new JSONObject();
                    TestLog.put("name", NameGet.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReqPut();
            }
        });
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass.setActivated(!addClass.isActivated());
            }
        });
        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher.setActivated(!addTeacher.isActivated());
            }
        });
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser.setActivated(!addUser.isActivated());
            }
        });

    }

    /**
     * builds the URL that is necessary to complete the put action that the user would like
     */
    private void makeJsonObjReqPut() {
        String baseURL = URL_JSON_OBJECT;
        if (addClass.isActivated() && addTeacher.isActivated()){
            baseURL = baseURL + "/teachers/" + userID.getText().toString() + "/add_classroom";
        }
        else if (addClass.isActivated() && addUser.isActivated()){
            baseURL = baseURL + "/users/" + userID.getText().toString() + "/classrooms/" + classID.getText().toString();
        }else{
            msgResponse.setText("Dont do that pls");
        }



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.PUT,
                baseURL,
                TestLog,
                // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
//



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

    /**
     * builds the correct URL for the user to delete what they would like
     */
    private void makeJsonObjReqDelete() {
        String baseURL = URL_JSON_OBJECT;
        if (addClass.isActivated()){
            baseURL = baseURL + "/classrooms/" + classID.getText().toString();
        }
        else if (addUser.isActivated()){
            baseURL = baseURL + "/users/" + userID.getText().toString();
        }
        else if(addTeacher.isActivated()){
            baseURL = baseURL + "/teachers/" + userID.getText().toString();
        }
        else{
            msgResponse.setText("Dont do that pls");
        }



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.DELETE,
                baseURL,
                TestLog,
                // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
//



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
    /**
     * Builds the URL for a custom OBJ request
     */

    private void makeJsonObjReq() {
        String baseURL = URL_JSON_OBJECT;
        if (addTeacher.isActivated()){
            baseURL = baseURL + "/teachers";
        } else if (addUser.isActivated()){
            baseURL = baseURL + "/users";
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.POST,
                baseURL,
                TestLog,
                 // Pass null as the request body since it's a GET request
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        msgResponse.setText(response.toString());
                        try {
                            String IDget = response.getString("id");
                            testText.setText("ID is: " + IDget) ;
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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
}