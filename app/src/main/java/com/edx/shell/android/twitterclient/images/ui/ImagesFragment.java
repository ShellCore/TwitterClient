package com.edx.shell.android.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.entities.Image;
import com.edx.shell.android.twitterclient.images.ImagesPresenter;
import com.edx.shell.android.twitterclient.images.adapters.ImagesAdapter;
import com.edx.shell.android.twitterclient.images.adapters.OnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {

    // Servicios
    ImagesPresenter presenter;
    ImagesAdapter adapter;

    // Componentes
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.frm_container)
    FrameLayout frmContainer;

    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        return view;
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
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
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
    public void setContents(List<Image> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
