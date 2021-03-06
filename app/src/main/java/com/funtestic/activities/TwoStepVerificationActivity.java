package com.funtestic.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.mainMenu.MainMenuActivity;
import com.funtestic.activities.report.ReportActivity;
import com.funtestic.models.DataBase;

import static com.funtestic.utilities.SharedConstants.PREFS_NAME;
import static com.funtestic.utilities.Validations.isValid2FATokenLength;

public class TwoStepVerificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendButton;
    private EditText number;
    private String phone ;
    private String password ;
    private String token ;
    private String TwoFA_pass ;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_step_verification);
        sp = this.getSharedPreferences(PREFS_NAME,0);

        sendButton = (Button)findViewById(R.id.buttonSend2stepVerification);
        number = (EditText) findViewById(R.id.editText2stepVerification);
        sendButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){

        switch(view.getId()) {

            case R.id.buttonSend2stepVerification:
                TwoFA_pass = number.getText().toString();
                if(!isValid2FATokenLength(TwoFA_pass)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Bad Password!", Toast.LENGTH_LONG);
                    toast.show();
                    return ;
                }
                phone=getIntent().getStringExtra("phone");
                password=getIntent().getStringExtra("password");

                token=DataBase.getInstance().TwoStepVerification(phone,password,TwoFA_pass);
                if(token == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "Bad Password!", Toast.LENGTH_LONG);
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
