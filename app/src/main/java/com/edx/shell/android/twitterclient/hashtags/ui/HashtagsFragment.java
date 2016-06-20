package com.edx.shell.android.twitterclient.hashtags.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.TwitterClientApp;
import com.edx.shell.android.twitterclient.entities.Hashtag;
import com.edx.shell.android.twitterclient.hashtags.HashtagsPresenter;
import com.edx.shell.android.twitterclient.hashtags.adapters.HashtagsAdapter;
import com.edx.shell.android.twitterclient.hashtags.adapters.OnHashtagItemClickListener;
import com.edx.shell.android.twitterclient.hashtags.dependence_injection.HashtagsComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HashtagsFragment extends Fragment implements HashtagsView, OnHashtagItemClickListener {

    // Servicios
    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter presenter;

    // Componentes
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.frm_container)
    FrameLayout frmContainer;

    public HashtagsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, v);
        setupInjection();
        setupRecyclerView();
        presenter.getHashtagTweets();
        return v;
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        HashtagsComponent hashtagsComponent = app.getHashtagsComponent(this, this);
        hashtagsComponent.inject(this);
    }

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showHashtags() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(frmContainer, error, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetUrl()));
        startActivity(intent);
    }
}
