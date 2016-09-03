package com.example.android.material_design.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by Ekta on 29-08-2016.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Movie movie;
    private ArrayList<String> trailerInfo = new ArrayList<>();
    private ArrayList<String> reviewsInfo = new ArrayList<>();
    private LayoutInflater layoutInflater;
    android.content.Context contextt;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private String dataZero;
    int a;

    public MovieDetailAdapter(Movie movie, Context context) {
        contextt = context;
        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.movie = movie;
        //this.trailerInfo = trailerInfo;
        //this.movieReviews = movieReviews;
    }

    public MovieDetailAdapter(ArrayList<String> trailerInfo, Context context) {
        contextt = context;
        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.trailerInfo = trailerInfo;
    }

    public MovieDetailAdapter(ArrayList<String> reviewsInfo, Context context, int review) {
        contextt = context;
        a = review;
        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.reviewsInfo = reviewsInfo;
    }

    public MovieDetailAdapter(Movie movie,ArrayList<String> trailerInfo,ArrayList<String> reviewsInfo, Context context) {
        contextt = context;

        layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        // this.context = context;
        this.reviewsInfo = reviewsInfo;

        this.trailerInfo = trailerInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if (viewType == 0) {
            View view = layoutInflater.inflate(R.layout.movie_detail_holder, parent, false);
            viewHolder = new MovieDetailViewHolder(view);
            return viewHolder;
        }

        if (viewType == 1) {
            View view = layoutInflater.inflate(R.layout.movie_trailerinfo_holder, parent, false);
            viewHolder = new TrailersViewHolder(view);

            return viewHolder;
        }

        if (viewType == 2) {
            View view = layoutInflater.inflate(R.layout.movie_reviewinfo_holder, parent, false);
            viewHolder = new ReviewsViewHolder(view);
            a = 0;
            return viewHolder;
        }
        return null;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case 0:
//                ((MovieDetailViewHolder) holder).movieName.setText(movie.getTitle());
                if (!movie.getTagLine().equals("")) {
                    ((MovieDetailViewHolder) holder).movieTagLine.setText("\" " + movie.getTagLine() + " \"");
                } else if (movie.getTagLine().equals("")) {
                    ((MovieDetailViewHolder) holder).movieTagLine.setVisibility(View.GONE);
                }
                ((MovieDetailViewHolder) holder).movieReleaseDate.setText(movie.getReleasedate());//+ "(" + movie.getStatus() + ")");

                String durationInMin = movie.getDuration();

                ((MovieDetailViewHolder) holder).movieDuration.setText(durationInMin);

                ((MovieDetailViewHolder) holder).movieGenre.setText("Genre: " + movie.getGenre());
                //  ((MovieDetailViewHolder) holder).movieLanguage.setText(String.format("Language: " + movie.getLanguage()));
                ((MovieDetailViewHolder) holder).moviePopularity.setText(String.format("%.1f", movie.getPopularity()) + "");
                ((MovieDetailViewHolder) holder).movieRating.setText(String.format((movie.getAudienceScore()) + ""));
                ((MovieDetailViewHolder) holder).movieSynopsis.setText(movie.getOverview());

                break;
            case 1:


                final String[] data = trailerInfo.get(position - 1).split(",");

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
break;
            case 2:
                final String[] ReviewData =  reviewsInfo.get(position - 1).split(",");
                ((ReviewsViewHolder) holder).reviwerName.setText(ReviewData[0]);
                ((ReviewsViewHolder) holder).reviwerDesc.setText(ReviewData[1]);

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        if (position > 0 && a == 1)
            return 2;
        else
            return 1;
    }

    class MovieDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView movieName, movieTagLine, movieReleaseDate, movieDuration, movieGenre, moviePopularity, movieSynopsis, movieRating, movieLanguage;
        public ImageView movieImage;


        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            // context = itemView.getContext();
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            movieName = (TextView) itemView.findViewById(R.id.tvMovieTitle);
            movieTagLine = (TextView) itemView.findViewById(R.id.tvMovieTagLine);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.tvMovieReleaseDate);
            movieDuration = (TextView) itemView.findViewById(R.id.tvMovieDuration);
            movieGenre = (TextView) itemView.findViewById(R.id.tvMovieGenre);
            moviePopularity = (TextView) itemView.findViewById(R.id.tvMoviePopularity);
            movieRating = (TextView) itemView.findViewById(R.id.tvMovieRating);
            movieSynopsis = (TextView) itemView.findViewById(R.id.tvMovieSynopsis);
            movieLanguage = (TextView) itemView.findViewById(R.id.tvMovieLanguage);
            // movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
        }


        public String getTitle(String title) {
            return title;
        }

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


    class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private TextView reviwerName, reviwerDesc;


        public ReviewsViewHolder(View itemView) {
            super(itemView);


            reviwerName = (TextView) itemView.findViewById(R.id.tvReviewName);
            reviwerDesc = (TextView) itemView.findViewById(R.id.tvReviewDesc);

        }
    }

}