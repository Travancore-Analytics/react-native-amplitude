package com.amplitude;


import com.amplitude.api.Amplitude;
import com.amplitude.api.Identify;
import com.amplitude.api.TrackingOptions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeAnalytics extends ReactContextBaseJavaModule  {

    public static final String REACT_CLASS = "AmplitudeAnalytics";

    public AmplitudeAnalytics(ReactApplicationContext context) {
        super(context);
    }


    @ReactMethod void initialize(String apiKey){
        Amplitude.getInstance().initialize(getReactApplicationContext().getApplicationContext(), apiKey)
                .enableForegroundTracking(getCurrentActivity().getApplication());
    }

    @ReactMethod void disableTrackingOptions() {
        TrackingOptions options = new TrackingOptions().disableCity().disableCountry().disableLatLng().disableRegion().disableDeviceManufacturer().disableDeviceModel().disableDeviceBrand();
        Amplitude.getInstance().setTrackingOptions(options);
    }

    @ReactMethod void setUserPropertyForOnce(String key, String value){
        Identify identify = new Identify().setOnce(key, value);
        Amplitude.getInstance().identify(identify);
    }

    @ReactMethod void unSetUserProperty(String key){
        Identify identify = new Identify().unset(key);
        Amplitude.getInstance().identify(identify);
    }

    @ReactMethod
    public void logEvent(String eventName, ReadableMap parameters) throws JSONException {

        JSONObject eventProperties = Utils.convertMapToJson(parameters);
        com.amplitude.api.Amplitude.getInstance().logEvent(eventName, eventProperties);
    }



    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
