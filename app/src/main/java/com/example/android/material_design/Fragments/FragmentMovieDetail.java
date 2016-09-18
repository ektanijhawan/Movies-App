package com.example.android.material_design.Fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.example.android.material_design.Adapters.MovieDetailAdapter;
import com.example.android.material_design.Adapters.MovieDetailAdapter2;
import com.example.android.material_design.Adapters.MovieDetailAdapter3;
import com.example.android.material_design.Database.DataSource;
import com.example.android.material_design.Database.DbHelper;
import com.example.android.material_design.Model.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.Utility.MovieProvider;
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
   private static final String DATABASE_NAME = "movies.db";

    public static final  String ID="movieId";
    public static final String  TITLE  ="title";
    public static final String URLSELF ="urlSelf";
    public static final String COVERIMAGE="coverimage";
    public static final String AUDIENCESCORE ="audienceScore";
    public static final String POPULARITY="popularity";
    public static final String   RELEASEDATE=   "releaseDateTheater";
    public static final String OVERVIEW = "overview";
    public static final String TAGLINE ="tagline";
    public static final String DURATION="duration";
    public static final String GENRE="genre";

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
String urlSelf;
    //TextView title;
    TextView tvPopularity;
    TextView tvVote_average;
    String data;
   // TextView tvGenre;
    //ImageView image;
    TextView tvDuration;
    String imagePostUrl="";
    ImageView itemIcon2;
    String movieID;
    String fragmentValue;
ImageView image;
    String imageString;
    String genres = "";
    String tagline="";

    String vote_average="";
    int hours=0;
    public MaterialTabHost tabHost;
    String favtitle, favurlSelf, favcoverImage, favaudienceScore, favpopularity,favtagLine, favreleaseDate,favduration, favgenre, favoverview;
    public ViewPager viewPager;
    String coverImage;
    Boolean inDatabase=false;
    String releaseDate,overview;
    String titleStr="";
    int minutes=0;
    SubActionButton button3;
//    TextView movieName;
    private TextView movieName, movieTagLine, movieReleaseDate, movieDuration, movieGenre, moviePopularity, movieSynopsis, movieRating, movieLanguage;
    String popularity="";
    ImageView itemIcon3;
    Movie movieInfo;
    ImageView itemIcon1;
    String DurationString;
    String movieVideosInfo;
    RecyclerView mRecyclerView;
    FloatingActionMenu actionMenu; private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
     Activity activity;
    ImageView movieImage;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ImageView back;
    String dataShare;
    ImageView trailers,reviews;
//    ThumbUpView mThumbUpView;
    private ArrayList<String> mTrailerInfo = new ArrayList<>();
    private ArrayList<String> mReviewInfo = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
//movieName= (TextView) view.findViewById(R.id.movieName);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_movie_detail);
        mLayoutManager = new LinearLayoutManager(activity);

        mRecyclerView.setLayoutManager(mLayoutManager);
      //  recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView_movie_detail2);

        mLayoutManager = new LinearLayoutManager(activity);

      //  recyclerView.setLayoutManager(mLayoutManager);
        //    fragmentManager = getSupportFragmentManager();//
movie= new Movie();
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
        movieImage = (ImageView) view.findViewById(R.id.ivMovieImage);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        back = (ImageView) view.findViewById(R.id.ivBack);
     //  trailers= (ImageView) view.findViewById(R.id.ivTrailer);
      //  reviews= (ImageView) view.findViewById(R.id.ivReviews);
        imageLoader = volleySingleton.getmImageLoader();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        movieID = getArguments().getString("stringId");
        urlSelf=getArguments().getString("urlSelf");
        fragmentValue = getArguments().getString("fragment");
if(fragmentValue.equals("favourite"))
{fab.setAlpha(0);

favtitle=getArguments().getString("title");
  favcoverImage=getArguments().getString("coverImage");
          favaudienceScore=getArguments().getString("audienceScore");
                  favpopularity=getArguments().getString("popularity");
                          favtagLine=getArguments().getString("tagLine");
                                  favreleaseDate=getArguments().getString("releaseDate");
                                          favduration=getArguments().getString("duration");
                                                  favgenre=getArguments().getString("genre");
                                                          favoverview=getArguments().getString("overview");


}
        else
