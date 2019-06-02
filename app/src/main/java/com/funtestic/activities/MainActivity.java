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
import com.funtestic.models.DataBase;
import com.funtestic.models.Send_HTTP_Request;
import com.funtestic.models.User;

import org.json.JSONObject;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button signinBtn;
    private Button signupBtn;
    private EditText userPhone;
    private EditText userPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        signinBtn = (Button) findViewById(R.id.signinBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        userPhone = (EditText) findViewById(R.id.userPhone);
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


    public final static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null || target.length() < 6 || target.length() > 13) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }

    }

    public final boolean isValidPhone(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            Toast.makeText(this,R.string.please_enter_phone, Toast.LENGTH_LONG).show();
            return false;
        }
        if (target == null || target.length() < 10 || target.length() > 13) {
            return false;
        }
        else if(!android.util.Patterns.PHONE.matcher(target).matches()) {
                Toast.makeText(this,R.string.phone_not_valid, Toast.LENGTH_LONG).show();
                return false;
        }
        return true;
    }


    private void userLogin( String phone ,String password ){


        //Checking if email and password are valid

        if(!isValidPassword(password)) {
            isValidPhone(phone);
            return ;
        }

        if(!isValidPhoneNumber(phone)){
            isValidPassword(password);
            return ;
        };


        progressDialog.setMessage(getResources().getString(R.string.checking_user_details));
        progressDialog.show();

        DataBase db = DataBase.getInstance();


        if(db.login(phone,password)) //user is valid
        {
            progressDialog.setMessage(getResources().getString(R.string.please_wait));
            progressDialog.show();
//            Intent intent = new Intent(getBaseContext(), TwoStepVerificationActivity.class);
//            intent.putExtra("phone", phone);
//            intent.putExtra("password", password);
//            startActivity(intent);
//            this.finish();
        }
        else //bad username/password
        {
            progressDialog.setMessage(getResources().getString(R.string.bad_username_password));
            progressDialog.show();
        }

    }

    @Override
    public void onClick(View view){
        if(view == signinBtn){
            String phone = userPhone.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            userLogin(phone ,password);
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
