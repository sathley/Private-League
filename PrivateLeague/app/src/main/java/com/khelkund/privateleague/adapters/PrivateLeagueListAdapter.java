package com.khelkund.privateleague.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khelkund.privateleague.activities.PrivateLeagueDetailsActivity;
import com.khelkund.privateleague.R;
import com.khelkund.privateleague.model.PrivateLeague;

import java.util.List;

/**
 * Created by sathley on 1/28/2015.
 */
public class PrivateLeagueListAdapter extends RecyclerView.Adapter<PrivateLeagueListAdapter.PrivateLeagueViewHolder> {

    private List<PrivateLeague> mPrivateLeagues;
    private Context mContext;
    public PrivateLeagueListAdapter(Context context, List<PrivateLeague> privateLeagues)
    {
        this.mContext = context;
        this.mPrivateLeagues = privateLeagues;
    }

    @Override
    public PrivateLeagueViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_private_league, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent privateLeagueDetailsIntent = new Intent(mContext, PrivateLeagueDetailsActivity.class);
                privateLeagueDetailsIntent.putExtra("private_league", mPrivateLeagues.get(i));
                mContext.startActivity(privateLeagueDetailsIntent);
            }
        });
        return new PrivateLeagueViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrivateLeagueViewHolder viewHolder, int i) {
        PrivateLeague privateLeague = mPrivateLeagues.get(i);
        viewHolder.name.setText(privateLeague.name);

        //  TODO Compute rank and points and correct logo to display
        viewHolder.logo.setImageResource(R.drawable.logo);
        viewHolder.points.setText("12.12");
        viewHolder.rank.setText("You stand 1st");
    }

    @Override
    public int getItemCount() {
        return mPrivateLeagues.size();
    }

    public static class PrivateLeagueViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView rank;
        protected ImageView logo;
        protected TextView points;

        public PrivateLeagueViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_league_name);
            rank = (TextView) itemView.findViewById(R.id.item_league_rank);
            logo = (ImageView) itemView.findViewById(R.id.item_logo);
            points = (TextView) itemView.findViewById(R.id.item_points);
        }
    }
}
