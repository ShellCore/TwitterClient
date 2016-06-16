package com.edx.shell.android.twitterclient.hashtags.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edx.shell.android.twitterclient.R;

public class HashtagsFragment extends Fragment {

    public HashtagsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hashtags, container, false);
    }
}
