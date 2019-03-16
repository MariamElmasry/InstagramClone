package com.example.mariam.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower,edtKickSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed= findViewById(R.id.edtPunchSpeed);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);

        btnSave = findViewById(R.id.btnKickBoxer);
        btnSave.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {

        try {

            final ParseObject kickBoxer = new ParseObject("Kick_Boxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("name") + " is saved to server.",
                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } 
                }
            });
        } catch ( Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(),
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}


//    public void helloWorldTapped (View view){
//
//        ParseObject boxer = new ParseObject("Boxer");
//
//        boxer.put("punch_speed", 200);
//
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null){
//                    Toast.makeText(MainActivity.this, "Boxer object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }
//
//    public void kickBoxerTapped (View view){
//
//        ParseObject kickBoxer = new ParseObject("Kick_Boxer");
//
//        kickBoxer.put("name", "Ahmed");
//        kickBoxer.put("punch_speed", 100);
//        kickBoxer.put("kick_speed", 50);
//        kickBoxer.put("kick_power", 80);
//
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//                    Toast.makeText(MainActivity.this, "Kick Boxer object is saved successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }


