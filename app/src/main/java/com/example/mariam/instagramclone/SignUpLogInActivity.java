package com.example.mariam.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpLogInActivity extends AppCompatActivity {

    private EditText edtUsernameLogin, edtUsernameSignup, edtPasswordLogin, edtPasswordSignup;
    private Button btnSignup, btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUsernameSignup = findViewById(R.id.edtUsernameSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordSignup);
        edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUsernameSignup.getText().toString());
                appUser.setPassword(edtPasswordSignup.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if ( e == null ){
                            FancyToast.makeText(SignUpLogInActivity.this, appUser.get("username") + " is signed up successfully.",
                                   FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(SignUpLogInActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        }else {
                            FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(),
                                   FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
    @Override
    public void done(ParseUser user, ParseException e) {
        if (user != null && e == null){
            FancyToast.makeText(SignUpLogInActivity.this, user.get("username") + " is logged in successfully.",
                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

            Intent intent = new Intent(SignUpLogInActivity.this, WelcomeActivity.class);
            startActivity(intent);

        } else {
            FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(),
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
});

            }
        });



    }
}
