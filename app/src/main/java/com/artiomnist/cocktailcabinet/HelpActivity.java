package com.artiomnist.cocktailcabinet;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created on 29/10/2015.
 */
public class HelpActivity extends Activity {
    public static final String EXTRA_FILE = "file:///android_asset/Misc/help.html";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            String file = getIntent().getStringExtra(EXTRA_FILE);
            Fragment f = HelpContentFragment.newInstance(file);
            getFragmentManager().beginTransaction().add(android.R.id.content, f).commit();
        }
    }
}