fab.setAlpha(1);
//mThumbUpView= (ThumbUpView) view.findViewById(R.id.tpv);
        ImageView icon = new ImageView(getActivity());
        icon.setImageResource(R.drawable.next);

     //   FloatingActionButton actionButton = new FloatingActionButton.Builder(getActivity())
       //         .setContentView(icon)
         //       .build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
        // repeat many times:
         itemIcon1 = new ImageView(getActivity());
        if(!inDatabase)
        itemIcon1.setImageResource(R.drawable.like_hollow_heart);
        else
            itemIcon1.setImageResource(R.drawable.like_filled_heart);

         itemIcon2 = new ImageView(getActivity());
        itemIcon2.setImageResource(R.drawable.share);

         itemIcon3 = new ImageView(getActivity());
        itemIcon3.setImageResource(R.mipmap.ic_launcher);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
         //button3 = itemBuilder.setContentView(itemIcon3).build();

        //attach the sub buttons to the main button
        actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                .addSubActionView(button2)
              //  .addSubActionView(button3)
                .attachTo(fab)
                .build();

        //sendjsonRequest(movieID);

    /*    trailers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!fragmentValue.equals("favourite"))
                {    mRecyclerView.clearFocus();
                    sendjsonRequestVideos(movieID,"notShare");}

            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!fragmentValue.equals("favourite"))
                {    mRecyclerView.removeAllViews();
                    mRecyclerView.clearFocus();
                    sendJsonRequestReviews(movieID);}
            }
        });



        //sendjsonRequestVideos(movieID);
        mThumbUpView.setUnLikeType(ThumbUpView.LikeType.broken);
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
       // movieLanguage = (TextView) view.findViewById(R.id.tvMovieLanguage);


       if(fragmentValue.equals("favourite")){
//            movieName.setText(favtitle);

           int b= inDatabase(movieID);
  Toast.makeText(getActivity(),favcoverImage,Toast.LENGTH_SHORT).show();
           movie.setStringid(movieID);
           movie.setTitle(favtitle);
           movie.setTagLine(favtagLine);
           movie.setAudienceScore(favaudienceScore);
           movie.setPopularity(popularity);
           movie.setReleasedate(favreleaseDate);
           movie.setDuration(favduration);
           movie.setGenre(favgenre);
           movie.setOverview(favoverview);
           int a=  imageRequest(favcoverImage);
           mAdapter = new MovieDetailAdapter(movie,mTrailerInfo,mReviewInfo,getActivity());
           mRecyclerView.setAdapter(mAdapter);
           mAdapter.notifyDataSetChanged();
           /*movieTagLine.setText(favtagLine);
            movieReleaseDate.setText(favreleaseDate);
            movieDuration.setText(favduration);
            movieGenre.setText(favgenre);
            moviePopularity.setText(favpopularity);
            movieRating.setText(favaudienceScore);
            movieSynopsis.setText(favoverview);
        */


        }
        else if((fragmentValue.equals("popular"))||(fragmentValue.equals("toprated")))

            sendjsonRequest(movieID);


        dbHelper = new DbHelper(getActivity());
        if((dbHelper.isInDatabase(Integer.parseInt(movieID))))
            itemIcon1.setImageResource(R.drawable.like_filled_heart);
        else
            itemIcon1.setImageResource(R.drawable.like_hollow_heart);


   inDatabase(movieID);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }


int imageRequest(String imageString)
{


    if (imageString != null) {

        imageLoader.get(imageString, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                movieImage.setImageBitmap(null);
                movieImage.setImageBitmap(response.getBitmap());
            }
        });

    }
    return 1;
}

    public void sendjsonRequest(final String id)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/"+id+"?api_key=74a8c711917fabf892c994dc63136a80"

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                movieInfo = parseJsonResponse(response);
inDatabase(id);

                sendjsonRequestVideos(movieID);
//                mAdapter = new MovieDetailAdapter(movieInfo,getActivity());

  //              mRecyclerView.setAdapter(mAdapter);



           //     mAdapter.notifyDataSetChanged();

                //adapterBoxOffice.setMovieList(listMovies);
                //  Toast.makeText(this ,response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
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

                final int durationInMin = duration;

                popularity=response.getString("popularity");

                vote_average=response.getString("vote_average");
                hours = durationInMin / 60;
                minutes = durationInMin % 60;
                DurationString=   "Duration: " + hours + " hr " + minutes + " min";


                 imageString=coverImage+imagePostUrl;

                final JSONArray genreArray = response.getJSONArray("genres");
                for (int i = 0; i < genreArray.length(); i++) {
                    String genre = genreArray.getJSONObject(i).getString("name");
                    if (i != genreArray.length() - 1)
                        genres += genre + ", ";
                    else
                        genres += genre + ".";
                }
int a=imageRequest(imageString);
          /*      if (imageString != null) {

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
                */
//                movieName.setText(titleStr);
                movie.setStringid(movieID);
                movie.setTitle(titleStr);
                movie.setCoverImage(imageString);
                movie.setUrlSelf(urlSelf);
                movie.setTagLine(tagline);
                movie.setAudienceScore(vote_average);
                movie.setPopularity(popularity);
                movie.setReleasedate(releaseDate);
                movie.setDuration(DurationString);
                movie.setGenre(genres);
                movie.setOverview(overview);


                // movie.setImage(image);


                     //tvGenre.setText(genres);

