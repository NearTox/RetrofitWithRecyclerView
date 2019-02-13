package com.neartox.retrofitwithrecyclerview.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.neartox.retrofitwithrecyclerview.MyToast;
import com.neartox.retrofitwithrecyclerview.R;
import com.neartox.retrofitwithrecyclerview.beans.Track;
import com.neartox.retrofitwithrecyclerview.fragment.TrackFragment;
import com.neartox.retrofitwithrecyclerview.fragment.TrackInfoFragment;

public class MainActivity extends AppCompatActivity
    implements TrackFragment.OnListFragmentInteractionListener, TrackInfoFragment.OnFragmentInteractionListener {
  TrackFragment mTrackF;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fresco.initialize(this);
    setContentView(R.layout.activity_main);
    if(null == savedInstanceState) {
      mTrackF = new TrackFragment();
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(android.R.id.content, mTrackF);
      fragmentTransaction.commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    /*return */
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    /*return */
    super.onOptionsItemSelected(item);
    int id = item.getItemId();
    if(id == R.id.main_menu_op1) {
      MyToast.ShowToast(item.toString(), this);
    } else {
      MyToast.ShowToast(item.toString(), this);
    }
    return true;
  }

  @Override
  public void onListFragmentInteraction(Track item) {
    FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
    ftrans.replace(android.R.id.content, TrackInfoFragment.newInstance(item.Name));
    ftrans.addToBackStack(null);
    ftrans.commit();

    MyToast.ShowToast(item.toString(), this);
  }

  @Override
  public void onFragmentInteraction(Uri uri) {
    MyToast.ShowToast(uri.toString(), this);
  }
}