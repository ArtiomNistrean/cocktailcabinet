package com.artiomnist.cocktailcabinet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

/**
 * Created on 29/10/2015.
 */
public class HelpContentFragment extends WebViewFragment {
    private static final String HELP_FILE="file:///android_asset/Misc/help.html";

    protected static HelpContentFragment newInstance(String file) {
        HelpContentFragment cf = new HelpContentFragment();

        Bundle args = new Bundle();
        args.putString(HELP_FILE, file);
        cf.setArguments(args);

        return(cf);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateView(inflater, container, savedInstanceState);

        getWebView().getSettings().setJavaScriptEnabled(true);
        getWebView().getSettings().setSupportZoom(true);
        getWebView().getSettings().setBuiltInZoomControls(true);
        getWebView().loadUrl(HELP_FILE);

        return(result);
    }

}
