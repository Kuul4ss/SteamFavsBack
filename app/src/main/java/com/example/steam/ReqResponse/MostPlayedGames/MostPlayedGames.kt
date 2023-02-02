package com.example.steam.ReqResponse.MostPlayedGames

import com.google.gson.annotations.SerializedName

class MostPlayedGames {

    data class MPGResponse (
        val response: MPGList
    ) {
        override fun toString(): String {
            return "MPGResponse(response=$response)"
        }
    }

    data class MPGList (
        @SerializedName("rollup_date")
        val rollupDate: Long,
        val ranks: List<Rank>
    ) {
        override fun toString(): String {
            return "MPGList(rollupDate=$rollupDate, ranks=$ranks)"
        }
    }

    data class Rank(
        val rank: Int,
        val appid: Int,

        @SerializedName("last_week_rank")
        val lastWeekRank: Int,

        @SerializedName("peak_in_game")
        val peakInGame: Long


    ) {
        override fun toString(): String {
            return "Rank(rank=$rank, appid=$appid, lastWeekRank=$lastWeekRank, peakInGame=$peakInGame)"
        }
    }
}