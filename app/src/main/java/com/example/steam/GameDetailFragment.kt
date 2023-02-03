package com.example.steam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var gameImage: ImageView
    lateinit var gameName: TextView
    lateinit var gameEditor: TextView
    lateinit var descriptionButton: Button
    lateinit var opinionButton: Button
    lateinit var descriptionMessage: TextView
    lateinit var opinionList: RecyclerView

    var mode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.gameImage = (view.findViewById<FrameLayout>(R.id.game_image)) as ImageView
        this.gameName = (view.findViewById<FrameLayout>(R.id.game_name)) as TextView
        gameName.text = arguments?.getString("name") ?: ""
        this.gameEditor = (view.findViewById<FrameLayout>(R.id.game_editor)) as TextView
        gameEditor.text = arguments?.getString("editor") ?: ""
        this.descriptionButton = (view.findViewById<FrameLayout>(R.id.description_button)) as Button
        this.opinionButton = (view.findViewById<FrameLayout>(R.id.opinion_button)) as Button
        this.descriptionMessage = (view.findViewById<FrameLayout>(R.id.description_message)) as TextView
        descriptionMessage.text = arguments?.getString("description") ?: ""
        this.opinionList = (view.findViewById<FrameLayout>(R.id.opinion_list)) as RecyclerView

        opinionButton.setOnClickListener {
            changeMode(true)
        }

        descriptionButton.setOnClickListener {
            changeMode(false)
        }

    }

    fun changeMode(boolean: Boolean) {
        this.mode = boolean
        if(boolean) {
            descriptionMessage.visibility = View.GONE
            opinionList.visibility = View.VISIBLE
        } else {
            descriptionMessage.visibility = View.VISIBLE
            opinionList.visibility = View.GONE
        }
    }
}