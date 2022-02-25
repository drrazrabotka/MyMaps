package com.f.msmaps.marker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.f.msmaps.R;
import com.f.msmaps.dialog.Userdialog;
import com.f.msmaps.geocoder.MyUserAddress;
import com.google.android.material.snackbar.Snackbar;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.net.InetAddress;

import androidx.core.content.ContextCompat;

public class MyMarker {
    private IMapController controller;
    private String address;

    public MyMarker(MapView mapView, GeoPoint geoPoint) {
        if (isNetworkAvailable(mapView.getContext()) == true) {
            address = new MyUserAddress(mapView.getContext(), geoPoint).MyUserAddress();

        }
        if (isNetworkAvailable(mapView.getContext()) == false || address == null) {
            address = "no network connection";
        }
        mapView.getOverlays().clear();
        mapView.invalidate();
        controller = mapView.getController();
        Marker marker = new Marker(mapView);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(ContextCompat.getDrawable(mapView.getContext(), R.drawable.user_location_icon));
        mapView.getOverlays().add(marker);
        controller.animateTo(geoPoint);
        marker.setOnMarkerClickListener((marker1, mapView1) -> {
            if (isNetworkAvailable(mapView.getContext()) == true) {
                address = new MyUserAddress(mapView.getContext(), geoPoint).MyUserAddress();
                Userdialog userdialog = new Userdialog(mapView.getContext());
                userdialog.createDialog(address);
            }
            if (isNetworkAvailable(mapView.getContext()) == false || address == null) {
                address = "no network connection";
            }

            return false;
        });

        mapView.invalidate();

    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
