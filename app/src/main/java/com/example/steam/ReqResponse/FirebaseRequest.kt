package com.example.steam.ReqResponse

import android.os.Bundle
import com.example.steam.R
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseRequest {
    val db = Firebase.firestore

        //initUser("15459676")
        //addGame("15459676","likes","630")
        //removeGame("15459676","likes","630")
        //getGames("15459676")


    fun getGames(userId: String):User{
        var user = User(null,null,null)
        val docRef =  db.collection("users")
            .whereEqualTo("userID", userId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    user = User(
                        document.data.get("userID") as String?,
                        document.data.get("likes") as ArrayList<String?>?,
                        document.data.get("wishes") as ArrayList<String?>?
                    )
                }
            }
        return user
    }

    fun initUser(userId: String){
        val user = User( userId,null, null)
        db.collection("users").document(userId).set(user)
    }

    fun addGame(userId: String,field :String,gameId : String){
        db.collection("users").document(
            userId).update(field, FieldValue.arrayUnion(gameId))
    }

    fun removeGame(userId: String,field :String,gameId : String){
        db.collection("users").document(
            userId).update(field, FieldValue.arrayRemove(gameId))
    }

}

data class User(
    @get: PropertyName("userId") @set: PropertyName("userId") var userId: String?,
    @get: PropertyName("likes") @set: PropertyName("likes") var likes: ArrayList<String?>?,
    @get: PropertyName("whishes") @set: PropertyName("whishes") var whishes: ArrayList<String?>?
)
