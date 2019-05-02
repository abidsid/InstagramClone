package com.example.abjs.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfession, edtHobbies, edtFavSport;
    private Button btnUpdate;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        edtFavSport = view.findViewById(R.id.edtFavSport);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser.get("ProfileName")==null){
            edtProfileName.setText("");
        }
        else {
            edtProfileName.setText(parseUser.get("ProfileName")+"");
        }
        if(parseUser.get("ProfileBio")==null){
            edtProfileBio.setText("");
        }
        else {
            edtProfileBio.setText(parseUser.get("ProfileBio")+"");
        }
        if(parseUser.get("Profession")==null){
            edtProfession.setText("");
        }
        else {
            edtProfession.setText(parseUser.get("Profession")+"");
        }
        if(parseUser.get("Hobbies")==null){
            edtHobbies.setText("");
        }
        else {
            edtHobbies.setText(parseUser.get("Hobbies")+"");
        }
        if(parseUser.get("FavouriteSport")==null){
            edtFavSport.setText("");
        }
        else {
            edtFavSport.setText(parseUser.get("FavouriteSport")+"");
        }
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("ProfileName",edtProfileName.getText().toString());
                parseUser.put("ProfileBio",edtProfileBio.getText().toString());
                parseUser.put("Profession",edtProfession.getText().toString());
                parseUser.put("Hobbies",edtHobbies.getText().toString());
                parseUser.put("FavouriteSport", edtFavSport.getText().toString());
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(getContext(),"Profile Updated",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                        }
                        else {
                            FancyToast.makeText(getContext(),e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

        return view;
    }

}
