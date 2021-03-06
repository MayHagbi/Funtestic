package com.funtestic.activities.mainMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.funtestic.R;
import com.funtestic.activities.MainActivity;
import com.funtestic.activities.child.SelectChildActivity;
import com.funtestic.activities.report.ReportActivity;

import static com.funtestic.utilities.SharedConstants.PREFS_NAME;

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
                startActivity(new Intent(getApplicationContext() , SelectChildActivity.class));
                break;
            case R.id.mainMenuReportButton:
                startActivity(new Intent(getApplicationContext() , ReportActivity.class));
                break;
            case R.id.mainMenuExitButton:
                SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,0);
                settings.edit().clear();
                settings.edit().commit();
                System.exit(0);
                break;
        }
    }


}


