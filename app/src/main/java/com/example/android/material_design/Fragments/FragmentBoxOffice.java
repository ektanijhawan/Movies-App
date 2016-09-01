package com.example.android.material_design.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.material_design.Adapters.AdapterBoxOffice;
import com.example.android.material_design.Activities.DeatilBoxOfficeActivity;
import com.example.android.material_design.EndlessRecyclerOnScrollListener;
import com.example.android.material_design.Movie;
import com.example.android.material_design.R;
import com.example.android.material_design.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoxOffice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoxOffice extends Fragment   implements AdapterBoxOffice.ClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   // public static final String  Url_BoxOFFICE="http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=b5pvu9qagkgqxry66hq6rzcd";
    public VolleySingleton volleySingleton;
    public ImageLoader imageLoader;
    public RequestQueue requestQueue;
    private Activity activity;
   public String title;
    Movie movie;
    public  String release_date;
    // TODO: Rename and change types of parameters
    int pageCount=1;  int pages;    private String mParam1;
    private String mParam2;
  public String image_preUrl="http://image.tmdb.org/t/p/w342/";
private RecyclerView listMoviesHits;
    String image_whole_Url;
    public  String overview;
    String image_post_URL;
    String sId;
    public ArrayList<Movie> listMovies = new ArrayList<>();
public AdapterBoxOffice adapterBoxOffice;


    public FragmentBoxOffice() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentBoxOffice newInstance(String param1, String param2) {
        FragmentBoxOffice fragment = new FragmentBoxOffice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        volleySingleton=VolleySingleton.getInstance();
        requestQueue= volleySingleton.getmRequestQueue();
    }


    public void sendJsonRequest(int pageCount)
    {

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/top_rated?api_key=74a8c711917fabf892c994dc63136a80"+pageCount

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
          listMovies = parseJsonResponse(response);
                listMovies = parseJsonResponse(response);
                adapterBoxOffice.setMovieList(listMovies);
             //Toast.makeText(getActivity(),response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(getActivity(),"error" + " ",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request)    ;




    }

    public ArrayList<Movie> parseJsonResponse(JSONObject response) {

       //ArrayList<Movie> listMovies= new ArrayList<>();

        {
            if (response == null || response.length() == 0)
                return null;
        }



    try {
        pageCount= response.getInt("page");
        pages= response.getInt("total_pages");

        StringBuilder data = new StringBuilder();
        if (response.has("results")) {
            JSONArray arrayMovieResults = response.getJSONArray("results");
            for (int i1 = 0; i1<arrayMovieResults.length(); i1++) {

                JSONObject currentMovie = arrayMovieResults.getJSONObject(i1);
                //String id=currentMovie.getString("id");

                 sId=currentMovie.getString("id");
                int id=currentMovie.getInt("id");
                //title
                title = currentMovie.getString("title");

                //language
                String lang = currentMovie.getString("original_language");
                String lang_final;
                if (lang.equals("en"))
                    lang_final = "English";
                else lang_final = "Not English";

                //oveview
                overview = currentMovie.getString("overview");

                //release date
                release_date = currentMovie.getString("release_date");

                //imageUrl
                image_post_URL = currentMovie.getString("poster_path");
                image_whole_Url = image_preUrl + image_post_URL;
                //vote

                String vote_average = currentMovie.getString("vote_average");

                //adult
                String adult = currentMovie.getString("adult");
                String isAdult;
                if (adult.equals("false")) {
                    isAdult = "No";
                } else
                    isAdult = "Yes";


                //appending all the movies which we are getiing one by one
                Movie movie = new Movie();
               /* movie.setStringid(sId);
                movie.setId(id);
                movie.setTitle(title);
                movie.setUrlSelf(image_whole_Url);
                movie.setReleaseDateTheater(release_date);
                movie.setOverview(overview);
                movie.setUrlThumbnail(image_whole_Url);
               */
                listMovies.add(movie);
            } if(pageCount<pages) {
                pageCount++;
                sendJsonRequest(pageCount);
            }

        }
    } catch (JSONException e) {
    }
        return listMovies;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box_office, container, false);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        listMoviesHits = (RecyclerView) view.findViewById(R.id.listMoviesHits);
        listMoviesHits.setLayoutManager(gridLayoutManager);
        adapterBoxOffice = new AdapterBoxOffice(getActivity());

        adapterBoxOffice.setClickListener(this);


        sendJsonRequest(1);
        listMoviesHits.addOnScrollListener( new EndlessRecyclerOnScrollListener(gridLayoutManager){
            @Override
            public void onLoadMore(int current_page) {
                int lastFirstVisiblePosition = ((GridLayoutManager) listMoviesHits.getLayoutManager()).findFirstVisibleItemPosition();
                ((GridLayoutManager) listMoviesHits.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);

                if(pages>pageCount) {
                    // pageCount++;
                    // sendJsonRequest(pageCount);
                }


            }
        }  );


        listMoviesHits.setAdapter(adapterBoxOffice);
        return view;
    }

    @Override
    public void itemClicked(View view,int position) {

        FragmentMovieOverview fragmentMovieOverview= new FragmentMovieOverview();
        Bundle bundle = new Bundle();
       // String myMessage = "Stackoverflow is cool!";
       // bundle.putString("overview", myMessage );
       // FragmentMovieOverview fragInfo = new FragmentMovieOverview();
       // fragInfo.setArguments(bundle);
       // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragInfo);
       // getActivity().getSupportFragmentManager().beginTransaction().commit();
        Intent i = new Intent(getActivity(),DeatilBoxOfficeActivity.class);

        movie=this.listMovies.get(position);
        i.putExtra("stringId",movie.getStringid());
        i.putExtra("title", movie.getTitle());
        i.putExtra("overview", movie.getOverview());
        i.putExtra("releaseDate", movie.getReleaseDateTheater());
       // i.putExtra("Image", movie.getUrlThumbnail());
        i.putExtra("favourite",false);
        //i.putExtra("id",movie.getId());

        startActivity(i);

    }
}

