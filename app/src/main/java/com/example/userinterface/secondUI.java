package com.example.userinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class secondUI extends AppCompatActivity {
//initialize variable mo muna
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_u_i);
//assign variable pre
    supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.googlemap);

    //location naman
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(secondUI.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager. PERMISSION_GRANTED){
            //pag pumayag
            //call method tayo

            getCurrentLocation();
        }else{
            //pag nadeny permission mo pre
            //request ka ulit
            ActivityCompat.requestPermissions(secondUI.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }




    }

    private void getCurrentLocation() {
        //Initialize task location
        @SuppressLint("MissingPermission") Task <Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //pag goods
                if (location != null){
                    //pasok mapa
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude()
                            ,location.getLongitude());
                            //create marker options
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("Nandito ako putangina");
                            //zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                            //add marker on map
                            googleMap.addMarker(options);

                        }
                    });
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //when permission granted
                //call method
                getCurrentLocation();
            }
        }
    }
}