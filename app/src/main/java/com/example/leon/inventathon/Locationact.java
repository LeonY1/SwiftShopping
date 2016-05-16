package com.example.leon.inventathon;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.leon.inventathon.R;

import java.util.ArrayList;

public class Locationact extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    TextView txtLat;
    private Location mLastLocation;
    private final LocationListener loclist = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //code
            System.out.println("onLocationChanged");
            mLastLocation = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("onProviderDisabled");
            //turns off gps services
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationact);
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView v = (SearchView) findViewById(R.id.search_bar);
        v.setSubmitButtonEnabled(true);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Locationreg.class);
                startActivity(i);
            }
        });
        TextView tv = (TextView) findViewById(R.id.textbox);
        v.setIconified(false);
        v.setQueryRefinementEnabled(true);
        if(null!= v){
            v.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
            v.setIconifiedByDefault(false);
            //tv.setText("hi");
        }
        SearchView.OnQueryTextListener sv = new SearchView.OnQueryTextListener(){
            public boolean onQueryTextChange(String newText){
                return true;
            }
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
        };

        v.setOnQueryTextListener(sv);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,5,loclist);
        mLastLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //tv.setText(v.getQuery().toString());
        String s = v.getQuery().toString();
        //ArrayList<LatLng> list = productdb.find(s);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng currentloc = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentloc,14.0f));


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
