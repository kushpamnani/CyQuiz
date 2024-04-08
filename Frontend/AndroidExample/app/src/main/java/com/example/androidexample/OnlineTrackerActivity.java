package com.example.androidexample;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.handshake.ServerHandshake;

import java.util.AbstractList;
import java.util.ArrayList;

public class OnlineTrackerActivity extends AppCompatActivity implements WebSocketListener {

    private Button onlineSet;
    private Button offlineSet;
    private TextView usernameGetOnline;
    private TextView debugText;
    private TextView helperText;
    private String serverURL = "ws://10.0.2.2:8080/chat/";
    private static boolean isOnline;
    private static ArrayList<String> onlineList = new ArrayList<>();
    private StringBuilder helper = new StringBuilder();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        WebSocketManager.getInstance().setWebSocketListener(OnlineTrackerActivity.this);
        onlineSet = findViewById(R.id.onlineButton);
        offlineSet = findViewById(R.id.offlineButton);
        usernameGetOnline = findViewById(R.id.usernameGet);
        helperText = findViewById(R.id.textStatement);
        debugText = findViewById(R.id.debugText);


        onlineSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverUrl = serverURL + usernameGetOnline.getText().toString();
                WebSocketManager.getInstance().connectWebSocket(serverUrl);
            }
        });
        offlineSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebSocketManager.getInstance().disconnectWebSocket();

            }
        });



    }
    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {
        runOnUiThread(() -> {
            isOnline = true;
            helperText.setText("Online");
        });
    }

    @Override
    public void onWebSocketMessage(String message) {
        runOnUiThread(() -> {
            helper.setLength(0);

            String debug = message.substring(0,10);
            String debug2 = message.substring(message.length()-12, message.length());
            if (debug2.equals("disconnected")){
                String username = "";
                username = message.substring(0, message.length()-12);
                onlineList.remove(username);


            } else if (!debug.equals("Welcome to")) {
                String username = "";
                    for (int i = 6; i < message.length(); i++) {
                        username = username + message.charAt(i);
                        if (message.charAt(i) == ' ') {
                            break;
                        }
                    }


                    onlineList.add(username);
            }
            for (int i = 0; i < onlineList.size(); i++) {
                helper.append(onlineList.get(i));
            }
            debugText.setText(helper);




        });

    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        runOnUiThread(() -> {
            isOnline = false;
            helperText.setText("offline");


        });
    }

    @Override
    public void onWebSocketError(Exception ex) {

    }
    public static boolean onlineCheck() {
        return isOnline;
    }
    public static ArrayList<String> getOnlineList(){
        return onlineList;
    }
}
