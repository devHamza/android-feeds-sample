package com.hhsain.jobapp.app.feeds.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhsain.jobapp.R;
import com.hhsain.jobapp.app.feeds.ui.holder.CandidatViewHolder;
import com.hhsain.jobapp.app.feeds.ui.holder.JobViewHolder;
import com.hhsain.jobapp.core.model.Candidat;
import com.hhsain.jobapp.core.model.Feed;
import com.hhsain.jobapp.core.model.Job;
import com.hhsain.jobapp.core.util.NetworkUtils;
import com.hhsain.jobapp.core.util.PicassoCache;

import java.util.List;

/**
 * Created by hhsain on 24/07/16.
 */
public class JobFeedsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_CANDIDAT = 1 ;
    private static final int TYPE_JOB = 2 ;

    private List<Feed> _feeds ;
    private Context _c ;

    public JobFeedsAdapter(List<Feed> listFeeds, Context context){
        _feeds = listFeeds;
        _c = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View renderView = null ;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case TYPE_CANDIDAT :
                renderView = inflater.inflate(R.layout.component_candidat_item,parent,false);
                viewHolder = new CandidatViewHolder(renderView);
                break;
            case TYPE_JOB :
                renderView = inflater.inflate(R.layout.component_job_item,parent,false);
                viewHolder = new JobViewHolder(renderView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case TYPE_CANDIDAT :
                CandidatViewHolder mHolder = (CandidatViewHolder)holder;
                Candidat mCandidat = (Candidat)_feeds.get(position);
                bindCandidatView(mCandidat,mHolder);
                break;
            case TYPE_JOB :
                JobViewHolder mJobHolder = (JobViewHolder)holder;
                Job mJob = (Job)_feeds.get(position);
                bindJobView(mJob,mJobHolder);
                break;
        }
    }

    private void bindCandidatView(Candidat mCandidat, CandidatViewHolder mHolder){
        mHolder.itemExp.setText(_c.getString(R.string.exp_label, mCandidat.experience));
        mHolder.itemAge.setText("" + mCandidat.age);
        mHolder.itemRecommander.setText(_c.getString(R.string.rec_label, mCandidat.currentRecommendation));
        mHolder.itemRecommander.setOnClickListener(view ->  {
            mCandidat.currentRecommendation += 1;
            JobFeedsAdapter.this.notifyDataSetChanged();
        });
        String name = mCandidat.firstName + " " + mCandidat.lastName ;
        mHolder.itemTitle.setText(name);
        mHolder.itemSubTitle.setText(mCandidat.job);
        PicassoCache.getPicassoInstance(_c)
                .load(mCandidat.picture)
                .placeholder(R.drawable.default_placeholder)
                .fit()
                .into(mHolder.itemImage);

    }

    private void bindJobView(Job mJob, JobViewHolder mHolder){
        mHolder.jobDate.setText(_c.getString(R.string.job_date_label, NetworkUtils.formatDate(mJob.postedDate)));
        mHolder.jobSubtitle.setText(mJob.postedBy);
        mHolder.jobTitle.setText(mJob.title);
        mHolder.jobUsers.setText("" + mJob.submittedCount);
        mHolder.jobViews.setText("" + mJob.viewedCount);
        PicassoCache.getPicassoInstance(_c)
                .load(mJob.picture)
                //.networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.default_placeholder)
                .fit()
                .into(mHolder.jobImage);

    }



    @Override
    public int getItemCount() {
        return _feeds.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(_feeds.get(position) instanceof Job)
            return TYPE_JOB;
        else if(_feeds.get(position) instanceof Candidat)
            return TYPE_CANDIDAT;
        return -1;
    }
}
