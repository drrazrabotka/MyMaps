package com.f.msmaps.map;

import android.content.Context;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;

public class ConfigurationMap {

    public ConfigurationMap(Context context) {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
    }
}
