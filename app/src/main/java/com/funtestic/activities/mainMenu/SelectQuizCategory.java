package com.funtestic.activities.mainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.quiz.Question1Activity;

import java.util.ArrayList;

public class SelectQuizCategory extends AppCompatActivity implements View.OnClickListener {

    private Spinner selectCategorySpinner;
    private Button btnStartQuiz;
    private ArrayAdapter<String> spinnerCategoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz_category_layout);
        selectCategorySpinner = (Spinner) findViewById(R.id.select_quiz_category_spinner);
        btnStartQuiz = (Button) findViewById(R.id.start_quiz_by_category);

        spinnerCategoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getCategoriesList());
        selectCategorySpinner.setAdapter(spinnerCategoryAdapter);

        btnStartQuiz.setOnClickListener(this);
    }

    private ArrayList<String> getCategoriesList() {
        ArrayList<String> categories = new ArrayList<>();

        //TODO: get categories

        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");

        return categories;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_quiz_by_category:
                String category = selectCategorySpinner.getSelectedItem().toString();
                if (category.isEmpty()) {
                    Toast.makeText(this, R.string.must_select_category, Toast.LENGTH_LONG).show();
                    break;
                }

                //TODO: select the quiz by the category

                Intent intent = new Intent(SelectQuizCategory.this, Question1Activity.class);
                startActivity(intent);
                finish();
        }
    }
}
