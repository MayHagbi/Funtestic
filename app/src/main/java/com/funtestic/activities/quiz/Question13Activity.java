package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question13_layout);
    }

    public void openQuestion12Activity(View v)
    {
        Intent intent = new Intent(this, Question12Activity.class);
        startActivity(intent);
    }
}
