package com.example.abjs.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginIn extends AppCompatActivity {
    private EditText edtUserSignUp, edtPwdSighUp, edtUserSignIn, edtPwdSignIn;
    private Button btnSignUp, btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login_in);
        edtUserSignUp = findViewById(R.id.edtUserSignUp);
        edtPwdSighUp = findViewById(R.id.edtPwdSignUp);
        edtUserSignIn = findViewById(R.id.edtUserSignIn);
        edtPwdSignIn = findViewById(R.id.edtPwdSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(edtUserSignUp.getText().toString());
                parseUser.setPassword(edtPwdSighUp.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Intent intent = new Intent(SignUpLoginIn.this, WelcomeActivity.class);
                            startActivity(intent);
                            FancyToast.makeText(SignUpLoginIn.this,parseUser.getUsername().toString()+" signed up successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLoginIn.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserSignIn.getText().toString(), edtPwdSignIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user !=null && e==null){
                            FancyToast.makeText(SignUpLoginIn.this,user.getUsername().toString()+" signed in successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            Intent intent = new Intent(SignUpLoginIn.this, WelcomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            FancyToast.makeText(SignUpLoginIn.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });
    }
}