/*
                if (!movie.getTagLine().equals("")) {
                    movieTagLine.setText("\" " + movie.getTagLine() + " \"");
                } else if (movie.getTagLine().equals("")) {
                    movieTagLine.setVisibility(View.GONE);
                }
                movieReleaseDate.setText(movie.getReleasedate());//+ "(" + movie.getStatus() + ")");

             //   int durationInMin = movie.getDuration();
               // int hours = durationInMin / 60;
                movieDuration.setText(DurationString);

                movieGenre.setText("Genre: " + movie.getGenre());
                //  ((MovieDetailViewHolder) holder).movieLanguage.setText(String.format("Language: " + movie.getLanguage()));
                moviePopularity.setText( movie.getPopularity() );
                movieRating.setText(String.format((movie.getAudienceScore()) + ""));
                movieSynopsis.setText(movie.getOverview());
                //movie.setLanguage(language);
//inDatabase(movieID);
*/



                itemIcon2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                       createShareIntent();


                    }
                });

            } catch (JSONException e){}



        }







 // Toast.makeText(getActivity(),movie.toString(),Toast.LENGTH_LONG).show();

        return movie;
    }


    public int inDatabase(final String movieID)
    {
          final String id=movieID;
        dbHelper = new DbHelper(getActivity());
        if((dbHelper.isInDatabase(Integer.parseInt(movieID))))
            itemIcon1.setImageResource(R.drawable.like_filled_heart);
        else
            itemIcon1.setImageResource(R.drawable.like_hollow_heart);


        itemIcon1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(!(dbHelper.isInDatabase(Integer.parseInt(id))))
                {
                    inDatabase = true;
                    itemIcon1.setImageResource(R.drawable.like_filled_heart);
                 //   dbHelper.insertInDatabase(Integer.parseInt(id), titleStr, urlSelf, imageString, vote_average, popularity, tagline, releaseDateString, DurationString, genres, overview);

Log.v("hi","hi");
                   // Boolean isInDb = dbHelper.isInDatabase(Integer.parseInt(movieID));
                    //if (!isInDb) {

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ID, Integer.parseInt(id));
                        contentValues.put(TITLE, titleStr);
                        contentValues.put(URLSELF, urlSelf);
                        contentValues.put(COVERIMAGE, imageString);
                        contentValues.put(AUDIENCESCORE, vote_average);
                        contentValues.put(POPULARITY, popularity);
                        contentValues.put(TAGLINE, tagline);
                        contentValues.put(RELEASEDATE, releaseDateString);
                        contentValues.put(DURATION, DurationString);
                        contentValues.put(GENRE, genres);
                        contentValues.put(OVERVIEW, overview);


                        //db.insert(TABLE_MOVIES, null, contentValues);
                        activity.getContentResolver().insert(MovieProvider.CONTENT_URI, contentValues);

                        Log.d("database", "inserted");
                    Toast.makeText(getActivity(),"\"Liked\"",Toast.LENGTH_SHORT).show();

                    //}


                }
                else if((dbHelper.isInDatabase(Integer.parseInt(id)))){
                    inDatabase = false;
                    itemIcon1.setImageResource(R.drawable.like_hollow_heart);
  //                  Uri contentUri = MovieProvider.CONTENT_URI;
//                    activity.getContentResolver().delete(contentUri, "id=?", new String[]{id});

                    Uri contentUri = MovieProvider.CONTENT_URI;
                    activity.getContentResolver().delete(contentUri, dbHelper.ID+"=?", new String[]{id});
                    Toast.makeText(getActivity(),"\"Unliked\"",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return 1;
    }

    public void sendjsonRequestVideos(String id)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/"+id+"/videos?api_key=74a8c711917fabf892c994dc63136a80"

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                mTrailerInfo = parseJsonResponseVidedos(response);
             //   recyclerView.setAlpha(0);
               // mRecyclerView.setAlpha(1);
              //  if(shareOrNot.equals("notShare")) {
                //    mTrailerInfo = parseJsonResponseVidedos(response,"notShare");
                    sendJsonRequestReviews(movieID);
                 //   mAdapter = new MovieDetailAdapter2(mTrailerInfo, getActivity());
                   // mRecyclerView.setAdapter(mAdapter);
                    //Toast.makeText(getActivity(),"the toast", Toast.LENGTH_SHORT).show();
                    //mAdapter.notifyDataSetChanged();
                }
                //adapterBoxOffice.setMovieList(listMovies);
                //  Toast.makeText(this ,response.toString() + " ",Toast.LENGTH_SHORT).show();
            //}
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

    dataShare = "http://www.youtube.com/watch?v=" + mTrailerObject.getString("key");


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
            //    recyclerView.setAlpha(0);
           //     mRecyclerView.setAlpha(1);

                mAdapter = new MovieDetailAdapter(movie,mTrailerInfo,mReviewInfo,getActivity());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

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
                JSONObject mReviewObject = mReviewResultArray.getJSONObject(i);
                mReviewInfo.add(mReviewObject.getString("author") + "," + mReviewObject.getString("content"));


            }
        }
        catch (JSONException e)
        {}

        return mReviewInfo;
    }
  /*  protected void sendEmail() {
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
*/
    private  void createShareIntent(){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, dataShare);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

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


        String[] tabText=getResources().getStringArray(R.array.tabs);
        public MyPagerAdapter(FragmentManager fm) {

            super(fm);
            tabText= getResources().getStringArray(R.array.tabs);

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
