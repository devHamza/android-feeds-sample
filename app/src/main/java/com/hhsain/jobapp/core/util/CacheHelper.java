package com.hhsain.jobapp.core.util;

import com.hhsain.jobapp.core.model.Candidat;
import com.hhsain.jobapp.core.model.Job;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by hhsain on 25/07/16.
 */
public class CacheHelper {
    private Realm realm;

    public CacheHelper(){
        realm = Realm.getDefaultInstance();
    }

    public void storeJsonOnCache(String json , boolean ecrase, Class dataType)
    {
        if(ecrase){
            deleteLocalData(dataType);
        }
        realm.beginTransaction();
        realm.createAllFromJson(dataType,json);
        realm.commitTransaction();
    }

    private void deleteLocalData(Class entity)
    {
        final RealmResults results = realm.where(entity).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

    public List getAll(Class entity) {
        RealmResults result= realm.where(entity).findAll();
        return realm.copyFromRealm(result);
    }

    public List<Job> getAllJobs() {
        RealmResults<Job> result =  realm.where(Job.class).findAll();
        return realm.copyFromRealm(result);
    }

    public List<Candidat> getAllCandidats() {
        RealmResults<Candidat> result =  realm.where(Candidat.class).findAll();
        return realm.copyFromRealm(result);
    }


}
