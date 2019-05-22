package com.funtestic.activities.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.funtestic.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.input_name)
    private EditText firstName;
    private EditText lastName;
//    @BindView(R.id.input_email)
    private EditText phoneNumber;
//    @BindView(R.id.input_password)
    private EditText password;
//    @BindView(R.id.btn_signUp)
    private Button btnSignUp;
//    @BindView(R.id.link_login)
    private TextView loginLink;
    private EditText etId;
    private ProgressDialog signUpProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity_layout);
//        ButterKnife.bind(this);

        firstName = (EditText) findViewById(R.id.input_firstName);
        lastName = (EditText) findViewById(R.id.input_lastName);
        phoneNumber = (EditText) findViewById(R.id.input_phoneNumber);
        password = (EditText) findViewById(R.id.input_password_sign_up);
        etId = (EditText) findViewById(R.id.input_id_sign_up);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        loginLink = (TextView) findViewById(R.id.link_login_sign_up_activity);
        signUpProgressDialog = new ProgressDialog(this);
        btnSignUp.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    public void signUp() {
        btnSignUp.setEnabled(false);

        if (!validate()) {
            onSignUpFailed();
            return;
        }

        //TODO: ProgressDialog deprecated, try to use progressbar
        signUpProgressDialog.setIndeterminate(true);
        signUpProgressDialog.setMessage(getString(R.string.creating_account));
        signUpProgressDialog.show();

        if(!addUserToDB()) {
            onSignUpFailed();
        }

        btnSignUp.setEnabled(true);
        finish();
        signUpProgressDialog.dismiss();
    }

    public boolean validate() {
        //TODO: call validation functions from utilities
        return false;
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
        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String phone = phoneNumber.getText().toString();
        String pass = password.getText().toString();
        String id = etId.getText().toString();

        // TODO: add the user to DB

        return false;
    }
}
