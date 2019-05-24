package com.funtestic.activities.quiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.utilities.SharedConstants;

public class Question11Activity extends AppCompatActivity {

    private Button next_btn;
    private Button prev_btn;
    private RadioGroup radioGroup;
    private RadioButton choose_btn;
    private SharedPreferences table_score_prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question11_layout);
        table_score_prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radio11);
        next_btn = (Button) findViewById(R.id.next11);
        prev_btn = (Button) findViewById(R.id.previous11);

        next_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // no button was selected
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Question11Activity.this,
                            "You must choose one option", Toast.LENGTH_SHORT).show();
                }

                else {
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    choose_btn = (RadioButton) findViewById(selectedId);

                    // save text of answer from button
                    String answer = String.valueOf(choose_btn.getText());

                    // save score by answer in shared preferences
                    SharedConstants.scorePreferencesInitialization(table_score_prefs, answer, "question11");

                    openQuestion12Activity();

//                    Toast.makeText(Question11Activity.this,
//                            String.valueOf(table_score_prefs.getInt("question10", -1)), Toast.LENGTH_SHORT).show();
                }
            }

        });


        prev_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                openQuestion10Activity();
            }
        });
    }

    public void openQuestion10Activity()
    {
        Intent intent = new Intent(this, Question10Activity.class);
        startActivity(intent);
        finish();
    }

    public void openQuestion12Activity()
    {
        Intent intent = new Intent(this, Question12Activity.class);
        startActivity(intent);
        finish();
    }
}
