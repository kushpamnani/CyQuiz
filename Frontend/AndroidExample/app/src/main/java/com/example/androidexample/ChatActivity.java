package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.handshake.ServerHandshake;

import java.util.Objects;

public class ChatActivity extends AppCompatActivity implements WebSocketListener{

    private Button sendBtn,back;
    private EditText msgEtx;
    private TextView msgTv;
    private String username,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /* initialize UI elements */
        sendBtn = (Button) findViewById(R.id.sendBtn);
        msgEtx = (EditText) findViewById(R.id.msgEdt);
        msgTv = (TextView) findViewById(R.id.tx1);
        username = LoginActivity.getUsername();
        back = findViewById(R.id.ChatBack);
        url = "ws://10.0.2.2:8080/chat/";

        /* connect this activity to the websocket instance */
        WebSocketManager.getInstance().connectWebSocket(url+username);
        WebSocketManager.getInstance().setWebSocketListener(ChatActivity.this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebSocketManager.getInstance().disconnectWebSocket();
                startActivity(new Intent(ChatActivity.this, MainActivity.class));
            }
        });

        /* send button listener */
        sendBtn.setOnClickListener(v -> {
            try {
                if(Objects.equals(username.substring(0,Math.min(5,username.length())), "Admin")){
                    // send message
                    WebSocketManager.getInstance().sendMessage(msgEtx.getText().toString());
                }
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
        });
    }
    private Integer MsgUsernameLength(String message){
        int i =0;
        int length=0;
        while(message.length()>i){
            if(message.charAt(i)==':'){
                return length;
            }
            length++;
            i++;
        }
        return length;
    }
    @Override
    public void onWebSocketMessage(String message) {
        /**
         * In Android, all UI-related operations must be performed on the main UI thread
         * to ensure smooth and responsive user interfaces. The 'runOnUiThread' method
         * is used to post a runnable to the UI thread's message queue, allowing UI updates
         * to occur safely from a background or non-UI thread.
         */
        runOnUiThread(() -> {
            // Only show msg with !Announcement
            int usernamelength = MsgUsernameLength(message);
            if(message.substring(Math.min(usernamelength+2,message.length()), Math.min(message.length(),usernamelength +15)).equals("!Announcement") || Objects.equals(username.substring(0,Math.min(5,username.length())), "Admin")){
                String s = msgTv.getText().toString();
                msgTv.setText(s + "\n"+message);
            }
        });
    }

    @Override
    public void onWebSocketClose(int code, String reason, boolean remote) {
        String closedBy = remote ? "server" : "local";
        runOnUiThread(() -> {
            String s = msgTv.getText().toString();
            msgTv.setText(s + "---\nconnection closed by " + closedBy + "\nreason: " + reason);
        });
    }

    @Override
    public void onWebSocketOpen(ServerHandshake handshakedata) {}

    @Override
    public void onWebSocketError(Exception ex) {}
}