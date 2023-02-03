package com.example.steam.ReqResponse

import com.example.steam.ReqResponse.GameById.GameById
import com.example.steam.ReqResponse.GameByName.GameByName
import com.example.steam.ReqResponse.MostPlayedGames.MostPlayedGames
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APISteam {
    @GET("ISteamChartsService/GetMostPlayedGames/v1/?")
    fun getMostPlayedGames() : Deferred<MostPlayedGames.MPGResponse>

    @GET("appdetails")
    fun getGameById(@Query("appids") id: String): Deferred<GameById.SteamGameByIdResponse>

    @GET("actions/SearchApps/{name}")
    fun getGames(@Path("name") name:String) : Deferred<ArrayList<GameByName.Game>>

}