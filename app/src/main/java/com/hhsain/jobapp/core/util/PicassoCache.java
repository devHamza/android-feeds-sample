package com.hhsain.jobapp.core.util;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Created by hhsain on 25/07/16.
 */
public class PicassoCache {

    /**
     * Static Picasso Instance
     */
    private static Picasso picassoInstance = null;

    /**
     * PicassoCache Constructor
     *
     * @param context application Context
     */
    private PicassoCache (Context context) {

        //Downloader downloader   = new com.squareup.picasso.OkHttpDownloader(context, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
        //builder.downloader(downloader);
        //Todo add intercepteur pour les appels ws

        picassoInstance = builder.build();
    }

    /**
     * Get Singleton Picasso Instance
     *
     * @param context application Context
     * @return Picasso instance
     */
    public static Picasso getPicassoInstance (Context context) {

        if (picassoInstance == null) {

            new PicassoCache(context);
            return picassoInstance;
        }

        return picassoInstance;
    }

}
