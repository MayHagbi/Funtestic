package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7_layout);
    }

    public void openQuestion6Activity(View v)
    {
        Intent intent = new Intent(this, Question6Activity.class);
        startActivity(intent);
    }

    public void openQuestion8Activity(View v)
    {
        Intent intent = new Intent(this, Question8Activity.class);
        startActivity(intent);
    }
}
