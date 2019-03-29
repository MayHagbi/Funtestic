package com.funtestic.activities.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.funtestic.R;

public class Question9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question9_layout);
    }

    public void openQuestion8Activity(View v)
    {
        Intent intent = new Intent(this, Question8Activity.class);
        startActivity(intent);
    }

    public void openQuestion10Activity(View v)
    {
        Intent intent = new Intent(this, Question10Activity.class);
        startActivity(intent);
    }
}
