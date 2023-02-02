package com.example.myapplication

import com.example.steam.ReqResponse.GameById.GameById
import com.google.gson.*
import java.lang.reflect.Type

class SteamGameByIdResponseDeserializer : JsonDeserializer<GameById.SteamGameByIdResponse> {


    companion object {
        val deserializer: Gson = GsonBuilder().create()
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GameById.SteamGameByIdResponse {
        // On récupère le JSON
        val jsonObject = json?.asJsonObject

        // On récupère la clé du premier élément du json (ex : "578080") qui est un entier
        val key = jsonObject?.keySet()?.first { it.toIntOrNull() != null }

        return GameById.SteamGameByIdResponse(
            deserializer.fromJson(
                jsonObject?.get(key), GameById.SteamGameResponse::class.java
            )
        )
    }
}