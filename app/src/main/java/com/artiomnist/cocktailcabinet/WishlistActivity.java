package com.artiomnist.cocktailcabinet;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created on 28/10/2015.
 */
public class WishlistActivity extends AppCompatActivity {

    private Context mContext;
    private DatabaseHandler db = new DatabaseHandler(this);
    private ListView wishlistView2;
    private CharSequence COCKTAIL_LIST[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.wishlist);

        COCKTAIL_LIST = generateCocktailList();

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        ListView wishlistView = new ListView(mContext);
        wishlistView2 = new ListView(mContext);

        Vector<View> pages = new Vector<View>();
        pages.add(wishlistView);
        pages.add(wishlistView2);


        WishlistPagerAdapter adapter = new WishlistPagerAdapter(mContext, pages);
        pager.setAdapter(adapter);

        // Initial Population of the Wishlist
        updateWishlist(wishlistView2);

        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {

            Fragment f = new WishlistFragment();

            getFragmentManager().beginTransaction().add(android.R.id.content, f).commit();

        }
    }

    public void addCocktailButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.wishlist_add_message)
                .setItems(R.array.cocktail_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String selection = (String) COCKTAIL_LIST[which];
                        db.addCocktailtoWishlist(selection);
                        Toast.makeText(mContext, "Added " + selection + " to your Wishlist", Toast.LENGTH_SHORT).show();
                        updateWishlist(wishlistView2);

                    }
                });

        builder.create().show();
    }

    public void removeCocktailButton(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (!db.getWishlist().isEmpty()) {

            builder.setTitle(R.string.wishlist_add_message)
                    .setItems(refineList(db.getWishlist()), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            String selection = (String) refineList(db.getWishlist())[which];

                            if (db.getWishlist().contains(selection)) {
                                db.deleteCocktail(selection);
                                Toast.makeText(mContext, "Removed " + selection + " " +
                                        "From your Wishlist", Toast.LENGTH_SHORT).show();
                                updateWishlist(wishlistView2);
                            }

                        }
                    });

            builder.create().show();
        } else {
            Toast.makeText(mContext, "Nothing to Remove", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateWishlist(ListView wishlist) {
        List<String> theWishList = db.getWishlist();
        if (theWishList.isEmpty()) {
            wishlist.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,
                    new String[]{"It Looks Like Your Wishlist is Empty"}));
        } else {
            wishlist.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,
                    refineList(theWishList)));
        }
    }

    public String[] refineList(List<String> rawWishlist) {
        List<String> refinedList = new ArrayList<String>();
        for (int i = 0; i < rawWishlist.size(); i++) {
            if (!(refinedList.contains(rawWishlist.get(i)))){
                refinedList.add(rawWishlist.get(i));
            }
        }
        return refinedList.toArray(new String[refinedList.size()]);
    }

    public CharSequence[] generateCocktailList() {
        CharSequence drinksList[] = {
                getString(R.string.cocktail1),
                getString(R.string.cocktail2),
                getString(R.string.cocktail3),
                getString(R.string.cocktail4),
                getString(R.string.cocktail5)};
        return drinksList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.global, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as dev specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.about:
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return(true);
            case R.id.help:
                i = new Intent(this, HelpActivity.class).putExtra(HelpActivity.EXTRA_FILE, "file:///android_asset/Misc/help.html");
                startActivity(i);
                return(true);
        }

        return super.onOptionsItemSelected(item);
    }

}
