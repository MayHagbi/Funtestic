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
//import butterknife.BindView;
//import butterknife.ButterKnife; //TODO: how it work?

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.input_name)
    private EditText etName;
//    @BindView(R.id.input_email)
    private EditText etEmail;
//    @BindView(R.id.input_password)
    private EditText etPassword;
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

        etName = (EditText) findViewById(R.id.input_name_sign_up);
        etEmail = (EditText) findViewById(R.id.input_email_sign_up);
        etPassword = (EditText) findViewById(R.id.input_password_sign_up);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        loginLink = (TextView) findViewById(R.id.link_login_sign_up_activity);
        etId = (EditText) findViewById(R.id.input_id_sign_up);
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
//        new android.os.Handler().postDelayed( //TODO: necessary?
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignUpSuccess or onSignUpFailed
//                        // depending on success
//                        onSignUpSuccess();
//                        // onSignUpFailed();
//
//                    }
//                }, 3000);
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
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String id = etId.getText().toString();

        // TODO: add the user to DB

        return false;
    }
}
