package com.neartox.retrofitwithrecyclerview.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.neartox.retrofitwithrecyclerview.MyToast;
import com.neartox.retrofitwithrecyclerview.R;
import com.neartox.retrofitwithrecyclerview.beans.Track;
import com.neartox.retrofitwithrecyclerview.fragment.TrackFragment;
import com.neartox.retrofitwithrecyclerview.fragment.TrackInfoFragment;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
    implements TrackFragment.OnListFragmentInteractionListener, TrackInfoFragment.OnFragmentInteractionListener {
  TrackFragment mTrackF;

  static String cookies = "";

  public class UserAgentInterceptor implements Interceptor {
    private final String userAgent;

    public UserAgentInterceptor(String userAgent) {
      this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
      Request originalRequest = chain.request();
      Request requestWithUserAgent = originalRequest.newBuilder()
          .header("User-Agent", userAgent)
          .header("Cookie", MainActivity.cookies)
          .build();
      return chain.proceed(requestWithUserAgent);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    OkHttpClient okHttpClient = new OkHttpClient
        .Builder()
        .addNetworkInterceptor(new UserAgentInterceptor("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0"))
        .build();

    ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
        .newBuilder(this, okHttpClient)
        .build();

    Fresco.initialize(this, config);

    super.onCreate(savedInstanceState);

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