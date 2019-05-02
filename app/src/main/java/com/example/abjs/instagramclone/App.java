package com.example.abjs.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("5sBzIBfMXVlOvrQlPslWV0E9xPvPnf1eEACI3mmD")
                // if defined
                .clientKey("qdyYm9xKwODW84RF9AI3MqmC4GvNF4HzbK78oiS0")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
