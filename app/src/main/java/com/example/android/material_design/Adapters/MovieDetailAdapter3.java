package com.example.android.material_design.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.android.material_design.Model.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Ekta on 01-09-2016.
 */

public class MovieDetailAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Movie movie;
    private ArrayList<String> trailerInfo = new ArrayList<>();
    private ArrayList<String> reviewsInfo = new ArrayList<>();
    private LayoutInflater layoutInflater;
    android.content.Context contextt;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private String dataZero;
    int a;


    public MovieDetailAdapter3(ArrayList<String> reviewsInfo, Context context) {
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




            View view = layoutInflater.inflate(R.layout.movie_reviewinfo_holder, parent, false);
            viewHolder = new ReviewsViewHolder(view);
            a = 0;
            return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

                final String[] ReviewData =  reviewsInfo.get(position ).split(",");
        ((ReviewsViewHolder) holder).reviwerName.clearFocus();
                ((ReviewsViewHolder) holder).reviwerName.setText(ReviewData[0]);
        ((ReviewsViewHolder) holder).reviwerDesc.clearFocus();
                ((ReviewsViewHolder) holder).reviwerDesc.setText(ReviewData[1]);


    }

    @Override
    public int getItemCount() {
        return reviewsInfo.size();
    }


    class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private TextView reviwerName, reviwerDesc;


        public ReviewsViewHolder(View itemView) {
            super(itemView);


            reviwerName = (TextView) itemView.findViewById(R.id.tvReviewName);
            reviwerDesc = (TextView) itemView.findViewById(R.id.tvReviewDesc);

        }
    }

}