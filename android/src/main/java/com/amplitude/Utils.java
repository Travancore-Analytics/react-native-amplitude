
package com.amplitude;

import android.text.TextUtils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Utils {

    private static Object readableMapToObject(ReadableMap readableMap, String key) {
        if (readableMap == null || TextUtils.isEmpty(key)) {
            return null;
        }

        Object object;

        ReadableType readableType = readableMap.getType(key);
        switch (readableType) {
            case Null:
                object = key;
                break;
            case Boolean:
                object = readableMap.getBoolean(key);
                break;
            case Number:
                // int or double.
                double tmp = readableMap.getDouble(key);
                if (tmp == (int) tmp) {
                    object = (int) tmp;
                } else {
                    object = tmp;
                }
                break;
            case String:
                object = readableMap.getString(key);
                break;
            case Map:
                object = readableMapToMap(readableMap.getMap(key));
                break;
            case Array:
                object = readableArrayToList(readableMap.getArray(key));
                break;
            default:
                throw new IllegalArgumentException("Failed to convert to object with key: " + key + ".");
        }

        return object;
    }

    static Map<String, Object> readableMapToMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }

        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        if (!iterator.hasNextKey()) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            map.put(key, readableMapToObject(readableMap, key));
        }

        return map;
    }

    private static List<Object> readableArrayToList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        List<Object> list = new ArrayList<>(readableArray.size());
        for (int index = 0; index < readableArray.size(); index++) {
            ReadableType readableType = readableArray.getType(index);
            switch (readableType) {
                case Null:
                    list.add(String.valueOf(index));
                    break;
                case Boolean:
                    list.add(readableArray.getBoolean(index));
                    break;
                case Number:
                    // int or double.
                    double temp = readableArray.getDouble(index);
                    if (temp == (int) temp) {
                        list.add((int) temp);
                    } else {
                        list.add(temp);
                    }
                    break;
                case String:
                    list.add(readableArray.getString(index));
                    break;
                case Map:
                    list.add(readableMapToMap(readableArray.getMap(index)));
                    break;
                case Array:
                    list = readableArrayToList(readableArray.getArray(index));
                    break;
                default:
                    throw new IllegalArgumentException("Failed to convert object with index: " + index + ".");
            }
        }

        return list;
    }

    static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
        WritableMap map = new WritableNativeMap();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                map.putMap(key, convertJsonToMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.putArray(key, convertJsonToArray((JSONArray) value));
                if(("option_values").equals(key)) {
                    map.putArray("options", convertJsonToArray((JSONArray) value));
                }
            } else if (value instanceof Boolean) {
                map.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                map.putInt(key, (Integer) value);
            } else if (value instanceof Double) {
                map.putDouble(key, (Double) value);
            } else if (value instanceof String)  {
                map.putString(key, (String) value);
            } else {
                map.putString(key, value.toString());
            }
        }
        return map;
    }

    static WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
        WritableArray array = new WritableNativeArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                array.pushMap(convertJsonToMap((JSONObject) value));
            } else if (value instanceof  JSONArray) {
                array.pushArray(convertJsonToArray((JSONArray) value));
            } else if (value instanceof  Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof  Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof  Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof String)  {
                array.pushString((String) value);
            } else {
                array.pushString(value.toString());
            }
        }
        return array;
    }

    static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject object = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    object.put(key, JSONObject.NULL);
                    break;
                case Boolean:
                    object.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    object.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    object.put(key, readableMap.getString(key));
                    break;
                case Map:
                    object.put(key, convertMapToJson(readableMap.getMap(key)));
                    break;
                case Array:
                    object.put(key, convertArrayToJson(readableMap.getArray(key)));
                    break;
            }
        }
        return object;
    }

    static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray array = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    break;
                case Boolean:
                    array.put(readableArray.getBoolean(i));
                    break;
                case Number:
                    array.put(readableArray.getDouble(i));
                    break;
                case String:
                    array.put(readableArray.getString(i));
                    break;
                case Map:
                    array.put(convertMapToJson(readableArray.getMap(i)));
                    break;
                case Array:
                    array.put(convertArrayToJson(readableArray.getArray(i)));
                    break;
            }
        }
        return array;
    }

}
