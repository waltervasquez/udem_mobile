package com.mobile.udem.ui.fragmets;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobile.udem.R;
import com.mobile.udem.adapters.MapsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsPagerFragment extends Fragment {
    TextView mTitle, mDesc;
    SimpleDraweeView mImage;
    private int index;

    public MapsPagerFragment() {
        // Required empty public constructor
    }
    public static MapsPagerFragment newInstance(int i) {

        MapsPagerFragment fragment = new MapsPagerFragment();
        Bundle args = new Bundle();
        args.putInt("INDEX", i);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_maps_pager, container, false);
        mTitle = (TextView) rootView.findViewById(R.id.location_title);
        mDesc = (TextView) rootView.findViewById(R.id.location_description);
        mImage = (SimpleDraweeView) rootView.findViewById(R.id.location_image);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) index = args.getInt("INDEX", 0);

        mTitle.setText(MapsAdapter.TITLES.get(index));
        final Uri imageUri = Uri.parse(MapsAdapter.getFoto(index));
        mImage.setImageURI(imageUri);
        mDesc.setText(MapsAdapter.getDesc(index));

    }
}
