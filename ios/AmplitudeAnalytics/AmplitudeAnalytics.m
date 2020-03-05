//  Created by react-native-create-bridge

#import <UIKit/UIKit.h>
#import "AmplitudeAnalytics.h"
// import RCTBridge
#if __has_include(<React/RCTBridge.h>)
#import <React/RCTBridge.h>
#elif __has_include(“RCTBridge.h”)
#import “RCTBridge.h”
#else
#import “React/RCTBridge.h” // Required when used as a Pod in a Swift project
#endif

// import RCTEventDispatcher
#if __has_include(<React/RCTEventDispatcher.h>)
#import <React/RCTEventDispatcher.h>
#elif __has_include(“RCTEventDispatcher.h”)
#import “RCTEventDispatcher.h”
#else
#import “React/RCTEventDispatcher.h” // Required when used as a Pod in a Swift project
#endif
#import "Amplitude.h"
#import "AMPTrackingOptions.h"

@interface AmplitudeAnalytics ()
{
    
}
@end
@implementation AmplitudeAnalytics
@synthesize bridge = _bridge;

// Export a native module
// https://facebook.github.io/react-native/docs/native-modules-ios.html
RCT_EXPORT_MODULE();

// Export methods to a native module
// https://facebook.github.io/react-native/docs/native-modules-ios.html

RCT_EXPORT_METHOD(initialize:(NSString *)apiKey){
    [[Amplitude instance] initializeApiKey:apiKey];
}

RCT_EXPORT_METHOD(setTrackingOptions:(NSArray *)options){
    AMPTrackingOptions *trackingOptions = [AMPTrackingOptions options];
    if([options containsObject:@"disableCarrier"]){
        [trackingOptions disableCarrier];
    }
    if([options containsObject:@"disableCity"]){
        [trackingOptions disableCity];
    }
    if([options containsObject:@"disableCountry"]){
        [trackingOptions disableCountry];
    }
    if([options containsObject:@"disableDeviceManufacturer"]){
        [trackingOptions disableDeviceManufacturer];
    }
    if([options containsObject:@"disableDeviceModel"]){
        [trackingOptions disableDeviceModel];
    }
    if([options containsObject:@"disableDMA"]){
        [trackingOptions disableDMA];
    }
    if([options containsObject:@"disableIDFA"]){
        [trackingOptions disableIDFA];
    }
    if([options containsObject:@"disableIDFV"]){
        [trackingOptions disableIDFV];
    }
    if([options containsObject:@"disableIPAddress"]){
        [trackingOptions disableIPAddress];
    }
    if([options containsObject:@"disableLanguage"]){
        [trackingOptions disableLanguage];
    }
    if([options containsObject:@"disableLatLng"]){
        [trackingOptions disableLatLng];
    }
    if([options containsObject:@"disableOSName"]){
        [trackingOptions disableOSName];
    }
    if([options containsObject:@"disableOSVersion"]){
        [trackingOptions disableOSVersion];
    }
    if([options containsObject:@"disablePlatform"]){
        [trackingOptions disablePlatform];
    }
    if([options containsObject:@"disableRegion"]){
        [trackingOptions disableOSVersion];
    }
    if([options containsObject:@"disableVersionName"]){
        [trackingOptions disablePlatform];
    }
    [[Amplitude instance] setTrackingOptions:trackingOptions];
}

RCT_EXPORT_METHOD(setUserPropertyForOnce:(NSString *)key value:(NSString *)value){
    AMPIdentify *identify = [[AMPIdentify identify] setOnce:key value:value];
    [[Amplitude instance] identify:identify];
}

RCT_EXPORT_METHOD(unSetUserProperty:(NSString *)key){
    AMPIdentify *identify = [[AMPIdentify identify] unset:key];
    [[Amplitude instance] identify:identify];
}

RCT_EXPORT_METHOD(logEvent:(NSString *) eventName params:(NSDictionary *)params){
    [[Amplitude instance] logEvent:eventName withEventProperties:params];
}
- (NSArray<NSString *> *)supportedEvents
{
    return @[];
}


@end
