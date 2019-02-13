package com.neartox.retrofitwithrecyclerview.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.neartox.retrofitwithrecyclerview.R;

public class MyPreferencesFragment extends PreferenceFragmentCompat {
  @Override
  public void onCreatePreferences(Bundle bundle, String s) {
    //super.onCreatePreferences(bundle, s);
    addPreferencesFromResource(R.xml.preferences_xml);

  }
}
