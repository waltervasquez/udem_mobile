package com.mobile.udem.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.mobile.udem.models.Locations;
import com.mobile.udem.ui.fragmets.MapsPagerFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by osmar on 08-13-17.
 */

public class MapsAdapter extends MapViewPager.Adapter {
    private List<Locations> locations;
    public static ArrayList<String> TITLES = new ArrayList<>();
    public static ArrayList<String> DESC = new ArrayList<>();
    public static ArrayList<String> IMAGES = new ArrayList<>();
    public ArrayList<CameraPosition> POSITIONS = new ArrayList<>();

    public MapsAdapter(FragmentManager fm, List<Locations> locations) {
        super(fm);
        this.locations = locations;

        for (int i=0; i<locations.size(); i++){

            TITLES.add(locations.get(i).getNombre());
            IMAGES.add(locations.get(i).getFoto());
            DESC.add(locations.get(i).getDescripcion());
            POSITIONS.add(CameraPosition.fromLatLngZoom(new LatLng(locations.get(i).getLat(),
                    locations.get(i).getLng()), 18f));

        }
        // Event name iterator
        Iterator<String> titles = TITLES.iterator();
        while(titles.hasNext()){
            String elemento = titles.next();
            Log.i("iterator",elemento);
        }
    }

    @Override
    public CameraPosition getCameraPosition(int position) {
        return POSITIONS.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES.get(position);
    }

    public static String getDesc(int position) {
        return DESC.get(position);
    }
    public static String getFoto(int position){
        return IMAGES.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return MapsPagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return locations.size();
    }
}
