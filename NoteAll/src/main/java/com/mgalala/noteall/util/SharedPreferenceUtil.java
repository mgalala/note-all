package com.mgalala.noteall.util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by galala on 12/12/13.
 */
public class SharedPreferenceUtil {

    public static void savePreferences(String key, String value, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences("MY_SHARED_PREF", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String loadPreferences(String key, Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences("MY_SHARED_PREF", activity.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
}
