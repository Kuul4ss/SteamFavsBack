package com.example.steam.ReqResponse.GameByName

import com.google.gson.annotations.SerializedName

class GameByName {

    data class Game(
        val appid: String?,
        val icon : String?,
        val logo : String?,
        val name : String?
    )

}