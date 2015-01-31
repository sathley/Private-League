package com.khelkund.privateleague.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.khelkund.privateleague.fragments.PrivateLeagueHomeFragment;
import com.khelkund.privateleague.R;


public class PrivateLeagueHomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PrivateLeagueHomeFragment())
                    .commit();
        }
    }
}
