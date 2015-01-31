package com.khelkund.privateleague.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by sathley on 1/29/2015.
 */
public class UserTeam implements Parcelable {
    public String logoUrl;
    public int points;
    public int rank;
    public String username;
    public String name;
    public String id;

    public UserTeam()
    {

    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(logoUrl);
        parcel.writeInt(points);
        parcel.writeInt(rank);
        parcel.writeString(username);
        parcel.writeString(name);
        parcel.writeString(id);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserTeam createFromParcel(Parcel in) {
            return new UserTeam(in);
        }
        public UserTeam[] newArray(int size) {
            return new UserTeam[size];
        }
    };

    private UserTeam(Parcel in)
    {
        logoUrl = in.readString();
        points = in.readInt();
        rank = in.readInt();
        username = in.readString();
        name = in.readString();
        id = in.readString();
    }

    public static final Comparator<UserTeam> Ascending_Rank_COMPARATOR = new Comparator<UserTeam>() {
        public int compare(UserTeam team, UserTeam team1) {
            return team.rank - team1.rank;
        }
    };
}
