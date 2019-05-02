package com.example.abjs.instagramclone;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogIn, btnSignUpLogIn;
    private EditText edtUsernameLogIn, edtPasswordLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtUsernameLogIn = findViewById(R.id.edtUsernameLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnLogIn);
                }
                return false;
            }
        });
        btnSignUpLogIn = findViewById(R.id.btnSignUpLogIn);
        btnLogIn.setOnClickListener(Login.this);
        btnSignUpLogIn.setOnClickListener(Login.this);
        if(ParseUser.getCurrentUser()!=null){
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaAvtivity();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                if(edtUsernameLogIn.getText().toString().equals("")||edtPasswordLogIn.getText().toString().equals("")){
                    FancyToast.makeText(Login.this, "Username and Passwoed needs to be entered",FancyToast.LENGTH_LONG, FancyToast.WARNING,true).show();
                }
                else {
                    ParseUser.logInInBackground(edtUsernameLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null && user != null) {
                                transitionToSocialMediaAvtivity();
                                FancyToast.makeText(Login.this, user.getUsername() + " is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(Login.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUpLogIn:
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
    public void RootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaAvtivity(){
        Intent intent = new Intent(Login.this,SociaMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
