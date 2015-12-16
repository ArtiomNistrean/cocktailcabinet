package com.artiomnist.cocktailcabinet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Context mContext;

    private ViewPager pager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));


    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        onSectionAttached(position);

    }

    public void onSectionAttached(int number) {
        String file = "file:///android_asset/Cocktails/";
        WebViewFragment fragment;
        switch (number) {
            default:
            case 0:
                file = file + "mojito.html";
                mTitle = getString(R.string.cocktail1);
                fragment = CocktailContentFragment.newInstance(file);
                break;
            case 1:
                file = file + "long-island-iced-tea.html";
                mTitle = getString(R.string.cocktail2);
                fragment = CocktailContentFragment.newInstance(file);
                break;
            case 2:
                file = file + "hendricks-smash.html";
                mTitle = getString(R.string.cocktail3);
                fragment = CocktailContentFragment.newInstance(file);
                break;
            case 3:
                file = file + "blue-lagoon.html";
                mTitle = getString(R.string.cocktail4);
                fragment = CocktailContentFragment.newInstance(file);
                break;
            case 4:
                file = file + "old-fashioned.html";
                mTitle = getString(R.string.cocktail5);
                fragment = CocktailContentFragment.newInstance(file);
                break;
        }

        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // there is a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return(true);
            case R.id.help:
                i = new Intent(this, HelpActivity.class).putExtra(HelpActivity.EXTRA_FILE,
                        "file:///android_asset/Misc/help.html");
                startActivity(i);
                return(true);
        }

        return super.onOptionsItemSelected(item);
    }
}
