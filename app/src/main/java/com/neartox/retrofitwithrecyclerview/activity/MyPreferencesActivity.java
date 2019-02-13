package com.neartox.retrofitwithrecyclerview.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.neartox.retrofitwithrecyclerview.R;
import com.neartox.retrofitwithrecyclerview.fragment.MyPreferencesFragment;

public class MyPreferencesActivity extends AppCompatActivity {
    private MyPreferencesFragment mSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preferences);
        if (null == savedInstanceState) {
            mSetting = new MyPreferencesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content, mSetting);
            fragmentTransaction.commit();
        }
    }
}
