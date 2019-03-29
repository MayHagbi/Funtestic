package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5_layout);
    }

    public void openQuestion4Activity(View v)
    {
        Intent intent = new Intent(this, Question4Activity.class);
        startActivity(intent);
    }

    public void openQuestion6Activity(View v)
    {
        Intent intent = new Intent(this, Question6Activity.class);
        startActivity(intent);
    }
}
