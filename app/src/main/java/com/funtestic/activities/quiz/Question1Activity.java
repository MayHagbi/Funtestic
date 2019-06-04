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
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.funtestic.R;
import com.funtestic.utilities.SharedConstants;

public class Question1Activity extends AppCompatActivity {

    private Button next_btn;
    private RadioGroup radioGroup;
    private RadioButton choose_btn;
    private SharedPreferences table_score_prefs;
    private String child_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1_layout);
        table_score_prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                child_id = null;
            } else {
                child_id = extras.getString("child_id");
            }
        } else {
            child_id= (String) savedInstanceState.getSerializable("child_id");
        }

        SharedPreferences.Editor editor = table_score_prefs.edit();

        editor.putString("child_id", child_id);
        editor.commit();

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        radioGroup = (RadioGroup) findViewById(R.id.radio1);
        next_btn = (Button) findViewById(R.id.next1);

        next_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // no button was selected
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Question1Activity.this,
                            "You must choose one option", Toast.LENGTH_SHORT).show();
                }

                else {
                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    choose_btn = (RadioButton) findViewById(selectedId);

                    // save text of answer from button
                    String answer = String.valueOf(choose_btn.getText());

                    //score_editor.clear();
                    // save score by answer in shared preferences
                    SharedConstants.scorePreferencesInitialization(table_score_prefs, answer, "question1");

                    openQuestion2Activity();

//                    Toast.makeText(Question1Activity.this,
//                            table_score_prefs.getString("child_id", "DEFAULT"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openQuestion2Activity()
    {
        Intent intent = new Intent(this, Question2Activity.class);
        startActivity(intent);
        finish();
    }
}
