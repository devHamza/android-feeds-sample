package com.hhsain.jobapp.core.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by hhsain on 24/07/16.
 */
public class Job extends RealmObject implements Feed{
    public int viewedCount;
    public Date postedDate ;
    public int submittedCount;
    public String picture ;
    public String postedBy ;
    public String title ;

    public Job(){}

    @Override
    public Date getPostedDate() {
        return postedDate;
    }

    @Override
    public int compareTo(Feed another) {
        return this.postedDate.compareTo(another.getPostedDate());
    }
}
