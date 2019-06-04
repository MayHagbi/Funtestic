package com.funtestic.activities.mainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.GameActivity;
import com.funtestic.activities.quiz.Question1Activity;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectQuizCategory extends AppCompatActivity implements View.OnClickListener {

    private Spinner selectCategorySpinner;
    private Button btnStartQuiz;
    private Button btnStartGame;
    private String child_id;

    private ArrayAdapter<String> spinnerCategoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz_category_layout);
        selectCategorySpinner = (Spinner) findViewById(R.id.select_quiz_category_spinner);
        btnStartQuiz = (Button) findViewById(R.id.start_quiz_by_category);
        btnStartGame = (Button) findViewById(R.id.buttonStartGame);
        child_id = getIntent().getStringExtra("child_id");

        spinnerCategoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getCategoriesList());
        selectCategorySpinner.setAdapter(spinnerCategoryAdapter);

        btnStartQuiz.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
    }

    private ArrayList<String> getCategoriesList() {
        return new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.category_array)));
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.start_quiz_by_category:
                String category = selectCategorySpinner.getSelectedItem().toString();
                if (category.isEmpty()) {
                    Toast.makeText(this, R.string.must_select_category, Toast.LENGTH_LONG).show();
                    break;
                }

                intent = new Intent(SelectQuizCategory.this, Question1Activity.class);
                intent.putExtra("child_id",child_id);
                startActivity(intent);
                finish();
                break;

            case R.id.buttonStartGame:
                intent = new Intent(SelectQuizCategory.this, GameActivity.class);
                startActivity(intent);
                //finish();
                break;


        }
    }
}
