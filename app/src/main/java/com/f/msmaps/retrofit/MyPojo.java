package com.f.msmaps.retrofit;

public class MyPojo {
    private Stations[] stations;

    public Stations[] getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "ClassPojo [stations = " + stations + "]";
    }
}
