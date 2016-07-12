package com.neartox.retrofitwithrecyclerview.loaders;

import android.os.AsyncTask;

import com.neartox.retrofitwithrecyclerview.beans.Track;
import com.neartox.retrofitwithrecyclerview.fragment.MyTrackRecyclerViewAdapter;
import com.neartox.retrofitwithrecyclerview.fragment.TrackFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class TrackLoader extends AsyncTask<Void, Void, Void> {
    public static final String API_URL = "https://api.github.com";

    private MyTrackRecyclerViewAdapter mMyTrackRecyclerViewAdapter;

    public TrackLoader(MyTrackRecyclerViewAdapter mMyTrackRecyclerViewAdapter) {
        this.mMyTrackRecyclerViewAdapter = mMyTrackRecyclerViewAdapter;
    }

    public static class Contributor {
        public final String login;
        public final int id;

        public Contributor(String login, int id) {
            this.login = login;
            this.id = id;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        List<Track> list = new ArrayList<Track>();
        //list.add(new Track("1","Tack","2","Author"));

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = null;
        try {
            contributors = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(contributors != null) {
            for (Contributor contributor : contributors) {
                list.add(new Track("1", contributor.login, "1", String.valueOf(contributor.id)));
                //System.out.println(contributor.login + " (" + contributor.contributions + ")");
            }
        }
        mMyTrackRecyclerViewAdapter.mValues = list;
        //return list;
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        mMyTrackRecyclerViewAdapter.notifyDataSetChanged();
    }

}
