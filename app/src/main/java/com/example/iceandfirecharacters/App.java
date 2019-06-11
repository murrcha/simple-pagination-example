package com.example.iceandfirecharacters;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.iceandfirecharacters.network.IceAndFireApi;
import com.example.iceandfirecharacters.network.IceAndFireService;

/**
 * App
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public class App extends Application {

    @NonNull
    private IceAndFireService service;

    @Override
    public void onCreate() {
        super.onCreate();

        service = IceAndFireService.getInstance();
    }

    @NonNull
    public IceAndFireApi getApi() {
        return service.getApi();
    }
}
