package com.hhsain.jobapp.app.feeds.presenter;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.RepositoryConfig;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.hhsain.jobapp.app.feeds.ui.FeedsListContract;
import com.hhsain.jobapp.core.executor.NetworkExecutor;
import com.hhsain.jobapp.core.model.Candidat;
import com.hhsain.jobapp.core.model.Feed;
import com.hhsain.jobapp.core.model.Job;
import com.hhsain.jobapp.core.net.FeedsDataSourceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hhsain on 24/07/16.
 */
public class FeedsListPresenterImpl implements FeedsListPresenter,Updatable{

    private FeedsListContract activity ;


    protected FeedsDataSourceImpl mDataSource;

    private Repository<List<Feed>> mCandidatValueRepository;
    private MutableRepository<Integer> mRefreshRepo = Repositories.mutableRepository(0);

    public FeedsListPresenterImpl(FeedsListContract a){
        activity = a;
        //TODO : injection de dependance dagger
        mDataSource = new FeedsDataSourceImpl();
    }

    public void onCreate(){
        List<Feed> initialList = new ArrayList<>();
        mCandidatValueRepository = Repositories.repositoryWithInitialValue(initialList)
                .observe(mRefreshRepo)
                .onUpdatesPerLoop()
                .goTo(NetworkExecutor.EXECUTOR)
                .attemptGetFrom(mDataSource.getCandidats())
                .orSkip()
                .attemptMergeIn(mDataSource.getJobs(),FeedsListPresenterImpl::mergeJobsAndCandidats)
                .orSkip()
                .thenTransform(FeedsListPresenterImpl::sortResultJobs)
                .onConcurrentUpdate(RepositoryConfig.SEND_INTERRUPT)
                .compile();
    }

    public static Result<List<Feed>> mergeJobsAndCandidats(List<Candidat> input1, Result<List<Job>> input2){
        return input2.ifSucceededAttemptMerge(input1, (jobs, candidats) -> {
            try {
                List<Feed> resultList = new ArrayList<Feed>(candidats);
                resultList.addAll(jobs);
                return Result.present(resultList);
            } catch (RuntimeException e) {
                return Result.failure(e);
            }
        });
    }

    private static List<Feed> sortResultJobs(List<Feed> input){
        Collections.sort(input);
        return input;
    }

    public void onStart(){
        mCandidatValueRepository.addUpdatable(this);
        activity.setLoading(true);
    }

    public void onStop(){
        mCandidatValueRepository.removeUpdatable(this);

    }

    @Override
    public void update() {
        activity.refreshList(mCandidatValueRepository.get());
        activity.setLoading(false);
    }

    public void onRefreshList(){
        mRefreshRepo.accept(mRefreshRepo.get() + 1);
    }
}
