package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button submitButton;

    EditText matrikelNummerInput;

    Integer matNr;

    TextView messageFromServer;

//    Server name and port
    String serverName = "se2-submission.aau.at";
    Integer portNr = 20080;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Text from user
        matrikelNummerInput = findViewById(R.id.matrikelNummerInput);
//        Send button
        submitButton = findViewById(R.id.btnAbschicken);
//        Message from server
        messageFromServer = findViewById(R.id.serverAntwort);
    }

//    Button behaviour function
    public void onClickSubmit(View view){
//        Checking if the input is empty
        if (matrikelNummerInput.getText().toString().isEmpty()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String noInputMsg = "Input missing!";
//                    Create toast to tell user, that there is no input yet
                    Toast.makeText(MainActivity.this, noInputMsg, Toast.LENGTH_SHORT).show();
                }
            });
        } else{
            submitButton.setEnabled(true);
            matNr = Integer.parseInt(matrikelNummerInput.getText().toString());

            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Establish the connection to the server by creating a socket
                    try {
                        Socket socket = new Socket(serverName, portNr);
                        Log.v("Connected", socket.getRemoteSocketAddress().toString());

//                        Sending data to server
                        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                        toServer.println(matNr);

//                        Reading the data from the server
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String serverMessage = bufferedReader.readLine();

                        Log.v("EditText", matrikelNummerInput.getText().toString());
                        Log.v("ServerMessage", serverMessage);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Turning text to visible
                                messageFromServer.setVisibility(View.VISIBLE);
//                                Show the message from server
                                messageFromServer.setText(serverMessage);
                            }
                        });

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }

}}