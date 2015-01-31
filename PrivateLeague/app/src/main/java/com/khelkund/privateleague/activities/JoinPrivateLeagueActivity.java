package com.khelkund.privateleague.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.khelkund.privateleague.R;

public class JoinPrivateLeagueActivity extends ActionBarActivity {

    private EditText mName;
    private EditText mPassword;
    private Button mConfirm;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_private_league);
        mContext = this;
        mName = (EditText) findViewById(R.id.join_name);
        mPassword = (EditText) findViewById(R.id.join_password);
        mConfirm = (Button) findViewById(R.id.join_confirm);
        mConfirm.setOnClickListener(confirmJoinLeagueClick);
    }

    View.OnClickListener confirmJoinLeagueClick = new View.OnClickListener() {
        public void onClick(View v) {
            if(mName.getText().toString().isEmpty())
            {
                mName.setError("Private League should have a name.");
                return;
            }
            if(mPassword.getText().toString().isEmpty())
            {
                mPassword.setError("Private League should have a password.");
                return;
            }
            JoinLeague();
        }
    };

    private void JoinLeague() {

        // TODO add join league call

        Intent privateLeagueHomeIntent = new Intent(mContext, PrivateLeagueHomeActivity.class);
        startActivity(privateLeagueHomeIntent);
    }
}
