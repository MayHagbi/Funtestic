package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4_layout);
    }

    public void openQuestion3Activity(View v)
    {
        Intent intent = new Intent(this, Question3Activity.class);
        startActivity(intent);
    }

    public void openQuestion5Activity(View v)
    {
        Intent intent = new Intent(this, Question5Activity.class);
        startActivity(intent);
    }
}
