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


public class AddChildActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etName;
    private EditText etAge;
    private EditText etID;
    private Spinner genderSpinner;
    private Button btnSubChild;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_layout);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etID = (EditText) findViewById(R.id.etID);
        btnSubChild = (Button) findViewById(R.id.btnSubChild);
        genderSpinner = (Spinner) findViewById(R.id.GenderSpinner);

        btnSubChild.setOnClickListener(this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(AddChildActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genderList));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(myAdapter);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnSubChild:
                addChild();
                finish();
                break;
        }
    }

    private void addChild() {
        if (etAge.getText().toString().length()!=0 && etID.getText().toString().length()!=0 && etName.getText().toString().length()!=0) {
            Intent intent = new Intent(AddChildActivity.this, SelectChildActivity.class);
            Child child = null;

            try {
                String name = etName.getText().toString();
                String gender = genderSpinner.getSelectedItem().toString();
                String age = etAge.getText().toString();
                String id =  etID.getText().toString();
                //child = new Child(name, gender, age, id);

                //TODO add incomingChild object to child database

                //intent.putExtra("child", child);
                setResult(RESULT_OK, intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
