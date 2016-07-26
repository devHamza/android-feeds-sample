package com.hhsain.jobapp.app.feeds.ui;

import com.hhsain.jobapp.core.model.Feed;

import java.util.List;

/**
 * Created by hhsain on 24/07/16.
 */
public interface FeedsListContract{
    void refreshList(List<Feed>listFeeds);
    void setLoading(boolean state);
}
