package com.neartox.retrofitwithrecyclerview.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.facebook.drawee.view.SimpleDraweeView;
import com.neartox.retrofitwithrecyclerview.R;
import com.neartox.retrofitwithrecyclerview.loaders.UserLoader;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrackInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrackInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackInfoFragment extends Fragment {
  private static final String ARG_USERNAME = "UserName";
  public SimpleDraweeView mImage;
  public TextView mName;
  public TextView mUsername;
  public TextView mLocation;
  public TextView mFollowersTxt;
  public TextView mFollowers;

  // TODO: Rename and change types of parameters
  public String UserName;

  private OnFragmentInteractionListener mListener;

  public TrackInfoFragment() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static TrackInfoFragment newInstance(String username) {
    TrackInfoFragment fragment = new TrackInfoFragment();
    Bundle args = new Bundle();
    args.putString(ARG_USERNAME, username);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getArguments() != null) {
      UserName = getArguments().getString(ARG_USERNAME);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_track_info, container, false);
    mImage = view.findViewById(R.id.track_img_big);
    mName = view.findViewById(R.id.track_name_);
    mUsername = view.findViewById(R.id.track_username_);
    mLocation = view.findViewById(R.id.track_location);
    mFollowers = view.findViewById(R.id.track_followers_);
    mFollowersTxt = view.findViewById(R.id.track_followers_txt);
    if(UserName != null) {
      mUsername.setText(UserName);
      UserLoader loader = new UserLoader(this);
      loader.execute();
    }
    return view;
  }

  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if(mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if(context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onSaveInstanceState(final Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(ARG_USERNAME, UserName);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    if(savedInstanceState != null) {
      //probably orientation change
      UserName = savedInstanceState.getString(ARG_USERNAME);
    } else {
      if(UserName != null) {
        //returning from backstack, data is fine, do nothing
      } else {
        //newly created, compute data
        UserName = "JakeWharton";
      }
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
