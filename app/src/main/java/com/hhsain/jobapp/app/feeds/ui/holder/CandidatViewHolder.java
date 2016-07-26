package com.hhsain.jobapp.app.feeds.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhsain.jobapp.BuildConfig;
import com.hhsain.jobapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hhsain on 24/07/16.
 */
public class CandidatViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title)
    public TextView itemTitle;

    @BindView(R.id.item_age_number)
    public TextView itemAge;

    @BindView(R.id.item_subtitle)
    public TextView itemSubTitle ;

    @BindView(R.id.item_exp)
    public TextView itemExp;

    @BindView(R.id.recommander_tv)
    public TextView itemRecommander;

    @BindView(R.id.contact_tv)
    public TextView itemContact;

    @BindView(R.id.item_image)
    public ImageView itemImage;

    public CandidatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        if(BuildConfig.CURRENT_USER.equals("recruteur"))
            itemContact.setVisibility(View.VISIBLE);

    }
}
