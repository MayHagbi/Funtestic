package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6_layout);
    }

    public void openQuestion5Activity(View v)
    {
        Intent intent = new Intent(this, Question5Activity.class);
        startActivity(intent);
    }

    public void openQuestion7Activity(View v)
    {
        Intent intent = new Intent(this, Question7Activity.class);
        startActivity(intent);
    }
}
