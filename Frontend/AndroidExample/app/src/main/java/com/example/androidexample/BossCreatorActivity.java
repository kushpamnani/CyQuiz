package com.example.androidexample;


import static com.example.androidexample.OnlineTrackerActivity.getOnlineList;
import static com.example.androidexample.OnlineTrackerActivity.onlineCheck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BossCreatorActivity extends AppCompatActivity {

    TextView healthGet;
    TextView dmgGet;
    private TextView flashGet;
    TextView errorText;
    TextView defenseGet;
    private Button addFlash;
    TextView nameGet;
    Button sendInfo;
    Button updateButton;
    private Button deleteButton;
    JSONObject bossJSON;
    private boolean flashcardExist = true;
    TextView idGet;
    private Button getButton;
    private ImageView onlineStar;
    private ImageView offlineStar;
    private TextView onlineList;

    private String baseURL = "http://coms-309-031.class.las.iastate.edu:8080";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_create);

        healthGet = findViewById(R.id.healthInput);
        dmgGet = findViewById(R.id.dmgInput);
        flashGet = findViewById(R.id.cardInput);
        sendInfo = findViewById(R.id.sendBtn);
        errorText = findViewById(R.id.errorText);
        defenseGet = findViewById(R.id.defenseInput);
        nameGet = findViewById(R.id.bossNameInput);
        idGet = findViewById(R.id.idInput);
        updateButton = findViewById(R.id.updateBtn);
        deleteButton = findViewById(R.id.deleteBtn);
        getButton = findViewById(R.id.getButton);
        addFlash = findViewById(R.id.addCard);
        onlineStar = findViewById(R.id.onlineStar);
        offlineStar = findViewById(R.id.offlineStar);
        onlineList = findViewById(R.id.onlineList);

        if (onlineCheck()){
            offlineStar.setVisibility(View.GONE);
            onlineStar.setVisibility(View.VISIBLE);
            onlineList.setText(getOnlineList().toString());
        } else{
            offlineStar.setVisibility(View.VISIBLE);
            onlineStar.setVisibility(View.GONE);
        }


        sendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bossJSON = new JSONObject();
                try {
                    bossJSON.put("name", nameGet.getText().toString());
                    bossJSON.put("health", healthGet.getText().toString());
                    bossJSON.put("attack", dmgGet.getText().toString());
                    bossJSON.put("defense", defenseGet.getText().toString());
                    //bossJSON.put("flashcard", flashGet.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReq();



            }
        });
        addFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReqPutFlash();



            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bossJSON = new JSONObject();
                try {
                    bossJSON.put("name", nameGet.getText().toString());
                    bossJSON.put("health", healthGet.getText().toString());
                    bossJSON.put("attack", dmgGet.getText().toString());
                    bossJSON.put("defense", defenseGet.getText().toString());
                    bossJSON.put("id", idGet.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                makeJsonObjReqPut();


//                makeJsonArrayReq();
//
//                if (flashcardExist) {
//                    makeJsonObjReqPut();
//                }


            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    makeJsonObjReqDelete();
            }
        });
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjReqGet();
            }
        });


    }

    private void makeJsonArrayReq() {
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(
                Request.Method.GET,
                baseURL + "/flashcards",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley Response", response.toString());

                        for (int i = 0; i <= response.length(); i++) {
                            if (response.optJSONObject(i).optString("id").equals(flashGet.getText().toString())) {
                                errorText.setText(response.optJSONObject(i).toString());
                                flashcardExist = true;
                                break;
                            }
                        }














                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrReq);
    }

    void makeJsonObjReq() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.POST,
                baseURL + "/enemies",
                bossJSON,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());
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

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    void makeJsonObjReqPut() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.PUT,
                baseURL + "/enemies/" + idGet.getText().toString(),
                bossJSON,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());

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

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    private void makeJsonObjReqPutFlash() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.PUT,
                baseURL + "/enemies/" + idGet.getText().toString() + "/flashcards/" + flashGet.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());

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
    private void makeJsonObjReqGet() {



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.GET,
                baseURL + "/enemies/" + idGet.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());

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

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }
    private void makeJsonObjReqDelete() {




        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                Request.Method.DELETE,
                baseURL + "/enemies/" + idGet.getText().toString(),
                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley Response", response.toString());
                        errorText.setText(response.toString());

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

                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        // Adding request to request queue
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }



}
