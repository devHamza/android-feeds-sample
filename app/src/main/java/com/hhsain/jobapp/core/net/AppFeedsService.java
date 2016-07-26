package com.hhsain.jobapp.core.net;

import com.hhsain.jobapp.core.model.Job;
import com.hhsain.jobapp.core.model.Candidat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hhsain on 24/07/16.
 */
public interface AppFeedsService {
    @GET(Api.CANDIDATS_URL)
    Call<List<Candidat>> listProfils();

    @GET(Api.JOBS_URL)
    Call<List<Job>> listOffres();
}
