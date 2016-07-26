package com.hhsain.jobapp.core.model;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by hhsain on 24/07/16.
 */
public class Candidat extends RealmObject implements RealmModel,Feed {
    public int id;
    public Date postedDate ;
    public int currentRecommendation;
    public int experience ;
    public int age ;
    public String job ;
    public String lastName ;
    public String firstName ;
    public String picture ;

    public Candidat(){}


    @Override
    public int compareTo(Feed another) {
        return this.postedDate.compareTo(another.getPostedDate());
    }

    @Override
    public Date getPostedDate() {
        return postedDate;
    }
}
