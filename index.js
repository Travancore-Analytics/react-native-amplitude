import { NativeModules} from "react-native";

const { AmplitudeAnalytics } = NativeModules;

export default {
  initialize(apiKey) {
    return AmplitudeAnalytics.initialize(apiKey);
  }, 
  setUserPropertyForOnce(key,value){
    return AmplitudeAnalytics.setUserPropertyForOnce(key,value);
  },
  setTrackingOptions(trackingOptions = []){
    return AmplitudeAnalytics.setTrackingOptions(trackingOptions);
  },
  unSetUserProperty(key){
    return AmplitudeAnalytics.unSetUserProperty(key);
  },
  logEvent(eventName,parameters) {
    return AmplitudeAnalytics.logEvent(eventName,parameters);
  },
  trackingOptions(){
    return {
      disableAdid                 : "disableAdid",
      disableCarrier              : "disableCarrier",
      disableCity                 : "disableCity",
      disableCountry              : "disableCountry",
      disableDeviceBrand          : "disableDeviceBrand",
      disableDeviceManufacturer   : "disableDeviceManufacturer",
      disableDeviceModel          : "disableDeviceModel",
      disableDMA                  : "disableDMA",
      disableIDFA                 : "disableIDFA",
      disableIDFV                 : "disableIDFV",
      disableIpAddress            : "disableIpAddress",
      disableLanguage             : "disableLanguage",
      disableLatLng               : "disableLatLng",
      disableOSName               : "disableOSName",
      disableOSVersion            : "disableOSVersion",
      disablePlatform             : "disablePlatform",
      disableRegion               : "disableRegion",
      disableVersionName          : "disableVersionName",
    }
  }
};
