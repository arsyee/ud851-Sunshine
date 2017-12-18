package com.example.android.sunshine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Do step 2 in SettingsActivity
        // COMPLETED (2) Set setDisplayHomeAsUpEnabled to true on the support ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
