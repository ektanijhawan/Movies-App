package com.example.android.material_design.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.material_design.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMovieOverview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMovieOverview extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView mTextView;

    public FragmentMovieOverview() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMovieOverview.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMovieOverview newInstance(String param1, String param2) {
        FragmentMovieOverview fragment = new FragmentMovieOverview();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Intent intent=getI

        View view=inflater.inflate(R.layout.fragment__movie__overview, container, false);
        Bundle bundle = this.getArguments();
      String strtext = bundle.getString("e");
        String s=null;
if(strtext.equals(s))
      Toast.makeText(getActivity(),"empty",Toast.LENGTH_SHORT).show();


      mTextView = (TextView) view.findViewById(R.id.hey);
       mTextView.setText(strtext );
        return view;
    }

}
