package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question12Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question12_layout);
    }

    public void openQuestion11Activity(View v)
    {
        Intent intent = new Intent(this, Question11Activity.class);
        startActivity(intent);
    }

    public void openQuestion13Activity(View v)
    {
        Intent intent = new Intent(this, Question13Activity.class);
        startActivity(intent);
    }
}
