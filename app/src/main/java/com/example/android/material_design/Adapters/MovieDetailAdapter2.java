package com.example.android.material_design.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.android.material_design.Model.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Ekta on 01-09-2016.
 */

public class MovieDetailAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    String coverImage;


    private Movie movie;
    private ArrayList<String> trailerInfo = new ArrayList<>();
    private ArrayList<String> reviewsInfo = new ArrayList<>();
    private LayoutInflater layoutInflater;
    android.content.Context contextt;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private String dataZero;
    int a,b;
    boolean review=false;



    public MovieDetailAdapter2(ArrayList<String> trailerInfo, Context context) {
        contextt = context;
        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.trailerInfo = trailerInfo;
    }
    public MovieDetailAdapter2(ArrayList<String> reviewsInfo, Context context,boolean review) {
        contextt = context;
        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.reviewsInfo=reviewsInfo;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;


    View view = layoutInflater.inflate(R.layout.movie_trailerinfo_holder, parent, false);
    viewHolder = new TrailersViewHolder(view);

    return viewHolder;



    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


            final String[] data = trailerInfo.get(position).split(",");

            dataZero = data[0];
            String coverImage = "http://img.youtube.com/vi/" + data[0] + "/mqdefault.jpg";
            if (coverImage != null) {
                imageLoader.get(coverImage, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                        ((TrailersViewHolder) holder).trailerImageView.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
            }

            ((TrailersViewHolder) holder).trailerTitle.setText(data[1]);
            Log.v("tag", "message");




    }
    @Override
    public int getItemCount() {

        return trailerInfo.size();

    }





    class TrailersViewHolder extends RecyclerView.ViewHolder {

        private TextView trailerTitle;
        private ImageView trailerImageView;
        private RelativeLayout trailer;


        public TrailersViewHolder(View itemView) {
            super(itemView);


            trailerTitle = (TextView) itemView.findViewById(R.id.ivTrailerTitle);
            trailerImageView = (ImageView) itemView.findViewById(R.id.ivTrailerImage);

        }
    }

}