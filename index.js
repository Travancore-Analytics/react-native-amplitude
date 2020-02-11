import { NativeModules} from "react-native";

const { AmplitudeAnalytics } = NativeModules;

export default {
  initialize(apiKey) {
    return AmplitudeAnalytics.initialize(apiKey);
  }, 
  setUserPropertyForOnce(key,value){
    return AmplitudeAnalytics.setUserPropertyForOnce(key,value);
  },
  logEvent(eventName,parameters) {
    return AmplitudeAnalytics.logEvent(eventName,parameters);
  }
};
