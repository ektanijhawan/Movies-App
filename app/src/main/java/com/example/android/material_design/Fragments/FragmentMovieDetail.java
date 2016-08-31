package com.example.android.material_design.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.material_design.Adapters.MovieDetailAdapter;
import com.example.android.material_design.Adapters.MovieDetailAdapter2;
import com.example.android.material_design.Adapters.MovieDetailAdapter3;
import com.example.android.material_design.Database.DataSource;
import com.example.android.material_design.Database.DbHelper;
import com.example.android.material_design.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
//import com.ldoublem.thumbUplib.ThumbUpView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieDetail extends Fragment implements MaterialTabListener {

    public ImageLoader imageLoader;
    public VolleySingleton volleySingleton;
   // String imageString;
    String share;
    String titleString;
    String releaseDateString;
    String overviewString;
    private Movie movie;
    private ArrayList<String> trailerInfo = new ArrayList<>();
    private ArrayList<String> reviewInfo = new ArrayList<>();
    int id;
    public RequestQueue requestQueue;
    private android.support.v7.widget.ShareActionProvider mShareActionProvider;
    Button button;
    DbHelper dbHelper;
    Boolean favourite;
    TextView tvReleaseDate;
    int duration=0;
    TextView tvOverview;
    public ArrayList<Movie> listMovies = new ArrayList<>();
String movieCoverImage;
    boolean flag;
    DataSource dataSource = new DataSource(getActivity());
    private static android.support.v4.app.FragmentManager fragmentManager;

    //TextView title;
    TextView tvPopularity;
    TextView tvVote_average;
   // TextView tvGenre;
    //ImageView image;
    TextView tvDuration;
    String imagePostUrl="";
    String coverImage="";
    String movieID;
    int fragmentValue;
ImageView image;
    String imageString;
    String genres = "";
    String tagline="";
    String releaseDate="";
    String overview="";
    String vote_average="";
    int hours=0;
    public MaterialTabHost tabHost;
    public ViewPager viewPager;
    String titleStr="";
    int minutes=0;
//    TextView movieName;
    private TextView movieName, movieTagLine, movieReleaseDate, movieDuration, movieGenre, moviePopularity, movieSynopsis, movieRating, movieLanguage;
    String popularity="";
    Movie movieInfo;
    String movieVideosInfo;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Activity activity;
    ImageView movieImage;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ImageView back;
//    ThumbUpView mThumbUpView;
    private ArrayList<String> mTrailerInfo = new ArrayList<>();
    private ArrayList<String> mReviewInfo = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
movieName= (TextView) view.findViewById(R.id.movieName);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_movie_detail);
        mLayoutManager = new LinearLayoutManager(activity);

        mRecyclerView.setLayoutManager(mLayoutManager);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView_movie_detail2);

        mLayoutManager = new LinearLayoutManager(activity);

        recyclerView.setLayoutManager(mLayoutManager);
        //    fragmentManager = getSupportFragmentManager();//
movie= new Movie();
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        movieImage = (ImageView) view.findViewById(R.id.ivMovieImage);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        back = (ImageView) view.findViewById(R.id.ivBack);
        imageLoader = volleySingleton.getmImageLoader();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        movieID = getArguments().getString("stringId");
     //   fragmentValue = getArguments().getInt("fragmentId");

