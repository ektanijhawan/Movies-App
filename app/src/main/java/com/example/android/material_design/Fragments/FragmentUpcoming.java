package com.example.android.material_design.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.material_design.Activities.DeatilBoxOfficeActivity;
import com.example.android.material_design.Adapters.AdapterBoxOffice;
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
 * Use the {@link FragmentUpcoming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUpcoming extends Fragment  implements AdapterBoxOffice.ClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public VolleySingleton volleySingleton;
    public ImageLoader imageLoader;
    public RequestQueue requestQueue;
    public String title;
int pageCount=1;
    public String image_preUrl = "http://image.tmdb.org/t/p/w342/";
    private RecyclerView listMoviesHits;
    String image_whole_Url;
    public String overview;
    String image_post_URL;
    int pages;
    // TODO: Rename and change types of parameters
    private String mParam1;
 //   private int visibleThreshold = 4;
    private String mParam2;
    public String release_date;
    public ArrayList<Movie> listMovies = new ArrayList<>();
    public AdapterBoxOffice adapterBoxOffice;
public long dbId;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true; // True if we are still waiting for the last set of data to load.



    public FragmentUpcoming(){
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentUpcoming newInstance(String param1, String param2) {
        FragmentUpcoming fragment = new FragmentUpcoming();
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
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getmRequestQueue();
    }


    public void sendJsonRequest(int pageCount) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET
                , "http://api.themoviedb.org/3/movie/top_rated?api_key=74a8c711917fabf892c994dc63136a80&page="+pageCount

                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                listMovies = parseJsonResponse(response);
                adapterBoxOffice.setMovieList(listMovies);
                //Toast.makeText(getActivity(),response.toString() + " ",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), "error" + " ", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(request);

    }

    public ArrayList<Movie> parseJsonResponse(JSONObject response) {

        //ArrayList<Movie> listMovies= new ArrayList<>();

        {
            if (response == null || response.length() == 0)
                return null;
        }

        StringBuilder data = new StringBuilder();
        //if (response.has("results")) {
        if(response != null && response.length() > 0){
        try {
            pageCount= response.getInt("page");
            pages= response.getInt("total_pages");


                JSONArray arrayMovieResults = response.getJSONArray("results");
                for (int i1 = 0; i1 < arrayMovieResults.length(); i1++) {

                    JSONObject currentMovie = arrayMovieResults.getJSONObject(i1);
                    int id=currentMovie.getInt("id");
                    String sId=currentMovie.getString("id");
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
                    movie.setStringid(sId);
                    movie.setId(id);
                    movie.setTitle(title);
                    movie.setUrlSelf(image_whole_Url);
                    movie.setReleaseDateTheater(release_date);
                    movie.setOverview(overview);
                    movie.setUrlThumbnail(image_whole_Url);
                    listMovies.add(movie);
                }
            if(pageCount<pages) {
                pageCount++;
                sendJsonRequest(pageCount);
            }

//adding the movies to the array
                //L.t(getActivity(), listMovies.toString());
            }catch (JSONException e) {

        }



        }

        return listMovies;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        //startActivity(new Intent(getActivity(), DeatilBoxOfficeActivity.class));



        Intent i = new Intent(getActivity(),DeatilBoxOfficeActivity.class);

        Movie movie=this.listMovies.get(position);
            i.putExtra("stringId",movie.getStringid());
            i.putExtra("id",movie.getId());
        i.putExtra("title", movie.getTitle());
        i.putExtra("overview", movie.getOverview());
        i.putExtra("releaseDate", movie.getReleaseDateTheater());
        i.putExtra("Image",movie.getUrlThumbnail());
        i.putExtra("favourite",false);

            Classh c= new Classh();
            c.setData("title");
        startActivity(i);

    }
}
