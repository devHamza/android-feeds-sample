package com.hhsain.jobapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by hhsain on 23/07/16.
 */
public class JobFeedsApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initRealMCacheConfig();
    }

    private void initRealMCacheConfig(){
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
