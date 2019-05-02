package com.example.abjs.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button btnSave;
//    private EditText edtPunchSpeed;
//    private EditText edtPunchPower;
//    private EditText edtKickSpeed;
//    private EditText edtKickPower;
//    private EditText edtName;
//    private TextView txtGetData;
//    private Button btnAllData;
//    private String allKickBoxer;
//    private Button btnTranition;
    private Button btnSignUp, btnLogInSignUp;
    private EditText edtEmailSignUp, edtPasswordSignUp, edtUsernameSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");

        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordSignUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogInSignUp = findViewById(R.id.btnLogInSugnUp);
        btnSignUp.setOnClickListener(MainActivity.this);
        btnLogInSignUp.setOnClickListener(MainActivity.this);
        if(ParseUser.getCurrentUser()!= null){
            transitionToSocialMediaAvtivity();
//            ParseUser.getCurrentUser().logOut();
        }
//        btnSave = findViewById(R.id.btnSave);
//        btnSave.setOnClickListener(MainActivity.this);
//        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
//        edtPunchPower = findViewById(R.id.edtPunchPower);
//        edtKickSpeed = findViewById(R.id.edtKickSpeed);
//        edtKickPower = findViewById(R.id.edtKickPower);
//        edtName = findViewById(R.id.edtName);
//        btnTranition = findViewById(R.id.btnTransition);
//        btnTranition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,SignUpLoginIn.class);
//                startActivity(intent);
//            }
//        });
//        btnAllData = findViewById(R.id.btnAllData);
//        btnAllData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
//                allKickBoxer ="";
//                queryAll.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if(e==null){
//                            if(objects.size()>0){
//                                for (ParseObject allobjects:objects){
//                                    allKickBoxer = allKickBoxer + allobjects.get("name")+ "\n";
//
//                                }
//                                FancyToast.makeText(MainActivity.this,allKickBoxer,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
//
//
//                            }
//                        }
//                    }
//                });
//            }
//        });
//        txtGetData = findViewById(R.id.txtGetData);
//        txtGetData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
//                parseQuery.getInBackground("ywQfoBu5Vx", new GetCallback<ParseObject>() {
//                    @Override
//                    public void done(ParseObject object, ParseException e) {
//                         if (object!=null && e==null){
//                             txtGetData.setText(object.get("name")+"");
//
//                         }
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        try {
//            final ParseObject kickboxer = new ParseObject("KickBoxer");
//            kickboxer.put("name", edtName.getText().toString());
//            kickboxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
//            kickboxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
//            kickboxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
//            kickboxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
//            kickboxer.saveInBackground(new SaveCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e == null) {
//                        FancyToast.makeText(MainActivity.this, kickboxer.get("name") + " saved successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
////                    Toast.makeText(MainActivity.this,kickboxer.get("name")+" saved successfully",Toast.LENGTH_LONG).show();
//                    } else {
//                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
////                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        } catch (Exception e){
//            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
//        }
//


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSignUp:
                if(edtUsernameSignUp.getText().toString().equals("")|| edtPasswordSignUp.getText().toString().equals("")|| edtEmailSignUp.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,"Emai, Username and password needs to be entered",FancyToast.LENGTH_LONG,FancyToast.WARNING,true).show();
                }
                else {
                    final ParseUser parseUser = new ParseUser();
                    parseUser.setEmail(edtEmailSignUp.getText().toString());
                    parseUser.setUsername(edtUsernameSignUp.getText().toString());
                    parseUser.setPassword(edtPasswordSignUp.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage(edtUsernameSignUp.getText().toString() + "  is being signed up");
                    progressDialog.show();
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, parseUser.getUsername() + " is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaAvtivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogInSugnUp:
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                break;
        }


    }
    public void RootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void transitionToSocialMediaAvtivity(){
        Intent intent = new Intent(MainActivity.this,SociaMediaActivity.class);
        startActivity(intent);
        finish();
    }
}

