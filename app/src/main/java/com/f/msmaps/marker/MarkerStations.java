package com.f.msmaps.marker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.f.msmaps.R;
import com.f.msmaps.dialog.Userdialog;
import com.f.msmaps.geocoder.MyUserAddress;
import com.f.msmaps.retrofit.MyPojo;
import com.f.msmaps.retrofit.NetworkYandexApi;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkerStations {

    public MarkerStations(MapView mapView, GeoPoint geoPoint) {
        Context context = mapView.getContext();
        NetworkYandexApi.getInstance().getJsonApi().getMyResponse(
                context.getResources().getString(R.string.key),
                context.getResources().getString(R.string.format),
                geoPoint.getLatitude(),
                geoPoint.getLongitude(),
                50,
                context.getResources().getString(R.string.land)
        ).enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                MyPojo station = response.body();
                for (int i = 0; i < station.getStations().length; i++) {
                    GeoPoint stationPoint =
                            new GeoPoint(station.getStations()[i].getLat()
                                    , station.getStations()[i].getLng());

                    Marker marker = new Marker(mapView);
                    marker.setPosition(stationPoint);
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    if (station.getStations()[i].getTransportType().equals(context.getString(R.string.rgd))) {
                        marker.setIcon(ContextCompat.getDrawable(context, R.drawable.train_station));
                    }
                    if (station.getStations()[i].getTransportType().equals(context.getString(R.string.bus))) {
                        marker.setIcon(ContextCompat.getDrawable(context, R.drawable.bus));
                    }
                    if (station.getStations()[i].getTransportType().equals(context.getString(R.string.plane))) {
                        marker.setIcon(ContextCompat.getDrawable(context, R.drawable.plane));
                    }
                    marker.setTitle("станция"
                            + "\t"
                            + station.getStations()[i].getTitle()
                            + "\n"
                            + "расстояние"
                            + "\t"
                            + "~"
                            + "\t"
                            + String.format("%.3f", station.getStations()[i].getDistance())
                            + "\t"
                            + "м");
                    mapView.getOverlays().add(marker);

                }
            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Toast.makeText(context, "Error occurred while getting request!", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