//mThumbUpView= (ThumbUpView) view.findViewById(R.id.tpv);
        ImageView icon = new ImageView(getActivity());
        icon.setImageResource(R.mipmap.ic_launcher);

     //   FloatingActionButton actionButton = new FloatingActionButton.Builder(getActivity())
       //         .setContentView(icon)
         //       .build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
        // repeat many times:
        ImageView itemIcon1 = new ImageView(getActivity());
        itemIcon1.setImageResource(R.mipmap.ic_launcher);

        ImageView itemIcon2 = new ImageView(getActivity());
        itemIcon2.setImageResource(R.mipmap.ic_launcher);

        ImageView itemIcon3 = new ImageView(getActivity());
        itemIcon3.setImageResource(R.mipmap.ic_launcher);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        //attach the sub buttons to the main button
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(fab)
                .build();

        //sendjsonRequest(movieID);

        itemIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              sendjsonRequestVideos(movieID);

            }
        });
        itemIcon2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendJsonRequestReviews(movieID);
            }
        });
        itemIcon3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
            }
        });


        //sendjsonRequestVideos(movieID);
     /*   mThumbUpView.setUnLikeType(ThumbUpView.LikeType.broken);
        mThumbUpView.setCracksColor(Color.rgb(22, 33, 44));
        mThumbUpView.setFillColor(Color.rgb(11, 200, 77));
        mThumbUpView.setEdgeColor(Color.rgb(33, 3, 219));
        mThumbUpView.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
            }
        });
        mThumbUpView.Like();
        mThumbUpView.UnLike();
        */
        //movieImage = (ImageView) view.findViewById(R.id.movieImage);
        movieName = (TextView) view.findViewById(R.id.tvMovieTitle);
        movieTagLine = (TextView) view.findViewById(R.id.tvMovieTagLine);
        movieReleaseDate = (TextView)view.findViewById(R.id.tvMovieReleaseDate);
        movieDuration = (TextView) view.findViewById(R.id.tvMovieDuration);
        movieGenre = (TextView) view.findViewById(R.id.tvMovieGenre);
        moviePopularity = (TextView) view.findViewById(R.id.tvMoviePopularity);
        movieRating = (TextView) view.findViewById(R.id.tvMovieRating);
        movieSynopsis = (TextView) view.findViewById(R.id.tvMovieSynopsis);
        movieLanguage = (TextView) view.findViewById(R.id.tvMovieLanguage);
        sendjsonRequest(movieID);
        return view;
    }


    public void sendjsonRequest(String id)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/"+id+"?api_key=74a8c711917fabf892c994dc63136a80"

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                movieInfo = parseJsonResponse(response);

