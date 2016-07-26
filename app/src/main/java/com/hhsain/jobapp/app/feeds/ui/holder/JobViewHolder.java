package com.hhsain.jobapp.app.feeds.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhsain.jobapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hhsain on 24/07/16.
 */
public class JobViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.job_date)
    public TextView jobDate ;

    @BindView(R.id.job_image)
    public ImageView jobImage;

    @BindView(R.id.job_subtitle)
    public TextView jobSubtitle ;

    @BindView(R.id.job_title)
    public TextView jobTitle ;

    @BindView(R.id.job_users_count)
    public TextView jobUsers ;

    @BindView(R.id.job_views_count)
    public TextView jobViews ;

    public JobViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
