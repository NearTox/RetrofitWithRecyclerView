package com.neartox.retrofitwithrecyclerview.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.neartox.retrofitwithrecyclerview.MyToast;
import com.neartox.retrofitwithrecyclerview.R;
import com.neartox.retrofitwithrecyclerview.beans.Track;
import com.neartox.retrofitwithrecyclerview.fragment.TrackFragment;

public class MainActivity extends AppCompatActivity
implements TrackFragment.OnListFragmentInteractionListener {
    TrackFragment mTrackF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        if (mTrackF == null) {
            mTrackF = new TrackFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(android.R.id.content, mTrackF);
            //ft.add(R.id.contrainer, mTrackF);
            ft.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Track item) {
        MyToast.ShowToast(item.toString(), this);
    }
}