package com.f.msmaps.location;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.f.msmaps.marker.MarkerStations;
import com.f.msmaps.marker.MyMarker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


public class MyLocation extends LocationCallback {

    private final LocationCallback locationCallback;
    private final FusedLocationProviderClient mFusedLocationClient;


    @SuppressLint("ServiceCast")
    public MyLocation(MapView mapView) {
        Context context = mapView.getContext();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) return;
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        GeoPoint geoPoint =
                                new GeoPoint(location.getLatitude(), location.getLongitude());

                        new MyMarker(mapView, geoPoint);
                        new MarkerStations(mapView, geoPoint);

                        mFusedLocationClient.removeLocationUpdates(locationCallback);
                    }
                }
            }
        };
        if (!checkLocationPermission(context)) return;
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    private boolean checkLocationPermission(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }
}
