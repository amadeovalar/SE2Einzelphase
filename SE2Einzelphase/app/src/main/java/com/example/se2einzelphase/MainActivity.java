package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button submitButton;

    EditText matrikelNummerInput;

    Integer matNr;

    TextView messageFromServer;


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
    

}