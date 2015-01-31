package com.khelkund.privateleague.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sathley on 1/29/2015.
 */
public class PrivateLeague implements Parcelable {
    public String id;
    public String name;
    public List<UserTeam> userTeams = new ArrayList<UserTeam>();

    public PrivateLeague()
    {
        userTeams = new ArrayList<>();
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeTypedList(userTeams);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PrivateLeague createFromParcel(Parcel in) {
            return new PrivateLeague(in);
        }
        public PrivateLeague[] newArray(int size) {
            return new PrivateLeague[size];
        }
    };

    private PrivateLeague(Parcel in)
    {
        id = in.readString();
        name = in.readString();
        in.readTypedList(userTeams, UserTeam.CREATOR);
    }
}
