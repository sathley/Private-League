package com.khelkund.privateleague.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khelkund.privateleague.R;
import com.khelkund.privateleague.model.PrivateLeague;
import com.khelkund.privateleague.model.UserTeam;

import java.util.List;

/**
 * Created by sathley on 1/29/2015.
 */
public class PrivateLeagueLeaderboardAdapter extends RecyclerView.Adapter<PrivateLeagueLeaderboardAdapter.PrivateLeagueLeaderboardViewHolder> {


    private PrivateLeague mPrivateLeague;
    private Context mContext;

    public PrivateLeagueLeaderboardAdapter(Context context, PrivateLeague privateLeague)
    {
        this.mContext = context;
        this.mPrivateLeague = privateLeague;
    }

    @Override
    public PrivateLeagueLeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_private_league_leaderboard, parent, false);
        return new PrivateLeagueLeaderboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrivateLeagueLeaderboardViewHolder holder, int position) {
        UserTeam userTeam = mPrivateLeague.userTeams.get(position);
        holder.userName.setText(userTeam.username);
        holder.teamName.setText(userTeam.name);
        holder.points.setText(String.valueOf(userTeam.points));
        holder.rank.setText(String.valueOf(userTeam.rank));

        //  TODO Apply proper logo here
        holder.logo.setImageResource(R.drawable.logo);
    }

    @Override
    public int getItemCount() {
        return mPrivateLeague.userTeams.size();
    }

    public static class PrivateLeagueLeaderboardViewHolder extends RecyclerView.ViewHolder {

        protected TextView userName;
        protected TextView teamName;
        protected TextView rank;
        protected ImageView logo;
        protected TextView points;

        public PrivateLeagueLeaderboardViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.item_leaderboard_username);
            rank = (TextView) itemView.findViewById(R.id.item_leaderboard_rank);
            logo = (ImageView) itemView.findViewById(R.id.item_leaderboard_logo);
            points = (TextView) itemView.findViewById(R.id.item_leaderboard_points);
            teamName = (TextView) itemView.findViewById(R.id.item_leaderboard_teamname);
        }
    }
}
