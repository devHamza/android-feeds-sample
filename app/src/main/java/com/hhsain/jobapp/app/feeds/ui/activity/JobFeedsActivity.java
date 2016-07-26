package com.hhsain.jobapp.app.feeds.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hhsain.jobapp.JobFeedsAbstractActivity;
import com.hhsain.jobapp.R;
import com.hhsain.jobapp.app.feeds.presenter.FeedsListPresenter;
import com.hhsain.jobapp.app.feeds.presenter.FeedsListPresenterImpl;
import com.hhsain.jobapp.app.feeds.ui.FeedsListContract;
import com.hhsain.jobapp.app.feeds.ui.adapter.JobFeedsAdapter;
import com.hhsain.jobapp.core.model.Feed;

import java.util.List;

import butterknife.BindView;

public class JobFeedsActivity extends JobFeedsAbstractActivity implements FeedsListContract{
    @BindView(R.id.refresh_btn)
    TextView refreshBtn;

    @BindView(R.id.feeds_list)
    RecyclerView feedsListRv;

    //TODO : injection de dependance dagger
    FeedsListPresenter presenter ;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FeedsListPresenterImpl(this);
        presenter.onCreate();
        initProgressDialog();
        refreshBtn.setOnClickListener(view -> presenter.onRefreshList());
    }

    @Override
    public void refreshList(List<Feed> listFeeds) {
        JobFeedsAdapter jfa = new JobFeedsAdapter(listFeeds,this);
        feedsListRv.setAdapter(jfa);
        feedsListRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setLoading(boolean show) {
        if(show && progress != null)
            progress.show();
        else
            progress.hide();
    }

    private void initProgressDialog(){
        progress = new ProgressDialog(this);
        progress.setTitle(R.string.loading);
        progress.setMessage(getString(R.string.loading_text));
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_job_feeds;
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        progress.dismiss();
        super.onStop();
    }

}
