package com.khelkund.privateleague.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.khelkund.privateleague.R;
import com.khelkund.privateleague.infra.APCallback;
import com.khelkund.privateleague.infra.Http;
import com.khelkund.privateleague.infra.Urls;
import com.khelkund.privateleague.infra.Vars;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class CreatePrivateLeagueActivity extends ActionBarActivity {

    private EditText mName;
    private EditText mPassword;
    private Button mConfirm;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_private_league);
        mContext = this;
        mName = (EditText) findViewById(R.id.create_name);
        mPassword = (EditText) findViewById(R.id.create_password);
        mConfirm = (Button) findViewById(R.id.create_confirm);
        mConfirm.setOnClickListener(confirmCreateLeagueClick);
    }

    View.OnClickListener confirmCreateLeagueClick = new View.OnClickListener() {
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
            CreateLeague();
        }
    };

    private void CreateLeague()
    {
        JSONObject payload = new JSONObject();
        try {
            payload.put("PrivateLeagueName", mName.getText().toString());
            payload.put("Password", mPassword.getText().toString());
        }
        catch (JSONException e)
        {
            throw new RuntimeException(e.getMessage());
        }
        Http http = new Http(mContext);
        http.post(Urls.CreatePrivateLeagueUrl, new HashMap<String, String>(){{put("Cookie", Vars.COOKIE);}}, payload, new APCallback() {
            @Override
            public void success(JSONObject result) {
                Intent privateLeagueHomeIntent = new Intent(mContext, PrivateLeagueHomeActivity.class);
                startActivity(privateLeagueHomeIntent);
            }

            @Override
            public void failure(Exception e) {
                Log.d("khelkund", e.getMessage());
            }
        });
    }
}
