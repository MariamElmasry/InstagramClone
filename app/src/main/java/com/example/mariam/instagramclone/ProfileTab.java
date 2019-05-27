package com.example.mariam.instagramclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

private EditText edtProfileName, edtProfileUsername, edtProfileBio, edtProfilePhoneNumber;
private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile_tab, container,
                false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileUsername = view.findViewById(R.id.edtProfileUsername);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfilePhoneNumber = view.findViewById(R.id.edtPhoneNumber);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        //get a reference of parseUser
        final ParseUser parseUser = ParseUser.getCurrentUser();

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileUserName", edtProfileUsername.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profilePhoneNumber", edtProfilePhoneNumber.getText().toString());

               /* if(parseUser.get("profileName") == null){
                    edtProfileName.setText("");
                }else{
                    edtProfileName.setText(parseUser.get("profileName").toString());
                }*/
                // عشان نحتفظ بالمعلومات كل مندخل
                edtProfileName.setText(parseUser.get("profileName")+"");
                edtProfileUsername.setText(parseUser.get("profileUserName") + "");
                edtProfileBio.setText(parseUser.get("profileBio") + "");
                edtProfilePhoneNumber.setText(parseUser.get("profilePhoneNumber") + "");


                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(getContext(), "Info Updated",
                                    FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        }else {
                            FancyToast.makeText(getContext(), e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        return view;
    }

}
