package com.example.mariam.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetAll;
    private EditText edtName, edtPunchSpeed, edtPunchPower,edtKickSpeed;
    private TextView txtGetData;
    private String allKickBoxers;

    private Button btnTransition;

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

        btnGetAll = findViewById(R.id.btnGetAll);

        btnTransition =findViewById(R.id.btnNextActivity);

        txtGetData = findViewById(R.id.txtGetData);
//        txtGetData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kick_Boxer");
//                parseQuery.getInBackground("kQIMPDdnhv", new GetCallback<ParseObject>(){
//                    @Override
//                 public void done(ParseObject object, ParseException e) {
//                       if(object != null && e == null){
//                           txtGetData.setText(object.get("name") + " - " + "Punch Power is: " + object.get("punchPower"));
//                      }
//                    }
//                });
//
//
//            }
//        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                final ParseQuery<ParseObject> GetAllData = ParseQuery.getQuery("Kick_Boxer");

              //  GetAllData.whereGreaterThan("punchPower", 1000);
             //   GetAllData.whereGreaterThanOrEqualTo("punchSpeed", 500);
               // GetAllData.setLimit(1);

                GetAllData.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject kickBoxer : objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                   //كل المعلومات عن الkickBoxers
                                    // allKickBoxers = allKickBoxers + kickBoxer.get("name")+ " punch power is:  " + kickBoxer.get("punchPower")
                                   //    + " punch speed is: " + kickBoxer.get("punchSpeed")+ " kick speed is:  " + kickBoxer.get("kickSpeed") +  "\n";
                                }
                                FancyToast.makeText(MainActivity.this, allKickBoxers,
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                        }
                    }
                });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        SignUpLogInActivity.class);
                startActivity(intent);

            }
        });

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


