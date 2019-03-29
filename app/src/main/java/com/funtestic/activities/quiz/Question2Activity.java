package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2_layout);
    }

    public void openQuestion1Activity(View v)
    {
        Intent intent = new Intent(this, Question1Activity.class);
        startActivity(intent);
    }

    public void openQuestion3Activity(View v)
    {
        Intent intent = new Intent(this, Question3Activity.class);
        startActivity(intent);
    }
}
