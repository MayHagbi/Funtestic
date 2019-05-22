package com.funtestic.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.user.SignUpActivity;
import com.funtestic.models.Send_HTTP_Request;

import java.security.MessageDigest;

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

        //YOELLLLLL
        new Thread(new Runnable() {
            public void run() {
                // a potentially time consuming task
                try {
                    Log.d("TTTT",Send_HTTP_Request.call_me());
                } catch (Exception e) {
                    Log.d("FFFF", String.valueOf(e));


                }
            }
        }).start();
        //YOELLLLL

        Log.d("TTTTT","PATHH");
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
        // Show message in case of bad username/password

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
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        }

    }

    public String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
