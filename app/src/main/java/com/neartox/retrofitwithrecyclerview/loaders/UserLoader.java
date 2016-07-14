package com.neartox.retrofitwithrecyclerview.loaders;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;

import com.neartox.retrofitwithrecyclerview.fragment.TrackInfoFragment;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class UserLoader extends AsyncTask<Void, Void, Void> {
    private Contributor mContributors = null;
    public static final String API_URL = "https://api.github.com";

    private TrackInfoFragment mInfo;

    public UserLoader(TrackInfoFragment mInfo) {
        this.mInfo = mInfo;
    }

    public static class Contributor {
        public final String login;
        public final int id;
        public final String avatar_url;
        public final String name;
        public final String location;
        public final String followers;

        public Contributor(String login, int id, String avatar_url, String name, String location, String followers) {
            this.login = login;
            this.id = id;
            this.avatar_url = avatar_url;
            this.name = name;
            this.location = location;
            this.followers = followers;
        }
    }

    public interface GitHub {
        @GET("/users/{owner}")
        Call<Contributor> user(@Path("owner") String owner);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<Contributor> call;
        if(mInfo!=null) {
            call= github.user(mInfo.UserName);
        }else{
            call= github.user("JakeWharton");
        }

        // Fetch and print a list of the contributors to the library.
        Contributor contributors = null;
        try {
            contributors = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contributors != null) {
            mContributors = contributors;
        }
        //return list;
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (mInfo != null) {
            mInfo.mImage.setImageURI(Uri.parse(mContributors.avatar_url));
            mInfo.mName.setText(mContributors.name);
            mInfo.mUsername.setText(mContributors.login);
            mInfo.mLocation.setText(mContributors.location);
            mInfo.mFollowers.setText(mContributors.followers);
            mInfo.mFollowersTxt.setVisibility(View.VISIBLE);
        }
    }
}
