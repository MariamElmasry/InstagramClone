package com.example.mariam.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText edtEmailSignup, edtPasswordSignup, edtUsername, edtFullname;
    private Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Sign up");

        edtEmailSignup = findViewById(R.id.edtEmailSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordSignup);

        // لو اليوزر ضغط على enter فى الكيبورد
        edtPasswordSignup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    btnSignup.callOnClick();

                }

                return false;
            }
        });

        edtFullname = findViewById(R.id.edtFullName);
        edtUsername = findViewById(R.id.edtUsername);
        btnSignup = findViewById(R.id.btnSignup);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // كتابة كل الخانات
                if (edtEmailSignup.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtFullname.getText().toString().equals("") ||
                        edtPasswordSignup.getText().toString().equals("")) {

                    FancyToast.makeText(SignupActivity.this, "Email, Full name, Username and Password are required.",
                            FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmailSignup.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPasswordSignup.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            edtEmailSignup.getText().toString();
                            edtFullname.getText().toString();
                            edtUsername.getText().toString();
                            if (e == null) {
                                FancyToast.makeText(SignupActivity.this, appUser.get("username") + " is signed up successfully.",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                Intent welcome = new Intent(SignupActivity.this, WelcomeActivity.class);
                                startActivity(welcome);

                            } else {
                                FancyToast.makeText(SignupActivity.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            // إخفاء الديالوج بعد ميبقى run
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

    }

    // إخفاء الkeyboard لو دوسنا ف اى مكان فاضى فى الصفحة
    public void rootLayoutTapped (View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
