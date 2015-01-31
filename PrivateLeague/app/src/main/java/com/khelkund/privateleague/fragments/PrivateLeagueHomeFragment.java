package com.khelkund.privateleague.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khelkund.privateleague.R;
import com.khelkund.privateleague.activities.CreatePrivateLeagueActivity;
import com.khelkund.privateleague.activities.JoinPrivateLeagueActivity;
import com.khelkund.privateleague.adapters.PrivateLeagueListAdapter;
import com.khelkund.privateleague.infra.APCallback;
import com.khelkund.privateleague.infra.Http;
import com.khelkund.privateleague.infra.Urls;
import com.khelkund.privateleague.infra.Vars;
import com.khelkund.privateleague.model.PrivateLeague;
import com.khelkund.privateleague.model.UserTeam;
import com.shamanland.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrivateLeagueHomeFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;
    private TextView mJoin;
    public PrivateLeagueHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View privateLeaguesHomeFragment = inflater.inflate(R.layout.fragment_private_league_home, container, false);
        fab = (FloatingActionButton) privateLeaguesHomeFragment.findViewById(R.id.fab);
        fab.setOnClickListener(createLeagueClick);
        mJoin = (TextView) privateLeaguesHomeFragment.findViewById(R.id.home_join_league);
        mJoin.setOnClickListener(joinLeagueClick);
        mRecyclerView = (RecyclerView) privateLeaguesHomeFragment.findViewById(R.id.list_private_league);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        fetchAndDisplayPrivateLeagues(mRecyclerView);
        return privateLeaguesHomeFragment;
    }

    View.OnClickListener createLeagueClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent createLeagueActivityIntent = new Intent(getActivity(), CreatePrivateLeagueActivity.class);
            startActivity(createLeagueActivityIntent);
        }
    };

    View.OnClickListener joinLeagueClick = new View.OnClickListener() {
        public void onClick(View v) {
            Intent joinLeagueActivityIntent = new Intent(getActivity(), JoinPrivateLeagueActivity.class);
            startActivity(joinLeagueActivityIntent);
        }
    };

    private void fetchAndDisplayPrivateLeagues(final RecyclerView recyclerView)
    {
        Http http = new Http(getActivity());
        final List<PrivateLeague> privateLeagues = new ArrayList<PrivateLeague>();
        http.get(Urls.GetPrivateLeaguesUrl, new HashMap<String, String>(){{put("Cookie", Vars.COOKIE);}}, new APCallback() {
            @Override
            public void success(JSONObject result) {
                try {
                    JSONArray privateLeaguesJson = result.optJSONArray("PrivateLeagues");
                    if(privateLeaguesJson == null) return;

                    for(int i = 0; i < privateLeaguesJson.length(); i++)
                    {
                        JSONObject privateLeagueJson = privateLeaguesJson.optJSONObject(i);
                        if(privateLeagueJson == null)
                            continue;

                        PrivateLeague privateLeague = new PrivateLeague();
                        privateLeague.id = privateLeagueJson.getString("PrivateLeagueId");
                        privateLeague.name = privateLeagueJson.getString("PrivateLeagueName");
                        privateLeague.userTeams = new ArrayList<UserTeam>();

                        JSONArray userTeamsJson = privateLeagueJson.optJSONArray("UserTeams");
                        if(userTeamsJson == null)
                            continue;
                        for(int j = 0; j < userTeamsJson.length(); j++)
                        {
                            JSONObject userTeamJson = userTeamsJson.optJSONObject(j);
                            if(userTeamJson == null)
                                continue;
                            UserTeam userTeam = new UserTeam();
                            userTeam.id = userTeamJson.getString("UserTeamId");
                            userTeam.name = userTeamJson.getString("UserTeamName");
                            userTeam.rank = userTeamJson.getInt("Rank");
                            userTeam.points = userTeamJson.getInt("Points");
                            userTeam.logoUrl = userTeamJson.getString("LogoUrl");
                            userTeam.username = userTeamJson.getString("UserName");

                            privateLeague.userTeams.add(userTeam);
                        }
                        privateLeagues.add(privateLeague);
                    }

                    mAdapter = new PrivateLeagueListAdapter(getActivity(), privateLeagues);
                    recyclerView.setAdapter(mAdapter);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e.getMessage());
                }

            }

            @Override
            public void failure(Exception e) {
                Log.d("khelkund", e.getMessage());
            }
        });
    }
}