//                mAdapter = new MovieDetailAdapter(movieInfo,getActivity());

  //              mRecyclerView.setAdapter(mAdapter);


             /*   FragmentTransaction t = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                FragmentMovieOverview mFrag = new FragmentMovieOverview();
                mFrag.setArguments(data);
                t.replace(R.id.viewpager, mFrag);
                Toast.makeText(getActivity() ,"hhhhhhhhhh ",Toast.LENGTH_SHORT).show();
                t.commit();
                */

           //     mAdapter.notifyDataSetChanged();

                //adapterBoxOffice.setMovieList(listMovies);
                //  Toast.makeText(this ,response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getActivity(),"error" + " ",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request)    ;


    }

    public Movie parseJsonResponse(JSONObject response) {

        if (response == null || response.length() == 0)
            return null;
        if(response != null && response.length() > 0){
            try {


                titleStr = response.getString("original_title");
                 imagePostUrl=response.getString("backdrop_path");
                 coverImage="http://image.tmdb.org/t/p/w780/";



               tagline=response.getString("tagline");

                 duration= Integer.parseInt(response.getString("runtime"));
                 releaseDate= response.getString("release_date");

                overview= response.getString("overview");

                int durationInMin = duration;

                popularity=response.getString("popularity");

                vote_average=response.getString("vote_average");
                hours = durationInMin / 60;
                minutes = durationInMin % 60;



                 imageString=coverImage+imagePostUrl;

                JSONArray genreArray = response.getJSONArray("genres");
                for (int i = 0; i < genreArray.length(); i++) {
                    String genre = genreArray.getJSONObject(i).getString("name");
                    if (i != genreArray.length() - 1)
                        genres += genre + ", ";
                    else
                        genres += genre + ".";
                }

                if (imageString != null) {

                    imageLoader.get(imageString, new ImageLoader.ImageListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            movieImage.setImageBitmap(response.getBitmap());
                        }
                    });

                }
movieName.setText(titleStr);
                movie.setId(id);
                movie.setTitle(titleStr);
                // movie.setTagLine(tagLine);
                movie.setReleasedate(releaseDate);
                movie.setDuration(duration);
               // movie.setGenre(genres);
                //  movie.setStatus(status);
                movie.setOverview(overview);
                movie.setCoverImage(imageString);
                movie.setTagLine(tagline);
                // movie.setImage(image);
                movie.setDuration(duration);
                movie.setPopularity(Float.parseFloat(popularity));
                movie.setGenre(genres);       //tvGenre.setText(genres);

                movie.setRating(Float.parseFloat(vote_average));
                if (!movie.getTagLine().equals("")) {
                    movieTagLine.setText("\" " + movie.getTagLine() + " \"");
                } else if (movie.getTagLine().equals("")) {
                    movieTagLine.setVisibility(View.GONE);
                }
                movieReleaseDate.setText(movie.getReleasedate());//+ "(" + movie.getStatus() + ")");

             //   int durationInMin = movie.getDuration();
               // int hours = durationInMin / 60;
                movieDuration.setText("Duration: " + hours + " hr " + minutes + " min");

                movieGenre.setText("Genre: " + movie.getGenre());
                //  ((MovieDetailViewHolder) holder).movieLanguage.setText(String.format("Language: " + movie.getLanguage()));
                moviePopularity.setText(String.format("%.1f", movie.getPopularity()) + "");
                movieRating.setText(String.format((movie.getRating()) + ""));
                movieSynopsis.setText(movie.getOverview());
                //movie.setLanguage(language);
            } catch (JSONException e){}

        /*    title.setText(titleStr);
            tvReleaseDate.setText(releaseDate);
            tvOverview.setText(overview);
            tvPopularity.setText(popularity);

            tvVote_average.setText(vote_average);

            tvDuration.setText("Duration: " + hours + " hr " + minutes + " min");
*/


        }



 // Toast.makeText(getActivity(),movie.toString(),Toast.LENGTH_LONG).show();

        return movie;
    }


    public void sendjsonRequestVideos(String id)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/"+id+"/videos?api_key=74a8c711917fabf892c994dc63136a80"

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                mTrailerInfo = parseJsonResponseVidedos(response);
                recyclerView.setAlpha(0);
                mRecyclerView.setAlpha(1);
              mAdapter = new MovieDetailAdapter2(mTrailerInfo,getActivity());
                mRecyclerView.setAdapter(mAdapter);
                //Toast.makeText(getActivity(),"the toast", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();

                //adapterBoxOffice.setMovieList(listMovies);
                //  Toast.makeText(this ,response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getActivity(),"error" + " ",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request)    ;


    }

    public ArrayList<String> parseJsonResponseVidedos(JSONObject response)
    {

        try {
            JSONArray mTrailerResultArray = response.getJSONArray("results");
            for (int i = 0; i < mTrailerResultArray.length(); i++) {
                JSONObject mTrailerObject = mTrailerResultArray.getJSONObject(i);
                mTrailerInfo.add(mTrailerObject.getString("key") + "," + mTrailerObject.getString("name")
                        + "," + mTrailerObject.getString("site") + "," + mTrailerObject.getString("size")
                        + "," + mTrailerObject.getString("type"));
            }


        }
        catch (JSONException e)
        {}

           return mTrailerInfo;
    }


    public void sendJsonRequestReviews(String id)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=74a8c711917fabf892c994dc63136a80"

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                mReviewInfo = parseJsonResponseReviews(response);
mRecyclerView.setAlpha(0);
                recyclerView.setAlpha(1);
              mAdapter = new MovieDetailAdapter3(mReviewInfo,getActivity());
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

                //adapterBoxOffice.setMovieList(listMovies);
                //  Toast.makeText(this ,response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getActivity(),"error" + " ",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request)    ;


    }

    public ArrayList<String> parseJsonResponseReviews(JSONObject response)
    {

        try {
            JSONArray mReviewResultArray = response.getJSONArray("results");
            for (int i = 0; i < mReviewResultArray.length(); i++) {
                JSONObject mTrailerObject = mReviewResultArray.getJSONObject(i);
                mReviewInfo.add(mTrailerObject.getString("author") + "," + mTrailerObject.getString("content"));


            }
        }
        catch (JSONException e)
        {}

        return mTrailerInfo;
    }
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, share);

        // try {
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        //finish();
        //    Log.i("Finished sending email...", "");
        // }
        // catch (android.content.ActivityNotFoundException ex) {
        //     Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        // }
    }

    private  Intent createShareIntent(){

        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, share);
        return shareIntent;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_fragment,menu);


        MenuItem menuItem= (MenuItem)menu.findItem(R.id.share);
        mShareActionProvider= (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if(mShareActionProvider!=null){
            mShareActionProvider.setShareIntent(createShareIntent());
        }
        return true;
    }
    */


    class MyPagerAdapter extends FragmentStatePagerAdapter {



        //int[] icons={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        String[] tabText=getResources().getStringArray(R.array.tabs);
        public MyPagerAdapter(FragmentManager fm) {

            super(fm);
            tabText= getResources().getStringArray(R.array.tabs);
            //Fragment   fragment=FragmentSearch.newInstance("", "");
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment= null;

            switch(position){

                case 0 :

                    //  fragment=FragmentPopular.newInstance("","");
                    fragment= FragmentMovieOverview.newInstance("","");

                    break;
                case 1 :
                    fragment= FragmentMovieOverview.newInstance("","");

                    break;
                case 2 :


                    fragment= FragmentMovieOverview.newInstance("","");
                    break;


            }

            return fragment;
            //return null;
        }



        public CharSequence getPageTitle(int position)
        {
            return tabText[position];

        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}
