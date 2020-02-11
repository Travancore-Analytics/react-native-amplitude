package com.amplitude;


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
        com.amplitude.api.Amplitude.getInstance().initialize(getReactApplicationContext().getApplicationContext(), apiKey)
                .enableForegroundTracking(getCurrentActivity().getApplication());
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
