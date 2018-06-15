package com.example.ahmed.cryptocurrencyliveapplication.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class NetworkManager {
    private static NetworkManager networkManagerInstance;
    private final Context mContext;
    public RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Map<String, String> headers;

    private NetworkManager(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        mContext = context;
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (networkManagerInstance == null) {
            networkManagerInstance = new NetworkManager(context);
        }
        return networkManagerInstance;
    }

    public <T> void addToRequestQueue(Request<T> req, String requestTag) {
        req.setTag(requestTag);
        mRequestQueue.add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


    public Cache getCahce() {
        return mRequestQueue.getCache();
    }

    public static void destroyNetworkManager() {
        networkManagerInstance = null;
    }

    public static void cancelAllRequests(String RequestTag) {
        if (networkManagerInstance != null && networkManagerInstance.mRequestQueue != null)
            networkManagerInstance.mRequestQueue.cancelAll(RequestTag);
    }


}