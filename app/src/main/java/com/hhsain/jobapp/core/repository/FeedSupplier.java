package com.hhsain.jobapp.core.repository;

import android.support.annotation.NonNull;

import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hhsain.jobapp.core.util.CacheHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hhsain on 24/07/16.
 */
public  class FeedSupplier<T> implements Supplier<Result<T>> {

    private static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private Call<T> mHttpCall ;
    private CacheHelper cacheHelper ;
    private Class entityType;

    public FeedSupplier(Call<T> mCall, Class entityType){
        mHttpCall = mCall;
        this.entityType = entityType;

    }

    public FeedSupplier(){
        mHttpCall = null;
    }
    @NonNull
    @Override
    public Result<T> get() {
        List emptyResult = new ArrayList<>();
        if(mHttpCall == null ) return Result.success((T)emptyResult);

        Result<T> response = null ;
        try {
            cacheHelper = new CacheHelper();
            Response<T> httpResponse = mHttpCall.clone().execute();
            if(httpResponse.isSuccessful()){
                response =  Result.success(httpResponse.body());
                Gson gson = new GsonBuilder()
                        .setDateFormat(JSON_DATE_FORMAT)
                        .create();
                String json = gson.toJson(httpResponse.body());
                cacheHelper.storeJsonOnCache(json,true,entityType);
            }
            else
            {
                List results = cacheHelper.getAll(entityType);

                if(results != null && results.size() != 0) {
                    response =  Result.success((T)results);
                }
                else {
                    response =  Result.failure();
                }
            }
        }catch (IOException ex)
        {
            List results = cacheHelper.getAll(entityType);
            if(results != null && results.size() != 0) {
                //TODO : recuperer les données depuis le cache
                response =  Result.success((T)results);
            }
            else {
                response =  Result.failure();
            }
        }
        return response;
    }

}
