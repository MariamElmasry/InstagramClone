package com.example.mariam.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imgInstagramLogo;
    private TextView txtNotHaveAccount, txtSignUp;
    private EditText edtEmailLogin, edtPasswordLogin;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign in");

        imgInstagramLogo = findViewById(R.id.imgInstagramLogo);
        txtNotHaveAccount = findViewById(R.id.txtNotHaveAccount);
        txtSignUp = findViewById(R.id.txtSignup);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnLogin = findViewById(R.id.btnLogin);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtEmailLogin.getText().toString(), edtPasswordLogin.getText().toString(),
                        new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(MainActivity.this, user.get("username") + " is logged in successfully.",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        } else{
                            FancyToast.makeText(MainActivity.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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