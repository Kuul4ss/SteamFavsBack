package com.example.steam.ReqResponse.MostPlayedGames

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MPGResponse (
    val response: MPGList
) {
    override fun toString(): String {
        return "MPGResponse(response=$response)"
    }
}

class MPGList (
    @SerializedName("rollup_date")
    val rollupDate: Long,
    val ranks: List<Rank>
) {
    override fun toString(): String {
        return "MPGList(rollupDate=$rollupDate, ranks=$ranks)"
    }
}

class Rank(
    val rank: Int,
    val appid: Int,

    @SerializedName("last_week_rank")
    val lastWeekRank: Int,

    @SerializedName("peak_in_game")
    val peakInGame: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong()
    ) {
    }

    override fun toString(): String {
        return "Rank(rank=$rank, appid=$appid, lastWeekRank=$lastWeekRank, peakInGame=$peakInGame)"
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Rank> {
        override fun createFromParcel(parcel: Parcel): Rank {
            return Rank(parcel)
        }

        override fun newArray(size: Int): Array<Rank?> {
            return arrayOfNulls(size)
        }
    }
}

class MostPlayedGames() {







}