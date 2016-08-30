package com.example.android.material_design;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by sunny on 3/1/2016.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance=null;
  private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(MyApplication.getAppContext());


        mImageLoader= new ImageLoader(mRequestQueue,new ImageLoader.ImageCache(){




            private LruCache<String,Bitmap> cache= new LruCache<>((int)Runtime.getRuntime().maxMemory()/1024/8);


            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url,bitmap);
            }
        });
    }


    public static VolleySingleton getInstance(){

        if(sInstance==null)
        {
            sInstance= new VolleySingleton();

        }
        return sInstance;
    }

    public RequestQueue getmRequestQueue()
    {
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }
}
