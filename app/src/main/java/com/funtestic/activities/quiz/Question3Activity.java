package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3_layout);
    }

    public void openQuestion2Activity(View v)
    {
        Intent intent = new Intent(this, Question2Activity.class);
        startActivity(intent);
    }

    public void openQuestion4Activity(View v)
    {
        Intent intent = new Intent(this, Question4Activity.class);
        startActivity(intent);
    }
}
