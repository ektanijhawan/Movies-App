package com.example.android.material_design;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFrgment extends Fragment {


    public RecyclerView recyclerView;
    private View containerView;

public static final String PREF_NAME_FILE="testpref";
    public static final String KEY_USER_LEARNED_DRAWER="user_learned_drawer";
public boolean mUserLearnedDrawer;
    public boolean mFromSavedInstanceState;
    public ActionBarDrawerToggle mDrawerToggle;
   public DrawerLayout mDrawerLayout;
    private RecyclerViewAdapter adapter;

    public NavigationDrawerFrgment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPrferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));


        if (savedInstanceState!=null)
            mFromSavedInstanceState=true;
    }


    public static List<Information> getData(){
        List<Information> data= new ArrayList<>();
      int[] icons={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        String[] titles={"hi","i","am","ekta"};


        for (int i=0;i<icons.length && i<titles.length;i++)
        {
            Information current= new Information();
            current.iconId=icons[i];
            current.title=titles[i];
            data.add(current);
        }
        return data;


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout=inflater.inflate(R.layout.fragment_navigation_drawer_frgment, container, false);
        recyclerView= (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter= new RecyclerViewAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public void setUp(  int fragmentId,DrawerLayout drawerlayout, android.support.v7.widget.Toolbar toolbar) {
        containerView=getActivity().findViewById(fragmentId);
        mDrawerLayout=drawerlayout;
        mDrawerToggle= new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.drawer_open,R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {

                if (!mUserLearnedDrawer){

                    mUserLearnedDrawer=true;
                    saveToPrferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                }

                    getActivity().invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {


                getActivity().invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

        if (!mUserLearnedDrawer  && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }



        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
             mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

public static void  saveToPrferences(Context context,String preferencename,String preferenceValue){
    SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_NAME_FILE,Context.MODE_PRIVATE);
    SharedPreferences.Editor editor= sharedPreferences.edit();
    editor.putString(preferencename,preferenceValue);
    editor.apply();

}

    public static String readFromPrferences(Context context,String preferencename,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_NAME_FILE, Context.MODE_PRIVATE);
       return sharedPreferences.getString(preferencename,defaultValue);

    }
}
