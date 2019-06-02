package com.funtestic.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.mainMenu.MainMenuActivity;
import com.funtestic.activities.report.ReportActivity;
import com.funtestic.models.DataBase;

public class TwoStepVerificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendButton;
    private EditText number;
    private String phone ;
    private String password ;
    private String token ;
    private String TwoFA_pass ;
    private SharedPreferences sp;

    private DataBase db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_step_verification);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        sendButton = (Button)findViewById(R.id.buttonSend2stepVerification);
        number = (EditText) findViewById(R.id.editText2stepVerification);
        sendButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){

        switch(view.getId()) {

            case R.id.buttonSend2stepVerification:
                TwoFA_pass = number.getText().toString();
                phone=getIntent().getStringExtra("phone");
                password=getIntent().getStringExtra("password");

                token=db.TwoStepVerification(phone,password,TwoFA_pass);

                if(token == null){
                    //TODO ERROR
                    Context context = getApplicationContext();
                    CharSequence text = "WORNG CODE!!!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("phone", phone);
                    editor.putString("token", token);
                    editor.commit();

                    finish();
                    startActivity(new Intent(getApplicationContext() , MainMenuActivity.class));
                }

                break;


        }
    }
}
