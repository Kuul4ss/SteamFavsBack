package com.example.steam.ReqResponse.MostPlayedGames

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface APISteam {
    @GET("/ISteamChartsService/GetMostPlayedGames/v1/?")
    fun getMotPlayedGames() : Deferred<MostPlayedGames.MPGResponse>

}