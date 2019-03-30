package com.funtestic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.funtestic.R;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startButton;
    private Button reportButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        startButton = (Button)findViewById(R.id.mainMenuStartButton);
        reportButton=(Button)findViewById(R.id.mainMenuReportButton);
        exitButton=(Button)findViewById(R.id.mainMenuExitButton);

        startButton.setOnClickListener(this);
        reportButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        switch(view.getId()) {
            case R.id.mainMenuStartButton:
                finish();
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
                break;

            case R.id.mainMenuReportButton:
                //finish();
                startActivity(new Intent(getApplicationContext() , ReportActivity.class));
                break;
            case R.id.mainMenuExitButton:
                this.finish();
                System.exit(0);
                break;
        }
    }


}


