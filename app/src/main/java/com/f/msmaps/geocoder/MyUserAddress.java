package com.f.msmaps.geocoder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

import java.io.IOException;
import java.util.List;

public class MyUserAddress {

    private StringBuilder strAddress;

    private Context context;
    private GeoPoint geoPoint;


    public MyUserAddress(Context context, GeoPoint geoPoint) {
        this.context = context;
        this.geoPoint = geoPoint;
    }

    public String MyUserAddress() {
        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> addresses =
                    geocoder.getFromLocation(geoPoint.getLatitude(), geoPoint.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address currentAddress = addresses.get(0);
                strAddress = new StringBuilder();
                if (currentAddress != null) {
                    strAddress.append(currentAddress.getLocality() + "\n");
                    strAddress.append(currentAddress.getThoroughfare() + "\n");
                    strAddress.append(currentAddress.getFeatureName());
                }


            }

        } catch (IOException e) {

        }

        if (strAddress == null) {
            strAddress.append("no data received").toString();
        }

        return strAddress.toString();
    }


}
