package com.edx.shell.android.twitterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edx.shell.android.twitterclient.LoginActivity;
import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.hashtags.HashtagsFragment;
import com.edx.shell.android.twitterclient.images.ui.ImagesFragment;
import com.edx.shell.android.twitterclient.main.ui.adapters.MainSectionsPagerAdapter;
import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Componentes
    @Bind(R.id.tbr_main)
    Toolbar tbrMain;
    @Bind(R.id.tbs_main)
    TabLayout tbsMain;
    @Bind(R.id.abl_main)
    AppBarLayout ablMain;
    @Bind(R.id.vpg_main)
    ViewPager viewPager;
    @Bind(R.id.coo_main)
    CoordinatorLayout cooMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(tbrMain);

        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{
                new ImagesFragment(),
                new HashtagsFragment()
        };
        String[] titles = new String[]{getString(R.string.main_title_images), getString(R.string.main_title_hashtags)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tbsMain.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}