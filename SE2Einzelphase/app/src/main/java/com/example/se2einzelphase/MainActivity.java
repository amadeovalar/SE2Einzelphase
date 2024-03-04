package com.example.se2einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        }

}}