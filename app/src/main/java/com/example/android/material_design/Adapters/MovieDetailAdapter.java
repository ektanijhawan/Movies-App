package com.example.android.material_design.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.android.material_design.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Ekta on 29-08-2016.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailViewHolder>   {



    private Movie movie;
    private ArrayList<String> trailerInfo = new ArrayList<>();
    private ArrayList<String> reviewsInfo = new ArrayList<>();
    private LayoutInflater layoutInflater;
    android.content.Context contextt;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private String dataZero;

    public MovieDetailAdapter(Movie movie,Context context){
        contextt=context;
      layoutInflater = LayoutInflater.from(contextt);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
       // this.context = context;
        this.movie = movie;
        //this.trailerInfo = trailerInfo;
        //this.movieReviews = movieReviews;
    }

    @Override
    public MovieDetailAdapter.MovieDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieDetailViewHolder viewHolder;

            View view = layoutInflater.inflate(R.layout.movie_detail_holder, parent, false);
            viewHolder = new MovieDetailViewHolder(view);
            return viewHolder;



    }

    @Override
    public void onBindViewHolder(MovieDetailAdapter.MovieDetailViewHolder holder, int position) {

        ((MovieDetailViewHolder) holder).movieName.setText(movie.getTitle());
     /*  if(!movie.getTagLine().equals("")) {
            ((MovieDetailViewHolder) holder).movieTagLine.setText("\" " + movie.getTagLine() + " \"");
        }else if(movie.getTagLine().equals("")) {
            ((MovieDetailViewHolder) holder).movieTagLine.setVisibility(View.GONE);
        }
        ((MovieDetailViewHolder) holder).movieReleaseDate.setText(movie.getReleasedate());//+ "(" + movie.getStatus() + ")");

        int durationInMin = movie.getDuration();
        int hours = durationInMin / 60;
        int minutes = durationInMin % 60;
        ((MovieDetailViewHolder) holder).movieDuration.setText("Duration: " + hours + " hr " + minutes + " min");

        ((MovieDetailViewHolder) holder).movieGenre.setText("Genre: " + movie.getGenre());
      //  ((MovieDetailViewHolder) holder).movieLanguage.setText(String.format("Language: " + movie.getLanguage()));
//        ((MovieDetailViewHolder) holder).moviePopularity.setText(String.format("%.1f", movie.getPopularity()) + "");
        //((MovieDetailViewHolder) holder).movieRating.setText(String.format((movie.getRating()) + ""));
        ((MovieDetailViewHolder) holder).movieSynopsis.setText(movie.getOverview());

*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public  class MovieDetailViewHolder extends RecyclerView.ViewHolder{
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
           // moviePopularity = (TextView) itemView.findViewById(R.id.tvMoviePopularity);
            //movieRating = (TextView) itemView.findViewById(R.id.tvMovieRating);
            movieSynopsis = (TextView) itemView.findViewById(R.id.tvMovieSynopsis);
            movieLanguage = (TextView) itemView.findViewById(R.id.tvMovieLanguage);
            // movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
        }


        public String getTitle(String title){
            return title;
        }

    }



    }



