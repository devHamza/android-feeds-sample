package com.hhsain.jobapp.core.net;

import com.hhsain.jobapp.core.model.Candidat;
import com.hhsain.jobapp.core.model.Job;
import com.hhsain.jobapp.core.repository.FeedSupplier;

import java.util.List;

/**
 * Created by hhsain on 24/07/16.
 */
public interface FeedsDataSource {
    public FeedSupplier<List<Job>> getJobs();
    public FeedSupplier<List<Candidat>> getCandidats();
}
