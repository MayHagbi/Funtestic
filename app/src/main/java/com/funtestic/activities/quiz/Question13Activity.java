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
import java.util.Map;

public class Question13Activity extends AppCompatActivity {

    public static int SUM_OF_QUESTIONS = 13;
    private Button submit_btn;
    private Button prev_btn;
    private RadioGroup radioGroup;
    private RadioButton choose_btn;
    private SharedPreferences table_score_prefs;
    private float gradeOfChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question13_layout);
        table_score_prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radio13);
        submit_btn = (Button) findViewById(R.id.submit);
        prev_btn = (Button) findViewById(R.id.previous13);

        submit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // no button was selected
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Question13Activity.this,
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
                    SharedConstants.scorePreferencesInitialization(table_score_prefs, answer, "question13");

                    gradeOfChild = calculateScore();

//                    Toast.makeText(Question13Activity.this,
//                            String.valueOf(gradeOfChild), Toast.LENGTH_SHORT).show();
                }
            }

        });


        prev_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                openQuestion12Activity();
            }
        });
    }

    public void openQuestion12Activity()
    {
        Intent intent = new Intent(this, Question12Activity.class);
        startActivity(intent);
        finish();
    }

    public float calculateScore() {
        Map<String, ?> allEntries = table_score_prefs.getAll();
        int totalScore = 0;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            totalScore += Integer.parseInt(entry.getValue().toString());
        }
        return totalScore / SUM_OF_QUESTIONS;
    }
}
