package com.f.msmaps.map;

import android.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MyMap {
    IMapController controller;

    public MyMap(MapView mapView) {
        mapView.setTileSource(TileSourceFactory.OpenTopo);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);
        mapView.setBuiltInZoomControls(false);

        controller = mapView.getController();
        controller.setZoom(15.5);

        controller.setCenter(new GeoPoint(55.7522, 37.6156));
        mapView.invalidate();

    }
}
