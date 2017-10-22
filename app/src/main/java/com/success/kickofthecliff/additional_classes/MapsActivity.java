package com.success.kickofthecliff.additional_classes;

/** Карта **/

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.success.kickofthecliff.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double Latitude;
    private double Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        Latitude = intent.getDoubleExtra("latitude",1);//получаем широту
        Longitude = intent.getDoubleExtra("longitude", 1);//получаем долготу

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Vladivostok and move the camera
        LatLng vladivostok = new LatLng(Latitude, Longitude);
        mMap.addMarker(new MarkerOptions().position(vladivostok).title("ВЛАДИВОСТОК"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vladivostok));
    }
}
