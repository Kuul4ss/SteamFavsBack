package com.example.steam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.SteamGameByIdResponseDeserializer
import com.example.steam.ReqResponse.APISteam
import com.example.steam.ReqResponse.GameById.GameById
import com.example.steam.ReqResponse.MostPlayedGames.MostPlayedGames
import com.example.steam.fragment.MyItemRecyclerViewAdapter
import com.example.steam.fragment.placeholder.PlaceholderContent
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import okhttp3.internal.wait
import okhttp3.internal.waitMillis
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mpgList: ArrayList<MostPlayedGames.Rank>
    lateinit var list: RecyclerView
    lateinit var items: ArrayList<PlaceholderContent.PlaceholderItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        generateAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val api = Retrofit.Builder()
            .baseUrl("https://api.steampowered.com/")
            .addConverterFactory(
                GsonConverterFactory.create())
            .addCallAdapterFactory(
                CoroutineCallAdapterFactory()
            )
            .build()
            .create(APISteam::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val list = withContext(Dispatchers.IO)
                { api.getMostPlayedGames().await() }.response
                convertList(list);
            } catch (e: Exception) {

            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.list = view.findViewById(R.id.search_list) as RecyclerView

    }

    suspend fun convertList(r: MostPlayedGames.MPGList) {
        this.mpgList = r.ranks as ArrayList<MostPlayedGames.Rank>
        val api = Retrofit.Builder()
            .baseUrl("https://store.steampowered.com/api/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(GameById.SteamGameByIdResponse::class.java, SteamGameByIdResponseDeserializer())
                        .create()
                )
            )
            .addCallAdapterFactory(
                CoroutineCallAdapterFactory()
            )
            .build()
            .create(APISteam::class.java)

        for(i in this.mpgList) {
            GlobalScope.async(Dispatchers.Main) {
                try {
                    val res = withContext(Dispatchers.IO)
                    { api.getGameById(i.appid.toString()).await() }
                    PlaceholderContent.add(
                        res.game.data?.name.toString(),
                        res.game.data?.publishers,
                        res.game.data?.price_overview?.final_formatted.toString()
                    )
                } catch (e: Exception) {

                }
            }.await().apply { generateAdapter() }

        }

    }

    private fun generateAdapter() {
        var adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
        this.list.adapter = null;
        this.list.layoutManager = null;
        this.list.adapter = adapter;
        this.list.layoutManager = LinearLayoutManager(this.context);
    }

}