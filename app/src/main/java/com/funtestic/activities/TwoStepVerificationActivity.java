package com.funtestic.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.funtestic.R;
import com.funtestic.activities.report.ReportActivity;

public class TwoStepVerificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendButton;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_step_verification);

        sendButton = (Button)findViewById(R.id.buttonSend2stepVerification);
        number = (EditText) findViewById(R.id.editText2stepVerification);
        sendButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){

        switch(view.getId()) {

            case R.id.buttonSend2stepVerification:
                String num = number.getText().toString();
                //get form db
                //if(db == num)
                //check input
                finish();
                //startActivity(new Intent(getApplicationContext() , MainActivity.class));
                break;


        }
    }
}