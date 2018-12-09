package com.bortnikov.artem.starwarwiki.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bortnikov.artem.starwarwiki.R;
import com.bortnikov.artem.starwarwiki.view.saved.SavedFragment;
import com.bortnikov.artem.starwarwiki.view.search.SearchFragment;

public class MainActivity extends SingleFragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    @Override
    protected Fragment createStartFragment() {
        return new SearchFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search: {
                placeFragment(SearchFragment.class.getName());
                break;
            }
            case R.id.nav_data_base: {
                placeFragment(SavedFragment.class.getName());
                break;
            }
            case R.id.nav_share: {
                share();
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void placeFragment(String fragmentTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment = Fragment.instantiate(this, fragmentTag, null);
        transaction.setCustomAnimations(
                android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_out, android.R.anim.fade_in);
        transaction.replace(R.id.container_fragments, fragment, fragmentTag);

        transaction.commit();
    }

    private void share() {
        Intent shareRecordIntent = new Intent(Intent.ACTION_SEND);
        shareRecordIntent.setType("text/plain");
        shareRecordIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_pattern));
        startActivity(Intent
                .createChooser(shareRecordIntent, getString(R.string.share_via)));
    }
}
