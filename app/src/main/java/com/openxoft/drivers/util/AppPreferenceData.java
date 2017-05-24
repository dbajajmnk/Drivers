package com.openxoft.drivers.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by openxoft on 19/05/17.
 */

public class AppPreferenceData {

    public static  void saveStringData(Context context, String fileName, String key, String value)
    {
      SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();

    }
    public static void saveInt(Context context,String fileName,String key,int value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public static  void saveFloat(Context context, String fileName, String key, float value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat(key,value);
        editor.commit();

    }
    public static void saveLong(Context context,String fileName,String key,long value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(key,value);
        editor.commit();
    }
    public static String getString(Context context,String fileName,String key)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);

    }

    public static int  getInt(Context context,String fileName,String key)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return  sharedPreferences.getInt(key,0);

    }

    public static float  getFloat(Context context,String fileName,String key)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return  sharedPreferences.getFloat(key,0);

    }
    public static long getDouble(Context context,String fileName,String key)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return  sharedPreferences.getLong(key,0);

    }
    public static void clearData(Context context,String fileName)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }
}
