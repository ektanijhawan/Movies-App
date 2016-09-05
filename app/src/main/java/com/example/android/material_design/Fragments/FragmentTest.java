package com.example.android.material_design.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.material_design.Adapters.AdapterFavourite;
import com.example.android.material_design.Database.DataSource;
import com.example.android.material_design.Database.DbHelper;
import com.example.android.material_design.Activities.DeatilBoxOfficeActivity;
import com.example.android.material_design.EndlessRecyclerOnScrollListener;
import com.example.android.material_design.Model.Movie;
import com.example.android.material_design.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest extends Fragment implements AdapterFavourite.ClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView listMoviesHits;
    String image_whole_Url;
    public  String overview;
    String image_post_URL;
    public ArrayList<Movie> listMovies = new ArrayList<>();
    public AdapterFavourite adapterFavourite;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String id;
    DbHelper dbHelper;
    ArrayList<Movie> movieFromDatabase;
    public FragmentTest() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentTest newInstance(String param1, String param2) {
        FragmentTest fragment = new FragmentTest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<Movie> movieList = new ArrayList<>();
    public DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment   // Inflate the layout for this fragment
        dbHelper=new DbHelper(getActivity());
        View view=inflater.inflate(R.layout.fragment_box_office, container, false);
        GridLayoutManager gridLayoutManager=   new GridLayoutManager(getActivity(),2);
        listMoviesHits= (RecyclerView) view.findViewById(R.id.listMoviesHits);
        listMoviesHits.setLayoutManager(gridLayoutManager);
        adapterFavourite =new AdapterFavourite(getActivity());
    //    button= (Button) view.findViewById(R.id.addButton);
        adapterFavourite.setClickListener(this);
        listMoviesHits.setAdapter(adapterFavourite);
        movieFromDatabase= new ArrayList<Movie>();
getMovies();
      //  sendJsonRequest();
        listMoviesHits.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                int lastFirstVisiblePosition=((GridLayoutManager)listMoviesHits.getLayoutManager()).findFirstVisibleItemPosition();
                ( (GridLayoutManager)listMoviesHits.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);


                final Movie movie= new Movie();


            }
        });
        return view;

    }
    public void setMovieListFavourite(ArrayList<Movie> listMovies) {

        this.listMovies = listMovies;
      //  notifyItemChanged(0, listMovies.size());
    }
    @Override
    public void itemClicked(View view, int position) {
         Intent i = new Intent(getActivity(),DeatilBoxOfficeActivity.class);
        Movie currentMovie = listMovies.get(position);
        int id1=0;
        id=currentMovie.getStringid();
        String name=currentMovie.getTitle();
        Toast.makeText(getActivity(),"position is "+name,Toast.LENGTH_SHORT).show();
        Movie movie;
        movie=dbHelper.getMovieFromDatabase(id);
       String title= movie.getTitle();


String urlSelf=movie.getUrlSelf();
        String coverImage=movie.getCoverImage();
        String audienceScore=movie.getAudienceScore();
        String popularity=movie.getPopularity();
        String tagLine=movie.getTagLine();
        String releaseDate=movie.getReleaseDateTheater();
        String duration=movie.getDuration();
        String genre=movie.getGenre();
        String overview= movie.getOverview();

        i.putExtra("stringId",id);
        i.putExtra("title", title);
        i.putExtra("coverImage", coverImage);
        i.putExtra("urlSelf", urlSelf);
        i.putExtra("audienceScore", audienceScore);
        i.putExtra("popularity", popularity);
        i.putExtra("tagLine", tagLine);
        i.putExtra("releaseDate", releaseDate);
        i.putExtra("duration", duration);
        i.putExtra("genre",genre);
        i.putExtra("overview",overview);
        i.putExtra("fragment","favourite");
        startActivity(i);




      /*  Movie movie=this.listMovies.get(position);
        i.putExtra("title", movie.getTitle());
        i.putExtra("overview", movie.getOverview());
        i.putExtra("releaseDate", movie.getReleaseDateTheater());
        i.putExtra("Image", movie.getUrlThumbnail());
        i.putExtra("favourite",true);
        startActivity(i);
        */


        {

            // movieFromDatabase=dbHelper.getMovieFeomDatabase()


        }
    }
    private void getMovies(){

        ArrayList<Movie> list = new ArrayList<Movie>();
        list= dbHelper.getAllComments();
        adapterFavourite.setMovieList(list);
        setMovieListFavourite(list);

        // movieList.clear();
        // for(Movie movie : list){
        //      movieList.add(movie);
        //}
    }


    @Override
    public void onResume() {
        super.onResume();
        getMovies();
        adapterFavourite.notifyDataSetChanged();
    }
}

