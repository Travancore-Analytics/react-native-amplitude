package com.amplitude;


import com.amplitude.api.Amplitude;
import com.amplitude.api.Identify;
import com.amplitude.api.TrackingOptions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AmplitudeAnalytics extends ReactContextBaseJavaModule  {

    public static final String REACT_CLASS = "AmplitudeAnalytics";

    public AmplitudeAnalytics(ReactApplicationContext context) {
        super(context);
    }


    @ReactMethod void initialize(String apiKey){
        Amplitude.getInstance().initialize(getReactApplicationContext().getApplicationContext(), apiKey)
                .enableForegroundTracking(getCurrentActivity().getApplication());
    }

    @ReactMethod void setTrackingOptions(ReadableArray options) {
        TrackingOptions trackingOptions = new TrackingOptions();
        ArrayList optionsList = options.toArrayList();
        if (optionsList.contains("disableAdid")){
            trackingOptions.disableAdid();
        }
        if (optionsList.contains("disableCarrier")){
            trackingOptions.disableCarrier();
        }
        if (optionsList.contains("disableCity")){
            trackingOptions.disableCity();
        }
        if (optionsList.contains("disableCountry")){
            trackingOptions.disableCountry();
        }
        if (optionsList.contains("disableDeviceBrand")){
            trackingOptions.disableDeviceBrand();
        }
        if (optionsList.contains("disableDeviceManufacturer")){
            trackingOptions.disableDeviceManufacturer();
        }
        if (optionsList.contains("disableDeviceModel")){
            trackingOptions.disableDeviceModel();
        }
        if (optionsList.contains("disableDMA")){
            trackingOptions.disableDma();
        }
        if (optionsList.contains("disableIPAddress")){
            trackingOptions.disableIpAddress();
        }
        if (optionsList.contains("disableLanguage")){
            trackingOptions.disableLanguage();
        }
        if (optionsList.contains("disableLatLng")){
            trackingOptions.disableLatLng();
        }
        if (optionsList.contains("disableOSName")){
            trackingOptions.disableOsName();
        }
        if (optionsList.contains("disableOSVersion")){
            trackingOptions.disableOsVersion();
        }
        if (optionsList.contains("disablePlatform")){
            trackingOptions.disablePlatform();
        }
        if (optionsList.contains("disableRegion")){
            trackingOptions.disableRegion();
        }
        if (optionsList.contains("disableVersionName")){
            trackingOptions.disableVersionName();
        }
        Amplitude.getInstance().setTrackingOptions(trackingOptions);
    }

    @ReactMethod void setUserPropertyForOnce(String key, String value){
        Identify identify = new Identify().setOnce(key, value);
        Amplitude.getInstance().identify(identify);
    }

    @ReactMethod void unSetUserProperty(String key){
        Identify identify = new Identify().unset(key);
        Amplitude.getInstance().identify(identify);
    }

    @ReactMethod void setDeviceId(String deviceId){
        Amplitude.getInstance().setDeviceId(deviceId);
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
