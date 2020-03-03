import { NativeModules} from "react-native";

const { AmplitudeAnalytics } = NativeModules;

export default {
  initialize(apiKey) {
    return AmplitudeAnalytics.initialize(apiKey);
  }, 
  setUserPropertyForOnce(key,value){
    return AmplitudeAnalytics.setUserPropertyForOnce(key,value);
  },
  disableTrackingOptions(){
    return AmplitudeAnalytics.disableTrackingOptions();
  },
  unSetUserProperty(key){
    return AmplitudeAnalytics.unSetUserProperty(key);
  },
  logEvent(eventName,parameters) {
    return AmplitudeAnalytics.logEvent(eventName,parameters);
  }
};
