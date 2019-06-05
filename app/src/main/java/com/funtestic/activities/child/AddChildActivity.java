package com.funtestic.activities.child;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.models.Child;
import com.funtestic.models.DataBase;
import com.funtestic.models.User;
import com.funtestic.utilities.Validations;

import java.io.Serializable;

import static com.funtestic.utilities.SharedConstants.PREFS_NAME;


public class AddChildActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etAge;
    private EditText etID;
    private Spinner genderSpinner;
    private Button btnSubChild;
    private SharedPreferences sp;
    private User parent ;
    private String token ;
    private String phone;
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_layout);

        sp = this.getSharedPreferences(PREFS_NAME,0);
        phone= sp.getString("phone","DEFAULT");
        token= sp.getString("token", "DEFAULT");
        parent = DataBase.getInstance().getUserByPhone(phone, token);

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
                break;
        }
    }

    private boolean addChild() {
        boolean valid = true;
        if (!Validations.isAgeValid(etAge.getText().toString())) {
            etAge.setError("Please enter valid age");
            valid = false;
        }
        if (!Validations.isIdValid(etID.getText().toString())) {
            etID.setError("Please enter valid ID");
            valid = false;
        }
        if (!Validations.isFullNameValid(etName.getText().toString())) {
            etName.setError("Please enter valid name");
            valid = false;
        }
        if (valid) {
            String name = etName.getText().toString();
            String gender = genderSpinner.getSelectedItem().toString();
            String age = etAge.getText().toString();
            String id =  etID.getText().toString();
            Child child = new Child(gender ,age,name, id, parent);;
            if(DataBase.getInstance().addChildToDb(child, token)){
                Intent intent = new Intent(AddChildActivity.this, SelectChildActivity.class);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        }
        Log.d("ERROR","can not insert child in to DB !!");
        Toast.makeText(this, "Try again", Toast.LENGTH_LONG);
        return false;
    }
}
