package com.example.steam.placeholder

import com.example.steam.ReqResponse.MostPlayedGames.MPGResponse
import com.example.steam.ReqResponse.MostPlayedGames.MostPlayedGames
import com.example.steam.ReqResponse.MostPlayedGames.Rank
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
class PlaceholderContent(val r: MPGResponse) {

    /**
     * An array of sample (placeholder) items.
     */
    var items: MutableList<PlaceholderItem> = ArrayList()

    private val COUNT = r.response.ranks.size

    private fun addItem(item: Rank) {
        items.add(PlaceholderItem(item.appid.toString(), item.rank.toString()))
    }

    init {
        val iterator = this.r.response.ranks.iterator()
        for(r:Rank in this.r.response.ranks) {
            addItem(r)
        }
    }


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }


    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val appid: String, val rank: String) {
        override fun toString(): String {
            return "PlaceholderItem(appid='$appid', rank='$rank')"
        }
    }
}