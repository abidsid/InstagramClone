package com.example.abjs.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPost extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);
        linearLayout = findViewById(R.id.linearLayout);
        Intent receivedItems = getIntent();
        final String receivedUserName=receivedItems.getStringExtra("username");

        setTitle(receivedUserName+"'s Posts");
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("username", receivedUserName);
        parseQuery.orderByDescending("createAt");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null){
                    for (ParseObject user:objects){
                        final TextView description = new TextView(UsersPost.this);
                        description.setText(user.get("Description")+"");
                        ParseFile post = (ParseFile) user.get("picture");
                        post.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(data!= null && e==null){
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView postImage = new ImageView(UsersPost.this);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(5,5,5,5);
                                    postImage.setLayoutParams(params);
                                    postImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImage.setImageBitmap(bitmap);
                                    LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5,5,5,5);
                                    description.setLayoutParams(des_params);
                                    description.setGravity(Gravity.CENTER);
                                    description.setBackgroundColor(Color.BLUE);
                                    description.setTextColor(Color.WHITE);
                                    description.setTextSize(30f);

                                    linearLayout.addView(postImage);
                                    linearLayout.addView(description);

                                }
                            }
                        });
                    }
                }else {
                    FancyToast.makeText(UsersPost.this,"No posts available",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                    finish();
                }
                progressDialog.dismiss();

            }
        });

    }
}
