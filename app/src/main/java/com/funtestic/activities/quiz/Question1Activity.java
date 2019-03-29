package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1_layout);
    }

    public void openQuestion2Activity(View v)
    {
        Intent intent = new Intent(this, Question2Activity.class);
        startActivity(intent);
    }
}
