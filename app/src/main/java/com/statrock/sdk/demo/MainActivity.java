package com.statrock.sdk.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View navHeaderSubTitle = navigationView.getHeaderView(0).findViewById(R.id.navHeaderSubTitle);
        if (navHeaderSubTitle != null) {
            navHeaderSubTitle.setVisibility(BuildConfig.DEBUG ? View.VISIBLE : View.GONE);
        }
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            MenuItem inViewFragmentMenuItem = findMenuItem(navigationView.getMenu(), R.id.nav_inpage);
            if (inViewFragmentMenuItem != null) {
                onNavigationItemSelected(inViewFragmentMenuItem);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int fragmentLayoutId;
        Class<?> fragmentClass = StatRockInPageFragment.class;
        switch (menuItem.getItemId()) {
            case R.id.nav_recycler_view:
                fragmentLayoutId = R.layout.fragment_recycler_view;
                fragmentClass = StatRockInRecyclerViewFragment.class;
                break;
            case R.id.nav_inview:
                fragmentLayoutId = R.layout.fragment_inview;
                fragmentClass = StatRockInViewFragment.class;
                break;
            default:
                fragmentLayoutId = R.layout.fragment_inpage;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        Method method;
        try {
            method = fragmentClass.getMethod("newInstance", int.class);
            fragment = (Fragment) method.invoke(null, fragmentLayoutId);
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        unCheckAllMenuItems(navigationView.getMenu());
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void unCheckAllMenuItems(@NonNull final Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                // Un check sub menu items
                unCheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    private MenuItem findMenuItem(@NonNull final Menu menu, int itemId) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                // Un check sub menu items
                return findMenuItem(item.getSubMenu(), itemId);
            } else {
                if (itemId == item.getItemId()) {
                    return item;
                }
            }
        }
        return null;
    }
}
