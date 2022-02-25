package com.f.msmaps.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stations {
    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("transport_type")
    @Expose
    private String transport_type;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("station_type_name")
    @Expose
    private String station_type_name;


    public double getDistance() {
        return distance;
    }

    public String getTitle() {
        return title;
    }

    public String getTransportType() {
        return transport_type;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getStationTypeName() {
        return station_type_name;
    }
}
