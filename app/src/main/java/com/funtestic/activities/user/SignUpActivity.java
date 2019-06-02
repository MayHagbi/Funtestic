package com.funtestic.activities.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.funtestic.R;
import com.funtestic.models.DataBase;
import com.funtestic.models.User;
import com.funtestic.utilities.Validations;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private EditText etEmail;
    private Button btnSignUp;
    private TextView tvLoginLink;
    private ProgressDialog signUpProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_layout);

        etFirstName = (EditText) findViewById(R.id.input_firstName);
        etLastName = (EditText) findViewById(R.id.input_lastName);
        etPhoneNumber = (EditText) findViewById(R.id.input_phoneNumber);
        etPassword = (EditText) findViewById(R.id.input_password_sign_up);
        etEmail = (EditText) findViewById(R.id.input_email_sign_up);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        tvLoginLink = (TextView) findViewById(R.id.link_login_sign_up_activity);

        signUpProgressDialog = new ProgressDialog(this);
        btnSignUp.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    public void signUp() {
        btnSignUp.setEnabled(false);

        if (!validate() || !addUserToDB() ) {
            onSignUpFailed();
            Log.d("TTTT","HIS FINISH");
            return;
        }

        Log.d("TTTT","dont need to by here ");

        signUpProgressDialog.setMessage(getResources().getString(R.string.please_wait));
        signUpProgressDialog.show();
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
    }

    public boolean validate() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String password = etPassword.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        if (!Validations.isEmailValid(email)) {
            valid = false;
            etEmail.setError(getString(R.string.email_not_valid));
        }
        if (!Validations.isFullNameValid(firstName)) {
            valid = false;
            etFirstName.setError(getString(R.string.enter_first_name));
        }
        if (!Validations.isFullNameValid(lastName)) {
            valid = false;
            etLastName.setError(getString(R.string.enter_last_name));
        }
        if (!Validations.isPasswordValid(password)) {
            valid = false;
            etPassword.setError(getString(R.string.password_too_short));
        }
        if (!Validations.isValidPhone(phoneNumber)) {
            valid = false;
            etPhoneNumber.setError((getString(R.string.phone_not_valid)));
        }
        return valid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                signUp();
                break;
            case R.id.link_login_sign_up_activity:
                finish();
                break;
        }
    }

    private void onSignUpFailed() {
        Toast.makeText(getBaseContext(), getString(R.string.sign_up_failed), Toast.LENGTH_LONG).show();
        btnSignUp.setEnabled(true);
    }

    private boolean addUserToDB() {
        String first_name = etFirstName.getText().toString();
        String last_name = etLastName.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        String pass = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        User tempU = new User(first_name,last_name,phone,email,pass);
        signUpProgressDialog.dismiss();
        return DataBase.getInstance().addUserToDb(tempU);

    }
}
