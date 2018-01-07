package com.example.android.sunshine.sync;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

// COMPLETED (1) Create a class called SunshineSyncTask
class SunshineSyncTask {
    private static final String TAG = SunshineSyncTask.class.getSimpleName();

    // COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context) {
        // COMPLETED (3) Within syncWeather, fetch new weather data
        URL openWeatherUrl = NetworkUtils.getUrl(context);
        try {
            // COMPLETED (4) If we have valid results, delete the old data and insert the new
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(openWeatherUrl);
            ContentValues[] values = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonResponse);
            Uri contentProviderUri = WeatherContract.WeatherEntry.CONTENT_URI;
            if (values != null) {
                Log.d(TAG, "Refreshing data from server!");
                context.getContentResolver().delete(contentProviderUri, null, null);
                context.getContentResolver().bulkInsert(contentProviderUri, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
