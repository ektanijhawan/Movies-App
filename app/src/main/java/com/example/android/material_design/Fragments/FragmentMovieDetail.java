package com.example.android.material_design.Fragments;


import android.app.Activity;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.material_design.Adapters.MovieDetailAdapter;
import com.example.android.material_design.Database.DataSource;
import com.example.android.material_design.Database.DbHelper;
import com.example.android.material_design.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

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
    String popularity="";
    Movie movieInfo;
    String movieVideosInfo;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Activity activity;
    ImageView movieImage;
    FloatingActionButton fab;
    ImageView back;
    private ArrayList<String> mTrailerInfo = new ArrayList<>();
    private ArrayList<String> mReviewInfo = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_movie_detail);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
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




        sendjsonRequest(movieID);
        //sendjsonRequestVideos(movieID);

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

                mAdapter = new MovieDetailAdapter(movieInfo,getActivity());

                mRecyclerView.setAdapter(mAdapter);
                Bundle data = new Bundle();
                data.putString("title","titleStr");

             /*   FragmentTransaction t = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                FragmentMovieOverview mFrag = new FragmentMovieOverview();
                mFrag.setArguments(data);
                t.replace(R.id.viewpager, mFrag);
                Toast.makeText(getActivity() ,"hhhhhhhhhh ",Toast.LENGTH_SHORT).show();
                t.commit();
                */

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


            } catch (JSONException e){}

        /*    title.setText(titleStr);
            tvReleaseDate.setText(releaseDate);
            tvOverview.setText(overview);
            tvPopularity.setText(popularity);

            tvVote_average.setText(vote_average);

            tvDuration.setText("Duration: " + hours + " hr " + minutes + " min");
*/

           // movie.setRating(rating);
            //movie.setLanguage(language);
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

                //mAdapter = new MovieDetailAdapter(movieInfo,getActivity());
                //mRecyclerView.setAdapter(mAdapter);
                //mAdapter.notifyDataSetChanged();

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
                mTrailerInfo.add(mTrailerObject.getString("key") + ",," + mTrailerObject.getString("name")
                        + ",," + mTrailerObject.getString("site") + ",," + mTrailerObject.getString("size")
                        + ",," + mTrailerObject.getString("type"));
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
                mReviewInfo = parseJsonResponseVidedos(response);

                //mAdapter = new MovieDetailAdapter(movieInfo,getActivity());
                //mRecyclerView.setAdapter(mAdapter);
                //mAdapter.notifyDataSetChanged();

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

        //  @Override
        //  public CharSequence getPageTitle(int position) {
////
        //  Drawable drawable= getResources().getDrawable(icons[position]);
        //  drawable.setBounds(0,0,36,36);
        //  ImageSpan imageSpan=new ImageSpan(drawable);
        //  SpannableString spannableString=new SpannableString(" ");
        //  spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //    return tabText[position];
        // }

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
