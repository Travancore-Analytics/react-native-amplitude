import { NativeModules} from "react-native";

const { AmplitudeAnalytics } = NativeModules;

export default {
  initialize(apiKey,rhbName) {
    return AmplitudeAnalytics.initialize(apiKey,rhbName);
  },
  logEvent(eventName,parameters) {
    return AmplitudeAnalytics.logEvent(eventName,parameters);
  }
};
