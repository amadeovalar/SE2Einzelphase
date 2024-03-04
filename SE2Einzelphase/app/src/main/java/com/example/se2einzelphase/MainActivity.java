package com.example.se2einzelphase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    Button submitButton;
    Button calculateButton;

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
//        Calculate button
        calculateButton = findViewById(R.id.btnBerechnen);
//        Message from server
        messageFromServer = findViewById(R.id.serverAntwort);
    }

    //    Submit button behaviour method
    public void onClickSubmit(View view) {
//        Checking if the input is empty
        if (matrikelNummerInput.getText().toString().isEmpty()) {
            runOnUiThread(() -> {
                String noInputMsg = "Input missing!";
//                    Create toast to tell user, that there is no input yet
                Toast.makeText(MainActivity.this, noInputMsg, Toast.LENGTH_SHORT).show();
            });
        } else {
            submitButton.setEnabled(true);
            matNr = Integer.parseInt(matrikelNummerInput.getText().toString());

            new Thread(() -> {
//                    Establish the connection to the server by creating a socket
                Socket socket = null;
                try {
                    socket = new Socket(serverName, portNr);
                    Log.v("Connected", socket.getRemoteSocketAddress().toString());

                    // Get output stream from the socket
                    PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
                    toServer.println(matNr);

                    // Send data
                    Log.v("MatNrStr", String.valueOf(matNr));

//                        Reading the data from the server
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverMessage = bufferedReader.readLine();

                    Log.v("EditText", matrikelNummerInput.getText().toString());
                    Log.v("ServerMessage", serverMessage);

                    runOnUiThread(() -> {
//                                Turning text to visible
                        messageFromServer.setVisibility(View.VISIBLE);
//                                Show the message from server
                        messageFromServer.setText(serverMessage);
                    });

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    // Close the socket in the finally block to ensure it's always closed
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }

    //  Calculate button behavior method
    public void onClickCalculateBinary(View view) {
        if (matrikelNummerInput.getText().toString().isEmpty()) {
            runOnUiThread(() -> {
                String noInputMsg = "Input missing!";
//                    Create toast to tell user, that there is no input yet
                Toast.makeText(MainActivity.this, noInputMsg, Toast.LENGTH_SHORT).show();
            });
        } else {
//            Enable the button
            calculateButton.setEnabled(true);
            matNr = Integer.parseInt(matrikelNummerInput.getText().toString());

            try {
                // Create an instance of BinaryConverter
                BinaryConverter binaryConverter = new BinaryConverter();

                // Convert the sum of numbers to binary
                String binaryArray = binaryConverter.convertSumToBinary(matNr);

                // Print the result
                runOnUiThread(() -> {
                    //                                Turning text to visible
                    messageFromServer.setVisibility(View.VISIBLE);
                    //                                show the results of the calculation
                    messageFromServer.setText(binaryArray);
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}