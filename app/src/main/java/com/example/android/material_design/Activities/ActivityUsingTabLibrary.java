package com.example.android.material_design.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.material_design.Fragments.MyFragment;
import com.example.android.material_design.R;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by sunny on 2/29/2016.
 */
public class ActivityUsingTabLibrary extends AppCompatActivity implements MaterialTabListener{

MyPagerAdapter adapter;
    public MaterialTabHost tabHost;
    public ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_using_tab_library);

       tabHost= (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager= (ViewPager) findViewById(R.id.pager);


      adapter = new  MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter (adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {

               tabHost.setSelectedNavigationItem(position);
            }
        });
  for (int i=0;i<adapter.getCount();i++){

     tabHost.addTab(tabHost.newTab().setIcon(adapter.getIcon(i)).setTabListener(this));

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


    class MyPagerAdapter extends FragmentStatePagerAdapter {
        int[] icons={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        String[] tabs;
        FragmentManager fragmentManager;
        public MyPagerAdapter(FragmentManager fm) {

            super(fm);
            fragmentManager=fm;
            //tabs= getResources().getStringArray(R.array.tabs);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        @Override
        public Fragment getItem(int position) {
          MyFragment myFragment= MyFragment.getInstance(position);

            return myFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }



        public Drawable getIcon(int position){

            return getResources().getDrawable(icons[position]);
        }
    }
}
