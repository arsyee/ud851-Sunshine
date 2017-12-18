package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

// COMPLETED (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    // Do steps 5 - 11 within SettingsFragment

    // COMPLETED (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
    private void setPreferenceSummary(Preference preference, Object object) {
        preference.setSummary(object.toString());
    }

    // COMPLETED (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);
        // Do step 9 within onCreatePreference
        // COMPLETED (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 0; i < preferenceScreen.getPreferenceCount(); ++i) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof EditTextPreference) {
                setPreferenceSummary(preference, ((EditTextPreference)preference).getText());
            }
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue((listPreference.getValue()));
                setPreferenceSummary(preference, getResources().getStringArray(R.array.pref_units_options)[index]);
            }
        }
    }

    // COMPLETED (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
    @Override
    public void onStop() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    // COMPLETED (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_location_key))) {
            setPreferenceSummary(findPreference(key), ((EditTextPreference)findPreference(key)).getText());
        }
        if (key.equals(getString(R.string.pref_units_key))) {
            ListPreference listPreference = (ListPreference) findPreference(key);
            int index = listPreference.findIndexOfValue((listPreference.getValue()));
            setPreferenceSummary(findPreference(key), getResources().getStringArray(R.array.pref_units_options)[index]);
        }
    }

}
