package com.example.android.material_design.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.android.material_design.Fragments.FragmentPopular;
import com.example.android.material_design.Fragments.FragmentTest;
import com.example.android.material_design.Fragments.FragmentUpcoming;
import com.example.android.material_design.R;
import com.example.android.material_design.SlidingTabLayout;
import com.example.android.material_design.VolleySingleton;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import it.neokree.materialtabs.MaterialTabHost;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends AppCompatActivity implements MaterialTabListener
{

private ViewPager mPager;

    public VolleySingleton volleySingleton;
    public ImageLoader imageLoader;
    public RequestQueue requestQueue;

    private SlidingTabLayout mTabs;

    public static final int MOVIES_SEARCH_RESULTS=0;

    public static final int MOVIES_HITS=1;

    public static final int MOVIES_UPCOMING=2;
    public MaterialTabHost tabHost;
    public ViewPager viewPager;

    public ImageView iconSortName;
    public  SubActionButton buttonSortName;

   public SubActionButton.Builder itemBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
           fab.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View view) {
                  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();
            }
         });
*/
          //@Override
          //  public void onClick(View view) {


           //  iconSortName= new ImageView(this);
            //   iconSortName.setImageResource(R.drawable.next);
             //  itemBuilder= new SubActionButton.Builder(this);

             //  buttonSortName= itemBuilder.setContentView(iconSortName).build();
              //   FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
              //           .addSubActionView(iconSortName)
              ////           .attachTo(fab)
             //            .build();

          //  }
         //});





        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // NavigationDrawerFrgment drawerFragment = (NavigationDrawerFrgment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        //drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);

       viewPager = (ViewPager) findViewById(R.id.pager);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());


        viewPager.setAdapter(adapter);
      viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
       @Override
           public void onPageSelected(int position) {

              tabHost.setSelectedNavigationItem(position);
            }
       });

   for (int i = 0; i < adapter.getCount(); i++) {

            tabHost.addTab(
                tabHost.newTab()
                          .setText(adapter.getPageTitle(i))
                          .setTabListener(this));

       }




    }





    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
   //   if(id==R.id.material_tabs)
      //{
      // startActivity(new Intent(this, ActivityUsingTabLibrary.class));
     //  }
        return super.onOptionsItemSelected(item);
    }



    class MyPagerAdapter extends FragmentStatePagerAdapter{



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

               case MOVIES_SEARCH_RESULTS :

                 //  fragment=FragmentPopular.newInstance("","");
                   fragment= FragmentPopular.newInstance("","");

                   break;
               case MOVIES_HITS :
                   fragment= FragmentUpcoming.newInstance("","");


                  break;
             case MOVIES_UPCOMING :


                 fragment= FragmentTest.newInstance("","");
        break;


    }

          return fragment;
          //return null;
       }

      // public Drawable getIcon(int position){

     //      return getResources().getDrawable(icons[position]);
       //}


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
