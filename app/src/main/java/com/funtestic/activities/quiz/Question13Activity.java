package com.funtestic.activities.quiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.funtestic.R;
import com.funtestic.models.DataBase;
import com.funtestic.models.Quiz;
import com.funtestic.utilities.SharedConstants;

import java.text.DecimalFormat;
import java.util.Map;

public class Question13Activity extends AppCompatActivity {

    public static int SUM_OF_QUESTIONS = 13;
    private Button submit_btn;
    private Button prev_btn;
    private RadioGroup radioGroup;
    private RadioButton choose_btn;
    private SharedPreferences sharedPrefs;
    private float gradeOfChild;
    private String child_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question13_layout);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        child_id = sharedPrefs.getString("child_id", "DEFAULT");
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove("child_id");
        editor.commit();

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
                    SharedConstants.scorePreferencesInitialization(sharedPrefs, answer, "question13");

                    gradeOfChild = calculateScore();
                    // convert grade to string with 2 digits after the point
                    DecimalFormat decimalFormat = new DecimalFormat("#.00");
                    String grade = decimalFormat.format(gradeOfChild);
                    // http request
                    String token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("token","DEFAULT");
                    if(!DataBase.getInstance().addQuizToDb(new Quiz(grade,child_id),token)){
                        Toast.makeText(Question13Activity.this, "try again", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    removeQuestionsScoreFromSharedPref();
                    Toast.makeText(Question13Activity.this, "the quiz added successfully!", Toast.LENGTH_SHORT).show();
                    finish();
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
        Map<String, ?> allEntries = sharedPrefs.getAll();
        float totalScore = 0;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (!(entry.getKey().equals("phone")) && !(entry.getKey().equals("token")))
                totalScore += Integer.parseInt(entry.getValue().toString());
        }
        return totalScore / SUM_OF_QUESTIONS;
    }

    public void removeQuestionsScoreFromSharedPref() {
        String questionStr = "question";
        SharedPreferences.Editor editor = sharedPrefs.edit();
        for(int num = 1; num <= SUM_OF_QUESTIONS; num++) {
            editor.remove(questionStr + Integer.toString(num));
        }
        editor.commit();
    }
}
