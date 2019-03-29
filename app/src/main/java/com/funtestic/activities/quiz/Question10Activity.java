package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question10Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question10_layout);
    }

    public void openQuestion9Activity(View v)
    {
        Intent intent = new Intent(this, Question9Activity.class);
        startActivity(intent);
    }

    public void openQuestion11Activity(View v)
    {
        Intent intent = new Intent(this, Question11Activity.class);
        startActivity(intent);
    }
}
