package com.com.blog.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String PREFERENCES_NAME = "auth_preference";

    public static final String PREF_TOKEN = "authToken";

    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    public PreferenceManager(Context context) {
        pref = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        prefEditor = pref.edit();
    }

    public void setString(String key, String value) {
        prefEditor.putString(key,value);
        prefEditor.commit();
    }

    public String getToken() {
        return pref.getString(PREF_TOKEN, "");
    }
}
