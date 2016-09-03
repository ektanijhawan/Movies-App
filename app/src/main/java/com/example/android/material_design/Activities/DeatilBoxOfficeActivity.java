package com.example.android.material_design.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.material_design.Fragments.FragmentMovieDetail;
import com.example.android.material_design.R;


public class DeatilBoxOfficeActivity extends AppCompatActivity {

     Bundle bundle;
    Fragment movieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil_box_office);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();
        movieDetail = new FragmentMovieDetail();
        movieDetail.setArguments(bundle);
        Intent i= getIntent();
        String s= i.getStringExtra("stringId");
       Toast.makeText(this,"id is "+s,Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_detail_container, movieDetail)
                .commit();


/*

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new MyPagerAdapter
                (getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle bundle = new Bundle();
                bundle.putString("e", "SJDFSDS");
// set Fragmentclass Arguments
               Fragment fragobj= null;
                fragobj= FragmentMovieOverview.newInstance("","");
                fragobj.setArguments(bundle);
                viewPager.setCurrentItem(tab.getPosition());




                //fragmentManager.beginTransaction().replace(R.id.pager,fragobj).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        */



     /*  dbHelper = new DbHelper(this);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getmImageLoader();
        Intent i = getIntent();
        id=i.getIntExtra("id",0);
        imageString = i.getStringExtra("Image");
        titleString = i.getStringExtra("title");
        overviewString = i.getStringExtra("overview");
        releaseDateString = i.getStringExtra("releaseDate");
        favourite = i.getBooleanExtra("favourite", false);
         //    View view= findViewById(R.id.relativelayout);
         image = (ImageView) findViewById(R.id.imageView);



        if(id!=0)
            sendjsonRequest(id);





*/
     //   TextView title = (TextView) findViewById(R.id.title);
      //title.setText(titleString);

        // image.setImageResource(getIntent().getCharExtra("image","a"));

  // TextView releaseDate = (TextView) findViewById(R.id.releaseDate);
      //  releaseDate.setText(releaseDateString);

    //    TextView overview = (TextView) findViewById(R.id.overview);
    //    overview.setText(overviewString);
     //   button = (Button) findViewById(R.id.addButton);



     //   if (!favourite) {
//            button.setVisibility(View.VISIBLE);



/*      button.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v) {
                //   ContentValues values= new ContentValues();
                // values.put(TITLE,movie.getTitle());
                //values.put(OVERVIEW,movie.getOverview());
                //values.put(RELEASEDATE,movie.getReleaseDateTheater());

                flag = dbHelper.insertContact(id,titleString, overviewString, releaseDateString, imageString);


            }
        });

        */
    }
    //    if (flag==true)
      //  Toast.makeText(this,"inserted in db",Toast.LENGTH_SHORT).show();
        //   if (savedInstanceState == null) {
      //      getSupportFragmentManager().beginTransaction()
      //              .add(R.id.container, new PlaceholderFragment())
       //             .commit();
      //  }




//share=imageString+titleString+overviewString+releaseDateString;

     //   Intent intent=new Intent(this,FragmentMovieOverview.class);
       // intent.putExtra("overview",overviewString);
    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              sendEmail();
           }
       });
        */

    }









//}
