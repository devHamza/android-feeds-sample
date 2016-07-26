package com.hhsain.jobapp.core.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hhsain.jobapp.BuildConfig;
import com.hhsain.jobapp.core.model.Candidat;
import com.hhsain.jobapp.core.model.Job;
import com.hhsain.jobapp.core.repository.FeedSupplier;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hhsain on 24/07/16.
 */
public class FeedsDataSourceImpl implements FeedsDataSource{

    private AppFeedsService mAppFeedsService;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public FeedsDataSourceImpl()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mAppFeedsService = retrofit.create(AppFeedsService.class);
    }

    public FeedSupplier<List<Job>> getJobs()
    {
        if(!BuildConfig.JOB)
            return new FeedSupplier<>();

        return new FeedSupplier<>(mAppFeedsService.listOffres(),Job.class);
    }

    public FeedSupplier<List<Candidat>> getCandidats()
    {
        if(!BuildConfig.CANDIDAT)
            return new FeedSupplier<>();

        return new FeedSupplier<>(mAppFeedsService.listProfils(),Candidat.class);
    }
}
