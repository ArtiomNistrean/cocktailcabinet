package com.artiomnist.cocktailcabinet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

/**
 * Created on 27/10/2015.
 */
public class CocktailContentFragment extends WebViewFragment {
    private static final String COCKTAIL="file";

    protected static CocktailContentFragment newInstance(String file) {
        CocktailContentFragment cf = new CocktailContentFragment();

        Bundle args = new Bundle();
        args.putString(COCKTAIL, file);
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
        getWebView().getSettings().setBuiltInZoomControls(false);
        getWebView().loadUrl(getCocktail());

        return(result);
    }

    private String getCocktail() {
        return(getArguments().getString(COCKTAIL));
    }

}
