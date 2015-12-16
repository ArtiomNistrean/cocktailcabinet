package com.artiomnist.cocktailcabinet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created on 27/10/2015.
 */
public class AboutActivity extends Activity {
    private static final String DEV_CONTACT = "http://www.artiomnist.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {

            Fragment f = new AboutFragment();

            getFragmentManager().beginTransaction().add(android.R.id.content, f).commit();

        }
    }

    public void buttonOnclick(View v) {
        Button button = (Button) v;

        startActivity(goToUrl(DEV_CONTACT));


    }

    public Intent goToUrl (String url){
        Uri link = Uri.parse(url);
        Intent browserLaunch = new Intent(Intent.ACTION_VIEW, link);

        return browserLaunch;

    }

}
