package com.mobile.udem.ui.fragmets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.mobile.udem.R;
import com.mobile.udem.adapters.MapsAdapter;
import com.mobile.udem.models.Locations;
import com.mobile.udem.utils.Utils;

import java.util.ArrayList;


public class MapsFragment extends Fragment implements  MapViewPager.Callback {

    GoogleMap map;
    private ViewPager viewPager;
    private MapViewPager mvp;
    private MapsAdapter mapsAdapter;
    ArrayList<Locations> locations;

    public MapsFragment(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setPageMargin(Utils.dp(getContext(), 10));
        Utils.setMargins(viewPager, 0, 0, 0, Utils.getNavigationBarHeight(getContext()));

        getData();

        mvp = new MapViewPager.Builder(getContext())
                .mapFragment(map)
                .viewPager(viewPager)
                .position(0)
                .adapter(new MapsAdapter(getActivity().getSupportFragmentManager(), locations))
                .callback(this)
                .build();
        return rootView;
    }

    private void getData() {
        locations = new ArrayList<>();
        locations.add(new Locations("Edificio A","Descipcion de A",12.1265515,-86.3116156,"https://i1.wp.com/web.udem.edu.ni/wp-content/uploads/2017/01/imagen-lu.png?resize=300%2C225?i=59076"));
        locations.add(new Locations("Edificio B","Descipcion de A",12.1265515,-86.3116156,"https://i1.wp.com/web.udem.edu.ni/wp-content/uploads/2017/01/imagen-lu.png?resize=300%2C225?i=59076"));
        locations.add(new Locations("Edificio C","Descipcion de A",12.1265515,-86.3116156,"https://i1.wp.com/web.udem.edu.ni/wp-content/uploads/2017/01/imagen-lu.png?resize=300%2C225?i=59076"));
        locations.add(new Locations("Edificio D","Descipcion de A",12.1265515,-86.3116156,"https://i1.wp.com/web.udem.edu.ni/wp-content/uploads/2017/01/imagen-lu.png?resize=300%2C225?i=59076"));

    }


    @Override
    public void onMapViewPagerReady() {
        mvp.getMap().setPadding(
                0,
                Utils.dp(getContext(), 40),
                Utils.getNavigationBarWidth(getContext()),
                viewPager.getHeight() + Utils.getNavigationBarHeight(getContext()));
        mvp.getMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}
