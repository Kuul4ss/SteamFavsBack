package com.example.steam.ReqResponse.GameById

import com.google.gson.annotations.SerializedName

class GameById {

    data class SteamGameByIdResponse(val game: SteamGameResponse)

    data class SteamGameResponse(
        val success: Boolean,
        val data: SteamGameResponseData?
    )

    data class SteamGameResponseData(
        val type: String?,
        val name: String?,
        val background: String?,
        val header_image:String?,
        val detailed_description:String?,
        val publishers:ArrayList<String>?,
        val price_overview: Price?
    )

    data class Price(
        val currency:String,
        val initial:String,
        val final:Int?,
        val discount_percent:Int?,
        val initial_formatted:String?,
        val final_formatted:Int?
    )

}