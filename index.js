import { NativeModules} from "react-native";

const { AmplitudeAnalytics } = NativeModules;

export default {
  initialize(apiKey) {
    return AmplitudeAnalytics.initialize(apiKey);
  }, 
  logEvent(eventName,parameters) {
    return AmplitudeAnalytics.logEvent(eventName,parameters);
  }
};
