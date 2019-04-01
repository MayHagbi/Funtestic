package com.funtestic.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.funtestic.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button signinBtn;
    private Button signupBtn;
    private EditText userName;
    private EditText userPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        signinBtn = (Button) findViewById(R.id.signinBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        progressDialog = new ProgressDialog(this);


        signinBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);

    }


    public final boolean isValidPassword(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this,R.string.enter_password, Toast.LENGTH_LONG).show();
            return false;
        }
        if(target.length() < 8){
            Toast.makeText(this,R.string.password_too_short , Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }


    public final boolean isValidEmail(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this,R.string.enter_email, Toast.LENGTH_LONG).show();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()) {
            Toast.makeText(this,R.string.email_not_valid, Toast.LENGTH_LONG).show();
            return false;
        };

        return true;
    }


    private void userLogin( String email ,String password ){


        //Checking if email and password are valid

        if(!isValidPassword(password)) {
            isValidEmail(email);
            return ;
        }

        if(!isValidEmail(email)){
            isValidPassword(password);
            return ;
        };


        progressDialog.setMessage(getResources().getString(R.string.checking_user_details));
        progressDialog.show();

        //TODO Search for user name and password in data base, Encrypt password with SHA



    }

    @Override
    public void onClick(View view){
        if(view == signinBtn){
            String email = userName.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            userLogin(email ,password);
        }

        if(view == signupBtn )
        {
            //TODO move to sign up activity
        }

    }
}
