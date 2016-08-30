package com.example.android.material_design.Adapters;

import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.material_design.Fragments.FragmentMovieOverview;

/**
 * Created by Ekta on 03-08-2016.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter{




    public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {

        super(fm);


    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        android.support.v4.app.Fragment fragment= null;

        switch(position){

            case 0 :

                //  fragment=FragmentPopular.newInstance("","");
                fragment= FragmentMovieOverview.newInstance("","");

                break;
            case 1 :
                fragment= FragmentMovieOverview.newInstance("","");


                break;
            case 2 :


                fragment=FragmentMovieOverview.newInstance("","");
                break;


        }

        return fragment;
        //return null;
    }


    @Override
    public int getCount() {
        return 3;
    }
}
