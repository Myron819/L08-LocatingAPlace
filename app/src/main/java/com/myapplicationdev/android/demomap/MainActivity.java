package com.myapplicationdev.android.demomap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    String[] region = { "No selection", "North", "Central", "East"};

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng lDefault = new LatLng(1.385704,103.8650033);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lDefault,11));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng l1 = new LatLng(1.463088,103.8238036);
                Marker m1 = map.addMarker(new
                        MarkerOptions()
                        .position(l1)
                        .title("HQ North")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));

                LatLng l2 = new LatLng(1.3004,103.8437593);
                Marker m2 = map.addMarker(new
                        MarkerOptions()
                        .position(l2)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng l3 = new LatLng(1.354313,103.9318743);
                Marker m3 = map.addMarker(new
                        MarkerOptions()
                        .position(l3)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        // Triggered when user click any marker on the map
                        Toast.makeText(getApplicationContext(), marker.getTitle(),Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // 1.463088,103.8238036,16
        // 1.3004,103.8437593,16
        // 1.354313,103.9318743,16

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng l = new LatLng(1.463088,103.8238036);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng l = new LatLng(1.3004,103.8437593);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng l = new LatLng(1.354313,103.9318743);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
                }
            }
        });

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
            Spinner spin = (Spinner) findViewById(R.id.spinner);
            spin.setOnItemSelectedListener(this);

            //Creating the ArrayAdapter instance
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, region);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spin.setAdapter(aa);
        }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
            if (position == 1) {
                LatLng l = new LatLng(1.463088,103.8238036);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
            } else if (position == 2) {
                LatLng l = new LatLng(1.3004,103.8437593);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
            } else if (position == 3) {
                LatLng l = new LatLng(1.354313,103.9318743);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(l,16));
            }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}