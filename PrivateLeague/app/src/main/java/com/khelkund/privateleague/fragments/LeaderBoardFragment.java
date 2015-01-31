package com.khelkund.privateleague.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khelkund.privateleague.R;
import com.khelkund.privateleague.activities.PrivateLeagueDetailsActivity;
import com.khelkund.privateleague.adapters.PrivateLeagueLeaderboardAdapter;
import com.khelkund.privateleague.adapters.PrivateLeagueListAdapter;
import com.khelkund.privateleague.model.PrivateLeague;
import com.khelkund.privateleague.model.UserTeam;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by sathley on 1/29/2015.
 */
public class LeaderBoardFragment extends Fragment {

    public PrivateLeague mPrivateLeague;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static LeaderBoardFragment newInstance() {
        return new LeaderBoardFragment();
    }

    public LeaderBoardFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_private_league_leaderboard, container, false);
        mPrivateLeague = ((PrivateLeagueDetailsActivity) getActivity()).getPrivateLeague();
        Collections.sort(mPrivateLeague.userTeams, UserTeam.Ascending_Rank_COMPARATOR);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_private_league_leaderboard);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PrivateLeagueLeaderboardAdapter(getActivity(), mPrivateLeague);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }
}
