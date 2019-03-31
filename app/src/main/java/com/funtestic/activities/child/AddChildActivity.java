package com.funtestic.activities.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.funtestic.R;
import com.funtestic.models.Child;


public class AddChildActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etAge;
    private EditText etID;
    private Spinner genderSpinner;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_layout);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etID = findViewById(R.id.etID);
        genderSpinner = (Spinner) findViewById(R.id.GenderSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddChildActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genderList));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(myAdapter);


        Button btnSubChild = findViewById(R.id.btnSubChild);

        btnSubChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAge.getText().toString().length()!=0 && etID.getText().toString().length()!=0 && etName.getText().toString().length()!=0) {
                    Intent intent = new Intent(AddChildActivity.this, SelectChildActivity.class);
                    Child child = null;

                    try {
                        String name = etName.getText().toString();
                        String gender = genderSpinner.getSelectedItem().toString();
                        String age = etAge.getText().toString();
                        String id =  etID.getText().toString();
                        child = new Child(name, gender, age, id);

                        intent.putExtra("child", child);
                        setResult(RESULT_OK, intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
